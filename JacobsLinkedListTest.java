import junit.framework.Assert;
import org.junit.Test;
import java.util.Random;

import static org.junit.Assert.*;

public class JacobsLinkedListTest {

    @Test
    public void addClearTests(){

        JacobsLinkedList jll = new <Integer> JacobsLinkedList();
        assertTrue(jll.isEmpty());

        for (int i =0; i<200; i++){
            jll.add(i*100);
            assertEquals(100*i, jll.get(i));
        }
        assertEquals(jll.size(),200);
        assertTrue(!jll.isEmpty());
        for (int i =0; i<200; i++){
            assertEquals(100*i, jll.get(i));
        }


        for (int i=0; i<100; i++){
            jll.add(5000+i,50+i);
            assertEquals(5000+i, jll.get(50+i));
        }
        assertEquals(jll.size(),300);
        for (int i=0; i<100; i++){
            assertEquals(5000+i, jll.get(50+i));
        }
        jll.clear();
        assertEquals(0,jll.size());
        assertNull(jll.get(5000));

        for (int i=0; i<100; i++) {
            jll.add(5000 + i, 50 + i);
            assertEquals(5000 + i, jll.get(i));
        }

        for (int i=0; i<100; i++) {
            jll.add(20* i);
            assertEquals(20*i, jll.get(100+i));
        }

        //check iteration is supported
        for (Object item:jll){
            assertNotNull(item);
        }



        JacobsLinkedList otherjll = new <Character> JacobsLinkedList('a');
        for (int i =0; i<30; i++){
            otherjll.add((char) ('b'+ i));
        }

        char check = 'a';
        for (int i =0; i<30; i++){
            assertEquals((char) (check+i), otherjll.get(i));
            if (i!=29) {
                assertNotEquals((char) (check + i + 1), otherjll.get(i));
            }
        }


    }

    @Test
    public void containsRemoveTests(){
        JacobsLinkedList jll = new <Integer>JacobsLinkedList();
        Integer[] randomInts = new Integer[500];
        for (int i=0; i<500;i++){
            int num = (int)(Math.random()*(i+1));
            randomInts[i] = num;
            jll.add(num);
        }

        for (int i=499; i>0;i--){
            assertTrue(jll.contains(randomInts[i]));
        }

        for (int i=0; i<250;i++){
            assertEquals(jll.remove(0),randomInts[i]);
        }
        assertEquals(jll.size(),250);




        JacobsLinkedList ojll = new<Integer>JacobsLinkedList();
        for (int i =0; i<500; i++){
            ojll.add(i);
        }

        int[] notPresent = new int[500];
        for (int i = 0; i<500; i++){
            notPresent[i]=i+500;
        }

        for (int i = 0; i<500; i++){
            assertFalse(ojll.contains(notPresent[i]));
            System.out.println(i);
        }


        for (int i=0; i<250;i++){
            assertEquals(ojll.remove(0),i);
        }
        assertEquals(ojll.size(),250);

        for (int i=0; i<250;i++){
            assertFalse(ojll.contains(i));
        }

        for (int i = 0; i<250; i++){
            assertEquals(ojll.get(i),250+i);
            assertTrue(ojll.contains(250+i));
        }
    }

    @Test
    public void toArrayTest(){
        String[] items = new String[]{"These", "are", "items", "to", "be", "added", "to",
                                        "list", "and", "then", "placed","in","new","array"};
        JacobsLinkedList stringList = new <String>JacobsLinkedList();

        for (String item:items){
            stringList.add(item);
        }
        String[] testArray = (String[]) stringList.toArray(new String[]{"a"});
        assertEquals(testArray.length, stringList.size());

        for (int i = 0; i<stringList.size(); i++){
            assertEquals(testArray[i], items[i]);
            assertEquals(testArray[i], stringList.get(i));
        }

        JacobsLinkedList intList = new <Integer>JacobsLinkedList();
        Integer[] randomInts = new Integer[500];
        for (int i=0; i<500;i++){
            int num = (int)(Math.random()*i);
            randomInts[i] = num;
            intList.add(num);
        }

        Integer[] intArray = (Integer[]) intList.toArray(new Integer[]{1});

        for (int i=0; i<500;i++) {
            assertEquals(randomInts[i], intArray[i]);
        }
    }

}
