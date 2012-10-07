/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author annahietanen
 */
public class FibonacciTest {
    
 
    
    Fibonacci heap;
    
    @Before
    public void setUp() {
        heap = new Fibonacci();
    }

    @Test
    public void insertTest(){
        long start = System.currentTimeMillis();
        for (int i = 100000; i >= 0; i--){
            heap.insert(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent adding 100 000: " + (end-start)*1.0 + "ms.");
        
        long deleteStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            heap.extract_min();
        }
        long deleteEnd = System.currentTimeMillis();
        System.out.println("Time spent deleting 100 000: " + (deleteEnd-deleteStart)*1.0 + "ms.");
    }
    
    @Test
    public void smallInsertTest(){
        Fibonacci Fiheap = new Fibonacci();
        for (int i = 4; i != 0; i--) {
            Fiheap.insert(i);
        }
        Fibonacci Fiheap2 = new Fibonacci();
        for (int j = 4; j != 0; j--) {
            Fiheap2.insert(j);
        }
        
        Fibonacci union = Fiheap.union(Fiheap, Fiheap2);
        assertEquals(union.extract_min().value, 1);
        assertEquals(union.extract_min().value, 1);
        assertEquals(union.extract_min().value, 2);
        assertEquals(union.extract_min().value, 2);

    }
    //Lisätään kekoon 1000000 alkiota ja poistetaan ne.
    @Test
    public void insertAndDeleteTest(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++){
            heap.insert(i);
        }
        for (int i = 0; i < 1000000; i++){
            assertEquals(heap.extract_min().value, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent adding and deleteting 1000000: " + (end-start*1.0) + "ms");
    }
    
    //Lisätään kekoon 10000 alkiota käänteisessä järjestyksessä ja poistetaan ne.
    @Test
    public void reverseInsertAndDeleteTest(){
        long start = System.currentTimeMillis();
        for (int i = 1000000; i >= 0; i--){
            heap.insert(i);
        }
        for (int i = 0; i < 1000000; i++){
            assertEquals(heap.extract_min().value, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent in inserting and deleting 1000000 in reverse: " + (end-start*1.0) + "ms");
    }
    
    
}