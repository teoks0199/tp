package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStallDescriptor;
import seedu.address.model.stall.Address;
import seedu.address.model.stall.Email;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Phone;
import seedu.address.model.stall.Stall;
import seedu.address.model.tag.Tag;

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
        descriptor.setPhone(stall.getPhone());
        descriptor.setEmail(stall.getEmail());
        descriptor.setAddress(stall.getAddress());
        descriptor.setTags(stall.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStallDescriptor}
     * that we are building.
     */
    public EditStallDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditStallDescriptor build() {
        return descriptor;
    }
}
