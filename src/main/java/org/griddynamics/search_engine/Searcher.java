package org.griddynamics.search_engine;

import java.util.*;

/**
 * Implements substring search in multiple lines
 */
public class Searcher {

    /**
     * Creates string -> Person[] map based on given array
     * @param people | Array of Person to form map for
     * @return map, key: string, value: Person array, each element has key in it
     */
    public static Map<String, Person[]> createStringToPersonArrayMap(Person[] people) {
        // Creating temporary map
        Map<String, List<Person>> tempMap = new HashMap<>();

        // Filling temp
        String[] currentInfo;
        List<Person> tempList;
        for (Person currentPerson : people) {
            // Taking current person's info
            currentInfo = Arrays.stream(takePersonInfo(currentPerson))
                                .map(String::toLowerCase)
                                .toArray(String[]::new);

            // Iterating on info
            for (String currentPart : currentInfo) {
                // Filling with current info
                if (!"".equals(currentPart)) {
                    if (tempMap.containsKey(currentPart)) {
                        tempMap.get(currentPart).add(currentPerson);
                    } else {
                        tempList = new LinkedList<>();
                        tempList.add(currentPerson);
                        tempMap.put(currentPart, tempList);
                    }
                }
            }
        }

        // Creating result
        Map<String, Person[]> result = new HashMap<>();
        for (var current : tempMap.entrySet()) {
            result.put(current.getKey(), current.getValue().toArray(Person[]::new));
        }

        // Returning
        return result;
    }

    private static String[] takePersonInfo(Person person) {
        return new String[]{ person.getFirstName(), person.getLastName(), person.getEmail() };
    }

    /**
     * Returns array of persons, each corresponds to keyword
     * @param map | str -> Person[] map to search in
     * @return Array of persons, each one corresponds to keyword
     */
    public static Person[] searchByKeyword(Map<String, Person[]> map, String keyword) {
        // Putting keyword to lower case
        String lowKeyword = keyword.toLowerCase();

        // Returning
        return map.get(lowKeyword);
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
