package org.griddynamics.search_engine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements substring search in multiple lines
 */
public class Searcher {

    /**
     * Returns array of persons, each corresponds to keyword
     * @param people | Array to look in
     * @return Array of persons, each one corresponds to keyword
     */
    public static Person[] searchByKeyword(Person[] people, String keyword) {
        // Putting keyword to lower case
        String lowKeyword = keyword.toLowerCase();

        // Returning result as array
        return Arrays.stream(people)
                     .filter(p -> checkPersonForKeyword(p, lowKeyword))
                     .toArray(Person[]::new);
    }

    /**
     * Checks given person for keyword
     * @param p       | person to check
     * @param keyword | keyword to look for
     * @return boolean to indicate, if person corresponds to keyword
     */
    private static boolean checkPersonForKeyword(Person p, String keyword) {
        return (p.getFirstName().toLowerCase().contains(keyword)) ||
               (p.getLastName().toLowerCase().contains(keyword)) ||
               (p.getEmail().toLowerCase().contains(keyword));
    }
}
