/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author annahietanen
 */
public class Binomialnode {
    
    int value;
    int degree;
    Binomialnode sibling;
    Binomialnode parent;
    Binomialnode child;

    /**
     *
     */
    public Binomialnode() {
    }

    /**
     *
     * @param value
     */
    public Binomialnode(int value) {
        this.value = value;
    }

    /**
     *
     * @param depth
     * @return 
     */
    public String printTree(int depth) {
        String print = "";

        for (int i = 0; i < depth; i++) {
            print = print + "  ";
        }
        print = print + toString() + "\n";

        Binomialnode a = child;
        while (a != null) {
            print = print + a.printTree(depth + 1);
            a = a.sibling;
        }

        return print;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return ("Value: " + value + " degree: " + degree);
    }
}

