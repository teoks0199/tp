package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.UniqueItemList;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;
import seedu.address.model.item.Item;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Stall[] getSampleStalls() {
        return new Stall[] {
            new Stall(new Name("Alex Yeoh"), new Location("Deck"), getMenuItems()),
            new Stall(new Name("Bernice Yu"), new Location("Deck"), getMenuItems()),
            new Stall(new Name("Charlotte Oliveiro"), new Location("Deck"), getMenuItems()),
            new Stall(new Name("David Li"), new Location("Deck"), getMenuItems()),
            new Stall(new Name("Irfan Ibrahim"), new Location("Deck"), getMenuItems()),
            new Stall(new Name("Roy Balakrishnan"), new Location("Deck"), getMenuItems())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Stall sampleStall : getSampleStalls()) {
            sampleAb.addStall(sampleStall);
        }
        return sampleAb;
    }

//    /**
//     * Returns a tag set containing the list of strings given.
//     */
//    public static Set<Item> getTagSet(String... strings) {
//        return Arrays.stream(strings)
//                .map(Item::new)
//                .collect(Collectors.toSet());
//    }

    public static UniqueItemList getMenuItems() {
        UniqueItemList menu = new UniqueItemList();
        menu.add(new Item(new ItemName("Rice")));
        menu.add(new Item(new ItemName("Noodles")));
        menu.add(new Item(new ItemName("Chicken")));
        return menu;
    }

}
