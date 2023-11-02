package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STALLS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Menu;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;
import seedu.address.model.stall.review.StallReview;

/**
 * Edits the details of an existing stall in FoodNotes.
 */
public class EditStallCommand extends Command {

    public static final String COMMAND_WORD = "edit-stall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the stall identified "
            + "by the index number used in the displayed stall list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Stall must have a review before rating and description can be edited.\n"
            + "Parameters: "
            + PREFIX_STALL + "STALL_INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STALL + "1 "
            + PREFIX_NAME + "Chicken Rice ";


    public static final String MESSAGE_EDIT_STALL_SUCCESS = "Edited Stall: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STALL = "This stall already exists in FoodNotes.";
    public static final String MESSAGE_NO_REVIEW_FOUND = "This stall has no review to edit.";

    private final Index index;
    private final EditStallDescriptor editStallDescriptor;

    /**
     * @param index of the stall in the filtered stall list to edit
     * @param editStallDescriptor details to edit the stall with
     *
     */
    public EditStallCommand(Index index, EditStallDescriptor editStallDescriptor) {
        requireNonNull(index);
        requireNonNull(editStallDescriptor);

        this.index = index;
        this.editStallDescriptor = new EditStallDescriptor(editStallDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToEdit = lastShownList.get(index.getZeroBased());

        if (!stallToEdit.hasStallReview() && editStallDescriptor.isReviewEdited()) {
            throw new CommandException(MESSAGE_NO_REVIEW_FOUND);
        }

        Stall editedStall = createEditedStall(stallToEdit, editStallDescriptor);

        if (!stallToEdit.isSameStall(editedStall) && model.hasStall(editedStall)) {
            throw new CommandException(MESSAGE_DUPLICATE_STALL);
        }

        model.setStall(stallToEdit, editedStall);
        model.updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);

        model.showStall(stallToEdit);
        model.setFilteredStall(index);
        model.setFilteredItemList(index);

        return new CommandResult(String.format(MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall)),
                CommandResult.ViewType.STALL_DETAIL);
    }

    /**
     * Creates and returns a {@code Stall} with the details of {@code stallToEdit}
     * edited with {@code editStallDescriptor}.
     */
    private static Stall createEditedStall(Stall stallToEdit, EditStallDescriptor editStallDescriptor) {
        assert stallToEdit != null;

        Name updatedName = editStallDescriptor.getName().orElse(stallToEdit.getName());
        Location updatedLocation = editStallDescriptor.getLocation().orElse(stallToEdit.getLocation());
        Menu updatedMenu = editStallDescriptor.getMenu().orElse(stallToEdit.getMenu());
        try {
            Rating updatedRating = editStallDescriptor.getRating().orElse(stallToEdit.getStallReview().getRating());
            Description updatedDescription = editStallDescriptor.getDescription()
                    .orElse(stallToEdit.getStallReview().getDescription());
            StallReview updatedStallReview = new StallReview(updatedRating, updatedDescription);
            Stall editedStall = new Stall(updatedName, updatedLocation, updatedMenu, updatedStallReview);
            return editedStall;
        } catch (NullPointerException e) {
            Stall editedStall = new Stall(updatedName, updatedLocation, updatedMenu);
            return editedStall;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStallCommand)) {
            return false;
        }

        EditStallCommand otherEditStallCommand = (EditStallCommand) other;
        return index.equals(otherEditStallCommand.index)
                && editStallDescriptor.equals(otherEditStallCommand.editStallDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editStallDescriptor", editStallDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the stall with. Each non-empty field value will replace the
     * corresponding field value of the stall.
     */
    public static class EditStallDescriptor {
        private Name name;
        private Location location;
        private Menu menu;
        private Rating rating;
        private Description description;

        public EditStallDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStallDescriptor(EditStallDescriptor toCopy) {
            setName(toCopy.name);
            setLocation(toCopy.location);
            setRating(toCopy.rating);
            setDescription(toCopy.description);
            setMenu(toCopy.menu);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, location, rating, description);
        }

        public boolean isReviewEdited() {
            return CollectionUtil.isAnyNonNull(rating, description);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public Optional<Menu> getMenu() {
            return Optional.ofNullable(menu);
        }


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStallDescriptor)) {
                return false;
            }

            EditStallDescriptor otherEditStallDescriptor = (EditStallDescriptor) other;
            return Objects.equals(name, otherEditStallDescriptor.name)
                    && Objects.equals(location, otherEditStallDescriptor.location)
                    && Objects.equals(rating, otherEditStallDescriptor.rating)
                    && Objects.equals(description, otherEditStallDescriptor.description);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("location", location)
                    .add("rating", rating)
                    .add("description", description)
                    .toString();
        }
    }
}
