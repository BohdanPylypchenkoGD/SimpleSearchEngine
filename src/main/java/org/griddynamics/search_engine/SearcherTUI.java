package org.griddynamics.search_engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Implements TUI for Searcher class
 */
public class SearcherTUI {

    // Private static stdin scanner
    private static final Scanner scanIn = new Scanner(System.in);

    /**
     * Program menu
     */
    public static void menu(File data) {
        // Getting people
        Person[] people = readPeopleFromFile(data);

        // Getting string -> Person[] map
        Map<String, Person[]> map = Searcher.createStringToPersonArrayMap(people);

        // Menu cycle
        String userDecision;
        while(true) {
            // Printing
            System.out.println("\n=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");

            // Getting user decision
            userDecision = scanIn.nextLine();

            // Switching
            switch (userDecision) {
                case "1":
                    searchByKeyword(map);
                    break;
                case "2":
                    printPeople(people);
                    break;
                case "0":
                    System.out.println("\nBye!");
                    System.exit(0);
                default:
                    System.out.println("\nIncorrect option! Try again.");
            }
        }
    }

    /**
     * Reads people from given file
     * @param data | File instance to read from
     * @return Array of Person, loaded from file
     */
    private static Person[] readPeopleFromFile(File data) {
        // Linked list to store people
        List<Person> people = new LinkedList<>();

        // Scanner to read from file
        Scanner dataScan;
        try {
            dataScan = new Scanner(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Reading
        while(dataScan.hasNextLine()) {
            // Adding to list
            people.add(Person.parsePerson(dataScan.nextLine()));
        }

        // Returning
        return people.toArray(Person[]::new);
    }

    /**
     * Prints persons in array
     * @param people | Array of Person to print
     */
    private static void printPeople(Person[] people) {
        System.out.println("\n=== List of people ===");
        for (Person current : people) {
            System.out.println(current.toString());
        }
    }

    /**
     * TUI for searchByKeyword method
     */
    private static void searchByKeyword(Map<String, Person[]> map) {
        // Asking to enter a keyword
        System.out.println("\nEnter a name or email to search all suitable people.");

        // Reading keyword
        String keyword = scanIn.nextLine();

        // Processing query
        Person[] result = Searcher.searchByKeyword(map, keyword);

        // Printing result
        if (result == null) {
            System.out.println("No matching people found.");
        } else {
            System.out.printf("%d persons found:\n", result.length);
            for (Person current : result) {
                System.out.println(current);
            }
        }
    }

    /**
     * Gets people with data, given by user
     * @return array of Person
     */
    private static Person[] getPeopleFromUser() {
        // Asking to enter people amount
        System.out.println("Enter the number of people:");

        // Reading amount of people
        int peopleAmount = Integer.parseInt(scanIn.nextLine());

        // Reading people
        System.out.println("Enter all people:");
        Person[] people = new Person[peopleAmount];
        for (int i = 0; i < peopleAmount; i++) {
            people[i] = Person.parsePerson(scanIn.nextLine());
        }

        // Returning
        return people;
    }
}
