package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.stall.Address;
import seedu.address.model.stall.Email;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Phone;
import seedu.address.model.stall.Stall;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing stall in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the stall identified "
            + "by the index number used in the displayed stall list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Stall: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This stall already exists in the address book.";

    private final Index index;
    private final EditStallDescriptor editStallDescriptor;

    /**
     * @param index of the stall in the filtered stall list to edit
     * @param editStallDescriptor details to edit the stall with
     */
    public EditCommand(Index index, EditStallDescriptor editStallDescriptor) {
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
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Stall stallToEdit = lastShownList.get(index.getZeroBased());
        Stall editedStall = createEditedPerson(stallToEdit, editStallDescriptor);

        if (!stallToEdit.isSameStall(editedStall) && model.hasStall(editedStall)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setStall(stallToEdit, editedStall);
        model.updateFilteredStallList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedStall)));
    }

    /**
     * Creates and returns a {@code Stall} with the details of {@code stallToEdit}
     * edited with {@code editStallDescriptor}.
     */
    private static Stall createEditedPerson(Stall stallToEdit, EditStallDescriptor editStallDescriptor) {
        assert stallToEdit != null;

        Name updatedName = editStallDescriptor.getName().orElse(stallToEdit.getName());
        Phone updatedPhone = editStallDescriptor.getPhone().orElse(stallToEdit.getPhone());
        Email updatedEmail = editStallDescriptor.getEmail().orElse(stallToEdit.getEmail());
        Address updatedAddress = editStallDescriptor.getAddress().orElse(stallToEdit.getAddress());
        Set<Tag> updatedTags = editStallDescriptor.getTags().orElse(stallToEdit.getTags());

        return new Stall(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editStallDescriptor.equals(otherEditCommand.editStallDescriptor);
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
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditStallDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStallDescriptor(EditStallDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
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
                    && Objects.equals(phone, otherEditStallDescriptor.phone)
                    && Objects.equals(email, otherEditStallDescriptor.email)
                    && Objects.equals(address, otherEditStallDescriptor.address)
                    && Objects.equals(tags, otherEditStallDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .toString();
        }
    }
}
