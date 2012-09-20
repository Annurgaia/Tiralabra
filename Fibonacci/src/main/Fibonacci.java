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

        Fibonaccinode x = new Fibonaccinode(value);
        x.degree = 0;
        x.parent = null;
        x.child = null;
        x.left = x;
        x.right = x;
        if (min != null) {            
            //solmut linkitetään toisiinsa            
            x.left = min;
            x.right = min.right;
            min.right = x;
            x.right.left = x;
            if (x.value < min.value) {
                min = x;
            }
        } else {
            min = x;
        }
        size++;
    }       

public void link(Fibonaccinode y, Fibonaccinode x) {
     y.left.right = y.right;
        y.right.left = y.left;
        y.parent = x;
        if (x.child == null) {
            x.child = y;
            y.right = y;
            y.left = y;
        } else {
            y.left = x.child;
            y.right = x.child.right;
            x.child.right = y;
            y.right.left = y;
        }
        x.degree++;
        y.mark = false;
    }    

//combining two heaps
 public Fibonacci union(Fibonacci H1, Fibonacci H2) {
      Fibonacci H = new Fibonacci();        
        //tarkistetaan, ettei kummankaan keon minimi ole null        
        if ((H1 != null) && (H2 != null)) {            
            //tehdään alustavasti H1:n minimistä H:n minimi            
            H.min = H1.min;
            if (H.min != null) {
                if (H2.min != null) {                    
                    //lisätään H2 uuteen kekoon laittamalla sen alkiot oikeille paikoille                    
                    H.min.right.left = H2.min.left;
                    H2.min.left.right = H.min.right;
                    H.min.right = H2.min;
                    H2.min.left = H.min;                    
                    //tarkistetaan, onko H2 minimi pienempi kuin H1:n minimi                    
                    if (H2.min.value < H1.min.value) {
                        H.min = H2.min;
                    }
                }
            } else {
                H.min = H2.min;
            }            
            //kasvatetaan uuden keon kokoa            
            H.size = H1.size + H2.size;
        }
        return H;
    }
 
 public Fibonaccinode extract_min() {
     Fibonaccinode z = min;
        if (z != null) {
            int children = z.degree;
            Fibonaccinode x = z.child;
            Fibonaccinode temporary;
            while (children > 0) {
                temporary = x.right;
                x.left.right = x.right;
                x.right.left = x.left;
                x.left = min;
                x.right = min.right;
                min.right = x;
                x.right.left = x;
                x.parent = null;
                x = temporary;
                children--;
            }
            z.left.right = z.right;
            z.right.left = z.left;
            if (z == z.right) {
                min = null;
            } else {
                min = z.right;
                consolidate();
            }
            size--;
        }
        return z;
    
 }
 public void consolidate() {
        int tableSize = ((int) Math.floor(Math.log(size) * logPhi)) + 1;
        Fibonaccinode[] table = new Fibonaccinode[tableSize];
        for (int j = 0; j < tableSize; j++) {
            table[j] = null;
        }
        int roots = 0;
        Fibonaccinode x = min;
        if (x != null) {
            roots++;
            x = x.right;
            while (x != min) {
                roots++;
                x = x.right;
            }
        }
        for (int i = 0; i < roots; i++) {
            int d = x.degree;
            Fibonaccinode next = x.right;            
            // Käydään taulukko läpi etsien onko samankokoisia            
            for (Fibonaccinode n : table) {
                Fibonaccinode y = table[d];
                if (y == null) {
                    break;
                }
                if (x.value > y.value) {
                    Fibonaccinode temp = y;
                    y = x;
                    x = temp;
                }
                link(y, x);
                table[d] = null;
                d++;
            }
            table[d] = x;
            x = next;
        }
        min = null;
        for (int i = 0; i < tableSize; i++) {
            Fibonaccinode y = table[i];
            if (y == null) {
                continue;
            }
            if (min != null) {
                y.left.right = y.right;
                y.right.left = y.left;
                y.left = min;
                y.right = min.right;
                min.right = y;
                y.right.left = y;
                if (y.value < min.value) {
                    min = y;
                }
            } else {
                min = y;
            }
        }
    }     
 public void decreaseKey(Fibonaccinode x, int k) {
        if (k > x.value) {
            throw new IllegalArgumentException("You gave a bigger value!");
        }
        x.value = k;
        Fibonaccinode y = x.parent;
        if ((y != null) && (x.value < y.value)) {
            //Leikkaus
            cut(x, y);
            cascadingCut(y);
        }
        if (x.value < min.value) {
            min = x;
        }
    }

    protected void cascadingCut(Fibonaccinode y) {
        Fibonaccinode z = y.parent;
        if (z != null) {
            if (!y.mark) {
                //merkataan solmu
                y.mark = true;
            } else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    
protected void cut(Fibonaccinode x, Fibonaccinode y) {
        x.left.right = x.right;
        x.right.left = x.left;
        y.degree--;
        if (y.child == x) {
            y.child = x.right;
        }
        if (y.degree == 0) {
            y.child = null;
        }
        x.left = min;
        x.right = min.right;
        min.right = x;
        x.right.left = x;
        x.parent = null;
        x.mark = false;
    }    
    
    
    // poistetaan alkio kutsumalla metodeita decreaseKey ja extract_min  
    public void delete(Fibonaccinode x) {
        decreaseKey(x, Integer.MIN_VALUE);
        extract_min();
    }
}


