package ListModul;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 *
 * @author cooperdebear
 */
@RunWith(Parameterized.class)
public class LinkedListTest {
    
    @Parameters
    public static Collection<Integer[]> data() {
        return Arrays.asList(new Integer[][] {     
            {0}, {199}, {20}, {3}, {45}, {5}, {1111}  
        });
    }
    
    private static LinkedList<Integer> list;
    
    private int input;
    
    public LinkedListTest(int input){
        this.input = input;
    }
    
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
        for(int i = 0; i <= input; i++){
            list.add(i);
        }
        assertEquals((Object) input, list.getElement(input));
    }
    
    @Test
    public void addSecond(){
        for(int i = 0; i <= input; i++){
            list.add(i, i);
        }
        assertEquals((Object) input, list.getElement(input));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addAndRemoveBackwards(){
        for(int i = 0; i <= input; i++){
            list.add(i);
        }
        for(int i = input; i >= 0; i--){
            list.remove(i);
        }
        list.getElement(0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addAndRemoveForward(){
        for(int i = 0; i <= input; i++){
            list.add(i);
        }
        for(int i = 0; i <= input; i++){
            list.remove(i);
        }
        list.getElement(0);
    }
    
    @Test
    public void size(){
        for(int i = 0; i <= input; i++){
            list.add(i);
        }
        assertEquals(input+1, list.size());
    }
    
    @Test
    public void addinEmptyList(){
        list.add(0, input);
        list.add(0, input+1);
        list.add(0, input+2);
        
        assertEquals((Object)(input+2), list.getElement(0));
        assertEquals((Object)(input+1), list.getElement(1));
        assertEquals((Object)(input), list.getElement(2));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void insertOutOfSize(){
        list.add(1, input);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void insertOutOfArrayLength(){
        list.add(100, input);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void setFirstPosition(){
        list.set(0, input);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void getElementAtEmptyList(){
        list.getElement(0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addLowerZero(){
        list.add(-1,input);
    }
    
    @Test
    public void addInUsedFieldAndAllFieldsAreUsed(){
        for(int i = 0; i < 10; i++){
            list.add(0,0);
        }
        list.add(2,input);
        
        assertEquals((Object) input, list.getElement(2));
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
    
    @Test (expected = IllegalArgumentException.class)
    public void quicksortEmptyList(){
        list.sort();
    }
    
    @Test
    public void quicksortFilledList(){
        list.add(1);
        list.add(5);
        list.add(3);
        
        list.sort();
        
        assertEquals(list.getElement(0),(Object) 1);
        assertEquals(list.getElement(1),(Object) 3);
        assertEquals(list.getElement(2),(Object) 5);
    }
    
    @Test
    public void quicksortTwoTimes(){
        list.add(1);
        list.add(5);
        list.add(3);
        list.add(120);
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(8);
        
        list.sort();
        list.sort();
        
        assertEquals(list.getElement(0),(Object) 1);
        assertEquals(list.getElement(1),(Object) 1);
        assertEquals(list.getElement(2),(Object) 3);
        assertEquals(list.getElement(3),(Object) 3);
        assertEquals(list.getElement(4),(Object) 5);
        assertEquals(list.getElement(5),(Object) 5);
        assertEquals(list.getElement(6),(Object) 5);
        assertEquals(list.getElement(7),(Object) 8);
        assertEquals(list.getElement(8),(Object) 120);
    }
}
