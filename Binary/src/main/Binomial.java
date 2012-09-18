/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import main.BinomialNode;
import main.BinomialNode;

/**
 *
 * @author annahietanen
 */

  
public class Binomial {
 
    BinomialNode root;
    int size;

  public Binomial() {
      
  }
    public Binomial(BinomialNode node, int size) {
        this.size = size;
        this.root = node;
    }
//Luodaan uusi keko, joka sisältää uuden solmun. Unionin avulla yhdistetään alkupräiseen kekoon.
    public void insert(int value) {
        //uusi keko
        Binomial h = new Binomial();
        BinomialNode node = new BinomialNode(value);
        node.parent = null;
        node.child = null;
        node.sibling = null;
        node.degree = 0;
        h.root = node;
        Binomial newer = union(h, this);
        this.root = newer.root;
        size++;
    }

    //keon pienimmän arvon etsintä
    public BinomialNode find_min() {
        int value = Integer.MAX_VALUE;
        BinomialNode helpnode = root;
        BinomialNode min = null;
        while (helpnode != null) {
            if (helpnode.value < value) {
                value = helpnode.value;
                min = helpnode;
            }
            helpnode = helpnode.sibling;
        }
        return min;
    }
     public void decreaseKey(BinomialNode x, int k) {
        if (k > x.value) {
            System.out.println("You gave a greater value! The value of the Binomialnode is: " + x.value);
            return;
        }
        x.value = k;
        BinomialNode y = x;
        BinomialNode z = y.parent;

        while ((z != null) && y.value < x.value) {
            int helpvalue = y.value;
            y.value = x.value;
            x.value = helpvalue;
            y = z;
            z = y.parent;
        }
    }
     //pienimmän arvon poisto
     public void extract_min() {
        Binomial helpheap = new Binomial();
        BinomialNode min = find_min();
        BinomialNode temp = min.child;
        BinomialNode current = root;
        BinomialNode help = root;
        //jos juuri ei ole pieni, etsitään sisarista pienintä arvoa
        if (root.value != min.value) {
            while (help.sibling.value != min.value) {
                help = help.sibling;
            }
            help = help.sibling.sibling;
        }
        //jos juuri on pienin, se poistetaan ja sisaresta tulee uusi juuri
        if (root.value == min.value) {
            root = root.sibling;
        }

        if (min.child != null) {
            /*
             * Vaihdetaan alipuun järjestys
             */
            while (temp != null) {
                BinomialNode next = temp.sibling;
                temp.sibling = helpheap.root;
                helpheap.root = temp;
                temp = next;
            }

            Binomial newer = union(this, helpheap);
            this.root = newer.root;
        }
    }
     //luodaan kahden binomisolmun välille parent-child-yhteys
      public void binomial_link(BinomialNode y, BinomialNode z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }
      //yhdistetään kaksi kekoa toisiinsa mergellä
public Binomial union(Binomial h1, Binomial h2) {
        Binomial h = new Binomial();
        //valitaan juuri
        h.root = merge(h1, h2);
        if (h.root == null) {
            return h;
        }
        BinomialNode prev_x = null;
        BinomialNode x = h.root;
        BinomialNode next_x = x.sibling;
        //yhdistetään keot ja tarkisteetaan ettei ole kahta samanasteista puuta
        while (next_x != null) {
            if (x.degree != next_x.degree || (next_x.sibling != null) && (next_x.sibling.degree == x.degree)) {
                prev_x = x;
                x = next_x;
            } else {
                if (x.value <= next_x.value) {
                    x.sibling = next_x.sibling;
                    binomial_link(next_x, x);
                } else {
                    if (prev_x == null) {
                        h.root = next_x;
                    } else {
                        prev_x.sibling = next_x;
                    }
                    binomial_link(x, next_x);
                    x = next_x;
                }
            }
            next_x = x.sibling;
        }
        return h;
    }

 BinomialNode merge(Binomial h1, Binomial h2) {
        BinomialNode node1 = null;
        BinomialNode node2 = null;

        if (h1 != null && h1.root != null) {
            node1 = h1.root;
        }
        if (h2 != null && h2.root != null) {
            node2 = h2.root;
        }
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }
        BinomialNode h;
        if (node1.degree < node2.degree) {
            h = node1;
            node1 = node1.sibling;
        } else {
            h = node2;
            node2 = node2.sibling;
        }
        BinomialNode curr = h;
        while (node1 != null && node2 != null) {
            if (node1.degree < node2.degree) {
                curr.sibling = node1;
                curr = node1;
                node1 = node1.sibling;
            } else {
                curr.sibling = node2;
                curr = node2;
                node2 = node2.sibling;
            }
        }
        if (node1 == null) {
            curr.sibling = node2;
        } else {
            curr.sibling = node1;
        }
        return h;
    }
 //printataan puu
public String toString() {
        String result = "";

        BinomialNode x = root;
        while (x != null) {
            result += x.printTree(0);
            x = x.sibling;
        }
        return result;
    }
//main
    public static void main(String[] args) {

        Binomial binomialheap = new Binomial();
        for (int i = 10000; i != 0; i--) {
            binomialheap.insert(i);
        }
        binomialheap.extract_min();
        binomialheap.extract_min();
        binomialheap.extract_min();
        System.out.println(binomialheap.toString());
    }
}
