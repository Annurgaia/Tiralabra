/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


/**
 *
 * @author annahietanen
 */

  
public class Binomial {

    Binomialnode root;
    int size;

    public Binomial() {
    }

    public Binomial(Binomialnode node, int size) {
        this.size = size;
        this.root = node;
    }

//Luodaan uusi keko, joka sisältää uuden solmun. Unionin avulla yhdistetään alkuperäiseen kekoon.
    public void insert(int value) {
        Binomial heap1 = new Binomial();
        Binomialnode node = new Binomialnode(value);

        node.parent = null;
        node.child = null;
        node.sibling = null;
        node.degree = 0;
        heap1.root = node;
        Binomial heap2 = union(heap1, this);
        this.root = heap2.root;
        size++;
    }

    //keon pienimmän arvon etsintä
    public Binomialnode find_min() {
        int value = Integer.MAX_VALUE;
        Binomialnode helpnode = root;
        Binomialnode min = null;
        while (helpnode != null) {
            if (helpnode.value < value) {
                value = helpnode.value;
                min = helpnode;
            }
            helpnode = helpnode.sibling;
        }
        return min;
    }
    public void extract_min() {
        Binomial helpheap = new Binomial();
        Binomialnode min = find_min();
        Binomialnode val = min.child;
        Binomialnode curr = root;
        Binomialnode help = root;

        //jos juuri ei ole minimi, etsitään sen sisaruksista pienintä arvoa
        if (root.value != min.value) {
            while (help.sibling.value != min.value) {
                help = help.sibling;
            }
            help = help.sibling.sibling;
        }
        //jos juuri on pienin value, poistetaan se ja tehdään sen sisaruksista uuden keon juuri.
        if (root.value == min.value) {
            root = root.sibling;
        }

        //tapaus, jossa pienemmällä arvolla on lapsia. järjestetään keko apukeon avulla
        if (min.child != null) {
            /*
             * Vaihdetaan alipuun järjestys
             */
            while (val != null) {
                Binomialnode next = val.sibling;
                val.sibling = helpheap.root;
                helpheap.root = val;
                val = next;
            }

            Binomial newheap = union(this, helpheap);
            this.root = newheap.root;
        }
    }
     public void link(Binomialnode y, Binomialnode z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }
     

     //luodaan kahden binomisolmun välille parent-child-yhteys
      public void binomial_link(Binomialnode y, Binomialnode z) {
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
        Binomialnode prev_x = null;
        Binomialnode x = h.root;
        Binomialnode next_x = x.sibling;
        //yhdistetään keot ja tarkistetaan ettei ole kahta samanasteista puuta
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

 Binomialnode merge(Binomial h1, Binomial h2) {
        Binomialnode node1 = null;
        Binomialnode node2 = null;

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
        Binomialnode h;
        if (node1.degree < node2.degree) {
            h = node1;
            node1 = node1.sibling;
        } else {
            h = node2;
            node2 = node2.sibling;
        }
        Binomialnode curr = h;
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

        Binomialnode x = root;
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
