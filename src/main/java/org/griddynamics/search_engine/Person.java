package org.griddynamics.search_engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents person
 */
public class Person {

    // First name
    private final String firstName;

    // Last name
    private final String lastName;

    // Email
    private final String email;

    // First last name pattern
    private static final Pattern NAME = Pattern.compile("\\w+");

    // Email pattern
    private static final Pattern EMAIL = Pattern.compile("\\w+@\\w+\\.com");

    /**
     * Default constructor
     * @param firstName | person's first name
     * @param lastName  | person's last name
     * @param email     | person's email
     */
    public Person(String firstName, String lastName, String email) {
        // Initializing fields
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Creates Person instance
     * @param s | String to parse Person from
     * @return Person instance, parsed from string
     */
    public static Person parsePerson(String s) {
        // Parsing first name
        String[] firstLastName = parseFirstLastName(s);

        // Parsing email
        String email = parseEmail(s);

        // Returning
        return new Person(firstLastName[0], firstLastName[1], email);
    }

    /**
     * firstName getter
     * @return firstName instance field
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * lastName getter
     * @return lastName instance field
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * email getter
     * @return email instance field
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * toString overriding
     */
    @Override
    public String toString() {
        // Result string
        String result = "";

        // Appending first name
        if (!"".equals(firstName)) {
            result += this.firstName;
        }

        // Appending last name
        if (!"".equals(lastName)) {
            result += " " + this.lastName;
        }

        // Appending email
        if (!"".equals(email)) {
            result += " " + this.email;
        }

        // Returning
        return result;
    }

    /**
     * equals overriding
     */
    @Override
    public boolean equals(Object obj) {
        // Checking if obj in a Person instance
        if (!(obj instanceof Person other)) {
            return false;
        }

        // Checking if obj is the same as this
        if (obj == this) {
            return true;
        }

        // Checking for Person equality
        return (this.firstName.equals(other.firstName)) &&
               (this.lastName.equals(other.lastName))   &&
               (this.email.equals(other.email));
    }

    /**
     * hashCode overriding
     */
    @Override
    public int hashCode() {
        int hash = 47;
        hash = 83 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 83 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 83 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    /**
     * Returns first and last name
     * @param s | String to search in
     * @return Array of 2 strings, 1st - first name, 2nd - last name
     */
    private static String[] parseFirstLastName(String s) {
        // Creating matcher for first last name
        Matcher firstLastMatcher = NAME.matcher(s);

        // Obtaining first names
        String[] result = new String[2];
        for (int i = 0; i < 2; i++) {
            if (firstLastMatcher.find()) {
                result[i] = firstLastMatcher.group();
            } else {
                result[i] = "";
            }
        }

        // Returning result
        return result;
    }

    /**
     * Parses email from given string
     * @param s | String to parse from
     * @return email as string, or "" if s does not have email in it
     */
    private static String parseEmail(String s) {
        // Creating matcher for email
        Matcher emailMatcher = EMAIL.matcher(s);

        // Returning
        return emailMatcher.find() ? emailMatcher.group() : "";
    }
}
