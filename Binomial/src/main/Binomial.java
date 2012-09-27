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

    
    /**
     *Luodaan uusi keko, joka sisältää uuden solmun. Unionin avulla yhdistetään alkuperäiseen kekoon.
     * @param value
     */
    public void insert(int value) {
        //luodaan uusi heap
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

    
    /**
     *etsitään keon pienin arvo
     * @return
     */
    public Binomialnode find_min() {
        //arvolle annetaan mahdollisimman suurin arvo
        int value = Integer.MAX_VALUE;
        Binomialnode helpnode = root;
        Binomialnode min = null;
        //käydään läpi keko solmu solmulta, ja pidetään yllä keon pienintä arvoa muuttujan min avulla
        while (helpnode != null) {
            if (helpnode.value < value) {
                value = helpnode.value;
                min = helpnode;
            }
            helpnode = helpnode.sibling;
        }
        return min;
    }

  
    /**
     *poistetaan pienin arvo keosta 
     */
    public void extract_min() {
        Binomial helpheap = new Binomial();
        Binomialnode min = find_min();
        Binomialnode temp = min.child;
        Binomialnode curr = root;
        Binomialnode help = root;

        //jos juuri ei ole minimi, etsitään sen sisarista pienintä arvoa
        if (root.value != min.value) {
            while (help.sibling.value != min.value) {
                help = help.sibling;
            }
            help = help.sibling.sibling;
        }
        //jos juuri on pienin value, poistetaan se ja tehdään sen sisaresta uusi juuri.
        if (root.value == min.value) {
            root = root.sibling;
        }

        //tapaus, jossa pienemmällä arvolla on lapsia. järjestetään keko apukeon avulla
        if (min.child != null) {
            /*
             * Vaihdetaan alipuun järjestys
             */
            while (temp != null) {
                Binomialnode next = temp.sibling;
                temp.sibling = helpheap.root;
                helpheap.root = temp;
                temp = next;
            }

            Binomial new1 = union(this, helpheap);
            this.root = new1.root;
        }
    }

    
    /**
     *luodaan kahden annetun binomisolmun välille parent-child yhteys
     * @param y
     * @param z
     */
    public void link(Binomialnode y, Binomialnode z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }
   

    /**
     *yhdistetään kaksi kekoa toisiinsa mergeä apuna käyttäen
     * @param k1
     * @param k2
     * @return
     */
    public Binomial union(Binomial k1, Binomial k2) {
        Binomial k = new Binomial();
        //valitaan juuri mergen avulla
        k.root = merge(k1, k2);
        if (k.root == null) {
            return k;
        }
        Binomialnode previous = null;
        Binomialnode current = k.root;
        Binomialnode next = current.sibling;
        //yhdistetään keot tarkistamalla, että keossa ei ole kahta samanasteista puuta.
        while (next != null) {
            if (current.degree != next.degree || (next.sibling != null && next.sibling.degree == current.degree)) {
                previous = current;
                current = next;
            } else {
                if (current.value <= next.value) {
                    current.sibling = next.sibling;
                    link(next, current);
                } else {
                    if (previous == null) {
                        k.root = next;
                    } else {
                        previous.sibling = next;
                    }
                    link(current, next);
                    current = next;
                }
            }
            next = current.sibling;
        }
        return k;
    }

    private static Binomialnode merge(Binomial heap1, Binomial heap2) {
        //Jos toisen juuri on tyhjä, palautetaan suoraan toinen
        if (heap1.root == null) {
            return heap2.root;
        } else if (heap2.root == null) {
            return heap1.root;
        }
        //Kumpikaan juurilista ei siis ole tyhjä. Käydään molemmat läpi käyttäen aina pienimmän arvon omaavaa solmua.
        Binomialnode root;		  
        Binomialnode last;		  
        Binomialnode next1 = heap1.root,
                next2 = heap2.root; 
        if (heap1.root.value <= heap2.root.value) {
            root = heap1.root;
            next1 = next1.sibling;
        } else {
            root = heap2.root;
            next2 = next2.sibling;
        }
        last = root;
        //Käy molemmat juurilistat läpi kunnes toinen loppuu.
        while (next1 != null && next2 != null) {
            if (next1.value <= next2.value) {
                last.sibling = next1;
                next1 = next1.sibling;
            } else {
                last.sibling = next2;
                next2 = next2.sibling;
            }

            last = last.sibling;
        }

        //Nyt toinen juurilista on loppunut. Jatketaan jäljellä olevasta juurilistasta loput listaan.
        if (next1 != null) {
            last.sibling = next1;
        } else {
            last.sibling = next2;
        }
        return root;

    }

   
    /**
     *tulostetaan puu
     * @return
     */
    public String toString() {
        String print = "";

        Binomialnode x = root;
        while (x != null) {
            print = print + x.printTree(0);
            x = x.sibling;
        }
        return print;
    }
 

    /**
     *ohjelman main
     * @param args
     */
    public static void main(String[] args) {

        Binomial binomikeko = new Binomial();
        for (int i = 10; i != 0; i--) {
            binomikeko.insert(i);
        }
        binomikeko.extract_min();
        binomikeko.extract_min();
        binomikeko.extract_min();
        System.out.println(binomikeko.toString());
    }
}

