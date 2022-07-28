package search_engine;

import org.griddynamics.search_engine.Person;
import org.griddynamics.search_engine.Searcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Searcher class tests
 */
public class SearcherTest {

    // str -> Person[] map
    private Map<String, Person[]> peopleMap;

    /**
     * Initializes people array
     */
    @BeforeEach
    private void initializePeople() {
        // Initializing array
        Person[] people = new Person[6];

        // Initializing instances
        people[0] = new Person("Dwight", "Joseph", "djo@gmail.com");
        people[1] = new Person("Rene", "Webb", "webb@gmail.com");
        people[2] = new Person("Katie", "Jacobs", "");
        people[3] = new Person("Erick", "Harrington", "harrington@gmail.com");
        people[4] = new Person("Myrtle", "Medina", "");
        people[5] = new Person("Erick", "Burgess", "");

        // Creating map based on people
        peopleMap = Searcher.createStringToPersonArrayMap(people);
    }

    @Test
    void erickTest() {
        // Searching by keyword
        Person[] result = Searcher.searchByKeyword(peopleMap, "Erick");

        // Asserting
        assertEquals(2, result.length);
        assertEquals((new Person("Erick", "Harrington", "harrington@gmail.com")), result[0]);
        assertEquals((new Person("Erick", "Burgess", "")), result[1]);
    }

    @Test
    void noMatchTest() {
        // Searching by keyword
        Person[] result = Searcher.searchByKeyword(peopleMap, "there is no me in there");

        // Asserting
        assertNull(result);
    }

    @Test
    void webbTest() {
        // Searching by keyword
        Person[] result = Searcher.searchByKeyword(peopleMap, "webb@gmail.com");

        // Asserting
        assertEquals(1, result.length);
        assertEquals((new Person("Rene", "Webb", "webb@gmail.com")), result[0]);
    }
}
