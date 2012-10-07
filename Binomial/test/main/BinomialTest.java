package main;


import org.junit.*;


/**
 *
 * @author annahietanen
 */
public class BinomialTest {
    
    Binomial heap;
    
    @Before
    public void setUp() {
        heap = new Binomial();
    }
    
    @Test
    public void insertTest(){
        long start = System.currentTimeMillis();
        for  (int i = 0; i < 100000; i++){
            heap.insert(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent inserting 100 000: " + (end-start)*1.0 + "ms.");
        
        long deleteStart = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            heap.extract_min();
        }
        long deleteEnd = System.currentTimeMillis();
        System.out.println("Time spent deleting 100 000: " + (deleteEnd-deleteStart)*1.0 + "ms.");
    }
    
    @Test
    public void heapTest(){
     long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++){
            heap.insert(i);
        }
        
     
        long end = System.currentTimeMillis();
        long totaltime = end - start;
        System.out.println("Time spent adding 1 000 000: " + totaltime + "ms = " + (totaltime*1.0)/1000 + "s"); 
        
}
    @Test
    public void heapTest2(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++){
            heap.insert(i);
        }
        for (int i = 0; i < 10; i++){
            heap.extract_min();
        }
     
        long end = System.currentTimeMillis();
        long totaltime = end - start;
        System.out.println("Time spent deleting 1 000 000: " + totaltime + "ms = " + (totaltime*1.0)/1000 + "s");  
}
}
