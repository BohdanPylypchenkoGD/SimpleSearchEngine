package search_engine;

import org.griddynamics.search_engine.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Person class tests
 */
public class PersonTest {

    @Test
    void toStringTest() {
        // Creating person
        Person person = new Person("Anakin",
                                   "Skywalker",
                                      "theCh0sen1@senate.com");

        // Converting
        String result = person.toString();

        // Asserting
        assertEquals(result, "Anakin Skywalker theCh0sen1@senate.com");
    }

    @Test
    void toStringNoEmailTest() {
        // Creating person
        Person person = new Person("Anakin",
                                   "Skywalker",
                                      "");

        // Converting
        String result = person.toString();

        // Asserting
        assertEquals(result, "Anakin Skywalker");
    }

    @Test
    void parsePersonTest() {
        // Calling parse
        Person result = Person.parsePerson("Anakin Skywalker theCh@senate.com");

        // Asserting
        assertEquals(result.getFirstName(), "Anakin");
        assertEquals(result.getLastName(), "Skywalker");
        assertEquals(result.getEmail(), "theCh@senate.com");
    }

    @Test
    void parsePersonNoEmailTest() {
        // Calling parse
        Person result = Person.parsePerson("Anakin Skywalker");

        // Asserting
        assertEquals(result.getFirstName(), "Anakin");
        assertEquals(result.getLastName(), "Skywalker");
        assertEquals(result.getEmail(), "");
    }

    @Test
    void parsePersonNoLastNameTest() {
        // Calling parse
        Person result = Person.parsePerson("Anakin");

        // Asserting
        assertEquals(result.getFirstName(), "Anakin");
        assertEquals(result.getLastName(), "");
        assertEquals(result.getEmail(), "");
    }

    @Test
    void parsePersonEmailAsLastName() {
        // Calling parse
        Person result = Person.parsePerson("Anakin askywalker@protonmail.com");

        // Asserting
        assertEquals(result.getFirstName(), "Anakin");
        assertEquals(result.getLastName(), "askywalker");
        assertEquals(result.getEmail(), "askywalker@protonmail.com");
    }
}
