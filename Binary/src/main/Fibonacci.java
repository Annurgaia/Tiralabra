/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author annahietanen
 */
public class Fibonacci {
    private static final double logPhi = 1.0 / Math.log((1.0 + Math.sqrt(5.0)) / 2.0);
    Fibonaccinode min;
    int size;

public Fibonacci() {
        min = null;
        size = 0;
}

public Fibonacci(Fibonaccinode node) {
        min = node;
        size = 1;
}
public void insert(int value) {
    
}
public void link(Fibonaccinode y, Fibonaccinode x) {
    
}
//combining two heaps
 public Fibonacci union(Fibonacci H1, Fibonacci H2) {
      Fibonacci H = new Fibonacci();
     return H;
 }
 public Fibonaccinode extract_min() {
     Fibonaccinode f = min;
     return f;
 }
public void delete(Fibonaccinode x){
    
}
}
