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
public class BinaryTest {
    Binary binaryheap;
    
    public BinaryTest() {
    }
  
    @Before
    public void setUp() {
     binaryheap = new Binary();
    }
    
   @Test
    public void insertTest(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            binaryheap.insert(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent adding 100 000: " + (end-start)*1.0 + "ms.");
        
        long deleteStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            binaryheap.delete();
        }
        long deleteEnd = System.currentTimeMillis();
        System.out.println("Time spent deleting 100 000: " + (deleteEnd-deleteStart)*1.0 + "ms.");
        
    }
    
    @Test
    public void heapTest(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++){
            binaryheap.insert(i);
        }
        for (int i = 0; i < 10000000; i++){
            binaryheap.delete();
            
        }
        long end = System.currentTimeMillis();
        long totaltime = end - start;
        System.out.println("Time spent: " + totaltime + "ms = " + (totaltime*1.0)/1000 + "s");      
      
    }
}
