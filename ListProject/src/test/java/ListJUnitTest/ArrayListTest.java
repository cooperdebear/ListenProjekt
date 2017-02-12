package ListJUnitTest;

import static org.junit.Assert.assertEquals;
import ListModul.ArrayList;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;

/**
 *
 * @author cooperdebear
 */
public class ArrayListTest {
    
    private static ArrayList<Integer> list;
    
    @BeforeClass
    public static void createList(){
        list = new ArrayList<>();
    }
    
    @AfterClass
    public static void deleteList(){
        list = null;
    }
    
    @Before
    public void resetList(){
        list = new ArrayList<>();
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
    public void insert(){
        Object a = 19;
        list.add(19);
        for(int i = 0; i < 19; i++){
            list.insert(i, i);
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
}
