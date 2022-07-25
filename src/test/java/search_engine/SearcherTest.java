package search_engine;

import org.griddynamics.search_engine.Person;
import org.griddynamics.search_engine.Searcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Searcher class tests
 */
public class SearcherTest {

    @Test
    void erickTest() {
        // Creating people array
        Person[] people = new Person[6];

        // Initializing people
        people[0] = new Person("Dwight", "Joseph", "djo@gmail.com");
        people[1] = new Person("Rene", "Webb", "webb@gmail.com");
        people[2] = new Person("Katie", "Jacobs", "");
        people[3] = new Person("Erick", "Harrington", "harrington@gmail.com");
        people[4] = new Person("Myrtle", "Medina", "");
        people[5] = new Person("Erick", "Burgess", "");

        // Searching by keyword
        Person[] result = Searcher.searchByKeyword(people, "Erick");

        // Asserting
        assertEquals(result[0].getFirstName(), "Erick");
        assertEquals(result[0].getLastName(), "Harrington");
        assertEquals(result[0].getEmail(), "harrington@gmail.com");
        assertEquals(result[1].getFirstName(), "Erick");
        assertEquals(result[1].getLastName(), "Burgess");
        assertEquals(result[1].getEmail(), "");
    }

    @Test
    void noMatchTest() {
        // Creating people array
        Person[] people = new Person[6];

        // Initializing people
        people[0] = new Person("Dwight", "Joseph", "djo@gmail.com");
        people[1] = new Person("Rene", "Webb", "webb@gmail.com");
        people[2] = new Person("Katie", "Jacobs", "");
        people[3] = new Person("Erick", "Harrington", "harrington@gmail.com");
        people[4] = new Person("Myrtle", "Medina", "");
        people[5] = new Person("Erick", "Burgess", "");

        // Searching by keyword
        Person[] result = Searcher.searchByKeyword(people, "there is no me in there");

        // Asserting
        assertEquals(result.length, 0);
    }

    @Test
    void webbTest() {
        // Creating people array
        Person[] people = new Person[6];

        // Initializing people
        people[0] = new Person("Dwight", "Joseph", "djo@gmail.com");
        people[1] = new Person("Rene", "Webb", "webb@gmail.com");
        people[2] = new Person("Katie", "Jacobs", "");
        people[3] = new Person("Erick", "Harrington", "harrington@gmail.com");
        people[4] = new Person("Myrtle", "Medina", "");
        people[5] = new Person("Erick", "Burgess", "");

        // Searching by keyword
        Person[] result = Searcher.searchByKeyword(people, "webb@gmail.com");

        // Asserting
        assertEquals(result.length, 1);
        assertEquals(result[0].getFirstName(), "Rene");
        assertEquals(result[0].getLastName(), "Webb");
        assertEquals(result[0].getEmail(), "webb@gmail.com");
    }

    @Test
    void dogTest() {
        // Creating people array
        Person[] people = new Person[3];

        // Initializing people
        people[0] = new Person("Dwight", "Joseph", "djo@gmail.com");
        people[1] = new Person("Rene", "Webb", "webb@gmail.com");
        people[2] = new Person("Katie", "Jacobs", "");

        // Searching by keyword
        Person[] result = Searcher.searchByKeyword(people, "@");

        // Asserting
        assertEquals(result.length, 2);
        assertEquals(result[0].getFirstName(), "Dwight");
        assertEquals(result[0].getLastName(), "Joseph");
        assertEquals(result[0].getEmail(), "djo@gmail.com");
        assertEquals(result[1].getFirstName(), "Rene");
        assertEquals(result[1].getLastName(), "Webb");
        assertEquals(result[1].getEmail(), "webb@gmail.com");
    }
}
