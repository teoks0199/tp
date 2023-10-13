package seedu.address.model.stall;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Stall in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Stall {

    // Identity fields
    private final Name name;
    private final Location location;

    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Stall(Name name, Location location) {
        requireAllNonNull(name, location);
        this.name = name;
        this.location = location;
        this.phone = null;
        this.email = null;
        this.address = null;
        this.tags.addAll(null);
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    /**
     * Returns true if both stalls have the same name.
     * This defines a weaker notion of equality between two stalls.
     */
    public boolean isSameStall(Stall otherStall) {
        if (otherStall == this) {
            return true;
        }

        return otherStall != null
                && otherStall.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Stall)) {
            return false;
        }

        Stall otherStall = (Stall) other;
        return name.equals(otherStall.name)
                && location.equals(otherStall.location);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, location);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("location", location)
                .toString();
    }

}
