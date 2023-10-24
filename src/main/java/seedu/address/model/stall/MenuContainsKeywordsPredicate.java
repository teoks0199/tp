package seedu.address.model.stall;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Stall}'s {@code Name} matches any of the keywords given.
 */
public class MenuContainsKeywordsPredicate implements Predicate<Stall> {
    private final List<String> keywords;

    public MenuContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Stall stall) {
        return stall.getMenu().containsKeywords(keywords);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MenuContainsKeywordsPredicate)) {
            return false;
        }

        MenuContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (MenuContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
