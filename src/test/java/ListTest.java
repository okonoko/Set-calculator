import org.junit.*;
import static org.junit.Assert.*;
import java.math.BigInteger;

/**
 * Created by Sebastian on 08/08/15.
 */
public class ListTest {

    @Before
    public void setUp() {

        // Add any maintenance which is necessary to set up your tests.
    }

    @Test
    public void testIsEmpty() {
        // Test an empty list.
        List<Identifier> list = new List<>();
        assertTrue("New list should be empty", list.isEmpty());

        list.insert(new Identifier("2"));
        assertFalse("Adding one element should return false.", list.isEmpty());

        list.remove();
        assertTrue("Removing should make list empty again.", list.isEmpty());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testInit() {
        List<Identifier> list = new List<>();

        // Create an empty list with init.
        list.init();
        assertTrue("Init on empty list should return an empty list", list.isEmpty());

        // Add item, init should still be empty.
        list.insert(new Identifier("2"));
        list.init();
        assertTrue("Init on non-empty list should return an empty list", list.isEmpty());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testSize() {
        List<Identifier> list = new List<>();

        assertEquals("Empty list should be size 0", 0, list.size());

        // Insert one item
        list.insert(new Identifier("2"));
        assertEquals("List of one element should have size 1", 1, list.size());

        // Add 200 items to the list.
        for (int i = 0; i < 200; i++) {
            list.insert(new Identifier("2"));
        }
        assertEquals("Adding many elements should result in a long list", 201, list.size());

        // Remove 1 item -> 200 items left
        list.remove();
        assertEquals("Removing one item should decrement the size", 200, list.size());

        // Init should empty the list.
        list.init();
        assertEquals("Init should set size to zero", 0, list.size());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testInsert() {
        List<Identifier> list = new List<>();

        /* Insert first item
         * Inserting into empty list is an edge case.
         */
        Identifier c = new Identifier("3");
        list.insert(c);
        assertEquals("Inserted element should be in list", c, list.retrieve());

        // Append second item
        Identifier f = new Identifier("9");
        list.insert(f);

        list.goToLast();
        assertEquals("Insert should order larger elements later in list", f, list.retrieve());

        list.goToFirst();
        assertEquals("Previous elements should still be in list", c, list.retrieve());


        // Insert in front of list
        // Test that the list is sorted correctly when inserting a smaller item.
        Identifier a = new Identifier("1");
        list.insert(a);
        list.goToFirst();
        assertEquals("Insert should order smaller elements earlier in list", a, list.retrieve());


        // Insert at the end.
        // Test that the list is sorted correctly when inserting a larger item.
        Identifier k = new Identifier("4");
        list.insert(k);
        list.goToLast();
        assertEquals(k, list.retrieve());


        // Insert between two items.
        // The order has to be preserved when inserting an item between existing items.
        Identifier b = new Identifier("2");
        list.insert(b);
        list.goToFirst();
        assertEquals(a, list.retrieve());
        list.goToNext();
        assertEquals(b, list.retrieve());
        list.goToNext();
        assertEquals(c, list.retrieve());

        // TODO: You can add more of your own tests. e.g.:
        // Insert duplicate item.
    }

    @Test
    public void testRetrieve() {

        List<Identifier> list = new List<>();

        Identifier z = new Identifier("22");
        list.insert(z);

        Identifier Identifier = list.retrieve();
        assertEquals("Retrieve should return an equal object", z, Identifier);

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testRemove() {
        List<Identifier> list = new List<>();
        Identifier a = new Identifier("3");
        Identifier b = new Identifier("4");
        Identifier c = new Identifier("5");
        Identifier d = new Identifier("6");
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);
        // Remove an element in the middle
        list.goToFirst();
        list.goToNext();
        list.remove();

        assertEquals(c, list.retrieve());

        // Remove last element in list
        list.goToLast();
        list.remove();
        assertEquals(c, list.retrieve());


        // Remove on list with size 1
        list.remove();
        list.remove();
        try {
            assertNull(list.retrieve()); // Inconsistent specification. Undefined behaviour for retrieve on empty list.
        } catch (NullPointerException e) {
        }

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testFind() {
    	List<Identifier> list = new List<>();
    	
    	Identifier a = new Identifier("2332434543654563234");
        Identifier b = new Identifier("2654643356756723413");
        Identifier c = new Identifier("7923094902339904243");
        Identifier d = new Identifier("3784798123829131090");
        Identifier g = new Identifier("67");
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);
        
        assertFalse(list.find(g));
        assertTrue(list.find(d));
        assertTrue(list.find(b));
        assertTrue(list.find(a));
        assertTrue(list.find(c));
        // TODO: You can add more of your own tests.
    }

    @Test
    public void testgoToFirst() {

        List<Identifier> list = new List<>();

        // Test on empty list
        assertFalse(list.goToFirst());

        Identifier a = new Identifier("2332434543654563234");
        Identifier b = new Identifier("2654643356756723413");
        Identifier c = new Identifier("7923094902339604214");
        Identifier d = new Identifier("3784798123829131090");
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);

        // Test on list with some elements.
        assertTrue(list.goToFirst());

        assertEquals(a, list.retrieve());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testgoToLast() {
        List<Identifier> list = new List<>();

        // Test on empty list
        assertFalse(list.goToLast());

        Identifier a = new Identifier("2");
        Identifier b = new Identifier("6");
        Identifier c = new Identifier("8");
        Identifier d = new Identifier("98");
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);

        // Test on list with some elements.
        assertTrue(list.goToLast());
        assertEquals(d, list.retrieve());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testgoToNext() {
        List<Identifier> list = new List<>();

        // Test on empty list
        assertFalse(list.goToNext());

        Identifier a = new Identifier("2332434543654563234");
        Identifier b = new Identifier("2654643356756723413");
        Identifier c = new Identifier("7923094902339704214");
        Identifier d = new Identifier("3784798123829131090");
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);

        // Test on last element
        assertFalse(list.goToNext());
        assertEquals(d, list.retrieve());


        // Test on first
        list.goToFirst();
        assertTrue(list.goToNext());
        assertEquals(b, list.retrieve());

        // TODO: You can add more of your own tests.
    }

    @Test
    public void testgoToPrevious() {

        List<Identifier> list = new List<>();

        // Test on empty list
        assertFalse(list.goToNext());

        Identifier a = new Identifier("2332434543654563234");
        Identifier b = new Identifier("2654643356756723413");
        Identifier c = new Identifier("7923094902339604214");
        Identifier d = new Identifier("3784798123829131090");
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);

        // Test on last element
        list.goToLast();
        assertTrue(list.goToPrevious());
        assertEquals(c, list.retrieve());

        // Test on first
        list.goToFirst();
        assertFalse(list.goToPrevious());
        assertEquals(a, list.retrieve());

        // TODO: You can add more of your own tests.
    }
    @Test
    public void testCopy() {
    	List<Identifier> list = new List<>();

        // Test on empty list
    	try {
            assertNull(list.copy().retrieve()); // Inconsistent specification. Undefined behaviour for retrieve on empty list.
        } catch (NullPointerException e) {}

        Identifier a = new Identifier("2332434543654563234");
        Identifier b = new Identifier("2654643356756723413");
        Identifier c = new Identifier("7923094902339704214");
        Identifier d = new Identifier("3784798123829131090");
        list.insert(a);
        list.insert(b);
        list.insert(c);
        list.insert(d);
        
        assertFalse(list.copy().isEmpty());
        assertTrue(list.copy().find(d));
        assertTrue(list.copy().goToFirst());
        assertEquals(d, list.copy().retrieve());
        assertTrue(list.copy().goToLast());
        assertFalse(list.copy().goToNext());
        assertTrue(list.copy().goToPrevious());
        
        
        
    }

    /**
     * Represents a comparable and clonable Identifier.
     *
     * This internal class is only used for testing.
     * It is independent of any of your Implementations.
     *
     * If you write your own tests you may also use your own
     * Implementations (i.e., of Identifier).
     */
    

    public class Identifier implements Comparable<Identifier>{
    	
    	public BigInteger value;
    	public String buffer;
    	
    	Identifier (String buffer){
    		value = new BigInteger(buffer);
    	}
    	public int compareTo(Identifier x) {
    		return value.compareTo(x.value);
    	}
	
    	public boolean equals(Object o){

    		if(o != null && o.getClass() == getClass()) {
    			return this.compareTo((Identifier)o) == 0;
    		}
    		return false;
    	}
    }
    /*private class Identifier implements Comparable<Identifier>, Cloneable {

        private char Identifier;

        public Identifier(char c){
            this.Identifier = c;
        }

        public int compareTo(Identifier l) {
            return this.Identifier - l.Identifier;
        }

        public Identifier clone() {
            return new Identifier(this.Identifier);
        }

        /*
         * Tests whether o is the same as this Identifier.
         *
         * For assertEquals() to work an equals() method is necessary.
         *
         * Adapt this method for any of your classes that you use in assertEquals(). 
         */
        //public boolean equals(Object o){

            // First clause: Test whether o is null before treating it as an object.
            // Second clause: Test whether o is of correct type.
            //                Change this type when copying to another class.
            //if(o != null && o.getClass() == getClass()) {

                // Now we know that o is not null and has the same type as this.

                // Do any calculation to determine whether o and this are the same Identifier.
                // In this case, Identifier implements Comparable, so we can use compareTo().
            //    return this.compareTo((Identifier)o) == 0;
          //  }

            // Since o was null or not of type Identifier, we can safely conclude
            // that o does not equal this.
        //    return false;
      //  }
    //}
}