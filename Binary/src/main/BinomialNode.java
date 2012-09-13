/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author annahietanen
 */
public class BinomialNode {
    
int value;
    int degree;
    BinomialNode sibling;
    BinomialNode parent;
    BinomialNode child;

    public BinomialNode() {
    }

    public BinomialNode(int arvo) {
        this.value = value;
    }

    public String printTree(int depth) {
        String print = "";

        for (int i = 0; i < depth; i++) {
            print = print + "  ";
        }
        print = print + toString() + "\n";

        BinomialNode a = child;
        while (a != null) {
            print = print + a.printTree(depth + 1);
            a = a.sibling;
        }

        return print;
    }

    public String toString() {
        return ("Value: " + value + " degree: " + degree);
    }
}

