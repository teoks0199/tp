package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStallDescriptor;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;

/**
 * A utility class to help with building EditStallDescriptor objects.
 */
public class EditStallDescriptorBuilder {

    private EditStallDescriptor descriptor;

    public EditStallDescriptorBuilder() {
        descriptor = new EditCommand.EditStallDescriptor();
    }

    public EditStallDescriptorBuilder(EditStallDescriptor descriptor) {
        this.descriptor = new EditCommand.EditStallDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStallDescriptor} with fields containing {@code stall}'s details
     */
    public EditStallDescriptorBuilder(Stall stall) {
        descriptor = new EditStallDescriptor();
        descriptor.setName(stall.getName());
        descriptor.setLocation(stall.getLocation());
    }

    /**
     * Sets the {@code Name} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    public EditStallDescriptor build() {
        return descriptor;
    }
}
