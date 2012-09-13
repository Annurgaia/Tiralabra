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
  
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception{
    }
    
    @Before
    public void setUp() {
     binaryheap = new Binary();
    }
    
    @After
    public void tearDown() {
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
