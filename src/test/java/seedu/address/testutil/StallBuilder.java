package seedu.address.testutil;

import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;

/**
 * A utility class to help with building Stall objects.
 */
public class StallBuilder {

    public static final String DEFAULT_NAME = "Chicken Rice";
    public static final String DEFAULT_LOCATION = "Deck";
    private Name name;
    private Location location;


    /**
     * Creates a {@code StallBuilder} with the default details.
     */
    public StallBuilder() {
        name = new Name(DEFAULT_NAME);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the StallBuilder with the data of {@code stallToCopy}.
     */
    public StallBuilder(Stall stallToCopy) {
        name = stallToCopy.getName();
        location = stallToCopy.getLocation();
    }

    /**
     * Sets the {@code Name} of the {@code Stall} that we are building.
     */
    public StallBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Stall} that we are building.
     */
    public StallBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public Stall build() {
        return new Stall(name, location);
    }

}
