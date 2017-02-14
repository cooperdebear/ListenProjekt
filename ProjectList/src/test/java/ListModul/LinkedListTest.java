package ListModul;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;

/**
 *
 * @author cooperdebear
 */
public class LinkedListTest {
    
    private static LinkedList<Integer> list;
    
    @BeforeClass
    public static void createList(){
        list = new LinkedList<>();
    }
    
    @AfterClass
    public static void deleteList(){
        list = null;
    }
    
    @Before
    public void resetList(){
        list = new LinkedList<>();
    }
    
    @Test
    public void add(){
        Object a = 19;
        for(int i = 0; i < 20; i++){
            list.add(i);
        }
        assertEquals(a, list.getElement(19));
    }
    
    @Test
    public void addSecond(){
        Object a = 19;
        for(int i = 0; i < 20; i++){
            list.add(i, i);
        }
        assertEquals(a, list.getElement(19));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addAndRemoveBackwards(){
        for(int i = 0; i < 20; i++){
            list.add(i);
        }
        for(int i = 19; i >= 0; i--){
            list.remove(i);
        }
        list.getElement(0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addAndRemoveForward(){
        for(int i = 0; i < 20; i++){
            list.add(i);
        }
        for(int i = 0; i <= 19; i++){
            list.remove(i);
        }
        list.getElement(0);
    }
    
    @Test
    public void size(){
        for(int i = 0; i < 20; i++){
            list.add(i);
        }
        Object a = 20;
        assertEquals( a, list.size());
    }
    
    @Test
    public void addinEmptyList(){
        list.add(0, 0);
        list.add(0, 1);
        list.add(0, 2);
        
        Object a = 2;
        Object b = 1;
        Object c = 0;
        assertEquals(a, list.getElement(0));
        assertEquals(b, list.getElement(1));
        assertEquals(c, list.getElement(2));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void insertOutOfSize(){
        list.add(1, 2);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void insertOutOfArrayLength(){
        list.add(100, 1);
        list.add(100, 1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void setFirstPosition(){
        list.set(0, 2);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void getElementAtEmptyList(){
        list.getElement(0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addLowerZero(){
        list.add(-1,9);
    }
    
    @Test
    public void addInUsedFieldAndAllFieldsAreUsed(){
        for(int i = 0; i < 10; i++){
            list.add(0,0);
        }
        list.add(2,3);
        
        Object a = 3;
        assertEquals(a, list.getElement(2));
    }
    
    @Test
    public void resetOnSecondPosition(){
        list.add(1);
        list.add(2);
        list.set(1, 3);
        
        Object a = 3;
        assertEquals(a, list.getElement(1));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void getElementLowerZero(){
        list.add(0);
        list.getElement(-1);
    }
    
    @Test
    public void giveBackPositionOfContent(){
        list.add(1);
        int a = list.giveBackPositionOfContent(1);
        
        int b = 0;
        
        assertEquals(b, a);
    }
    
    @Test
    public void giveBackPositionLowerThanZero(){
        int a = list.giveBackPositionOfContent(-2);
        
        int b = -1;
        
        assertEquals(b, a);
    }
    
    @Test
    public void contentIsInList(){
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        
        assertTrue(list.contentIsInList(1));        
        
    }
    
    @Test
    public void contentIsNotInList(){
        list.add(2);
        list.add(3);
        
        assertTrue(!list.contentIsInList(1));
    }
}
