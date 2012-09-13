package main;


import org.junit.*;


/**
 *
 * @author annahietanen
 */
public class BinomialTest {
    
    Binomial heap;
    public BinomialTest() {
    }
    
    @BeforeClass
    public static void setUpClass()throws Exception { 
    }
    
    @AfterClass
    public static void tearDownClass()throws Exception { 
    }
    
    @Before
    public void setUp() {
        heap = new Binomial();
    
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void heapTest(){
     long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++){
            heap.insert(i);
        }
        
     
        long end = System.currentTimeMillis();
        long totaltime = end - start;
        System.out.println("Time spent: " + totaltime + "ms = " + (totaltime*1.0)/1000 + "s"); 
        
}
    public void heapTest2(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++){
            heap.insert(i);
        }
        for (int i = 0; i < 10; i++){
            heap.extract_min();
        }
     
        long end = System.currentTimeMillis();
        long totaltime = end - start;
        System.out.println("Time spent: " + totaltime + "ms = " + (totaltime*1.0)/1000 + "s");  
}
}
