/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author annahietanen
 */
public class Binary {

    int size;
    Binarynode root;

    public Binary() {
        size = 0;
    }
    
   
    /**
     *Lisää uuden solmun kekoon ja vaihtaa keon binäärimuotoon. Jos keko loppuun 0, lisätään solmu vasemmalle, muuten oikealle.
     * @param value
     */
    public void insert(int value) {
        Binarynode node = new Binarynode(value);
        size++;
        if (size == 1) {
            root = node;
            return;
        }
        Binarynode helpnode = root;
        String binary = Integer.toBinaryString(size);
        for (int i = 1; i < binary.length() - 1; i++) {
            if (binary.charAt(i) == '0') {
                helpnode = helpnode.left;
            } else {
                helpnode = helpnode.right;
            }
        }
        if (binary.charAt(binary.length() - 1) == '0') {
            helpnode.left = node;
            node.parent = helpnode;
        } else {
            helpnode.right = node;
            node.parent = helpnode;
        }
        heap_up(node);
    }

    
     /**
     *Poistaa solmun keosta. Keon järjestys tarkistetaan lopuksi heap_down().
     * @return
     */
    public int delete() {
        if (size == 1) {
            int helpvalue = root.value;
            root = null;
            return helpvalue;
        }
        int returnedvalue = root.value;

        Binarynode help = root;
        help.value = root.value;
        String binary = Integer.toBinaryString(size);
        for (int i = 1; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                help = help.left;
            } else {
                help = help.right;
            }
        }
        root.value = help.value;
        if (binary.charAt(binary.length() - 1) == '0') {
            help.parent.left = null;
        } else {
            help.parent.right = null;
        }

        size--;
        heap_down();
        return returnedvalue;

    }
   
      /**
     *Printataan keko.
     * @param root
     */
    public void print_heap(Binarynode root) {
        System.out.println(root.value);
        if (root.left != null) {
            print_heap(root.left);
        }
        if (root.right != null) {
            print_heap(root.right);
        }
    }

      //Niin kauan kuin solmun avain on pienempi kuin vanhempansa, solmun alkio ja solmun vanhemman alkio vaihtavat toistensa kanssa paikkaa.
private void heap_up(Binarynode node) {
        if (node == root) {
            return;
        }

        int valuehelper;
        if (node.parent.value > node.value) {
            valuehelper = node.value;
            node.value = node.parent.value;
            node.parent.value = valuehelper;
        } else {
            return;
        }
        heap_up(node.parent);

    }

//Väärässä paikassa oleva alkio siirretään oikealle paikalle. Jos solmun avain on suurempi kuin vähintään toinen sen lasten avaimista, siirretään alaspäin. 
// Kun alkio löytää paikkansa lopetetaan siirto alaspäin ja keon järjestys on valmis.
private void heap_down() {
        Binarynode node = root;
        Binarynode help;
        int valuehelper;
        while (true) {
            if (node.right == null || node.left.value > node.right.value) {
                help = node.right;
            } else {
                help = node.left;
            }

            if (help != null && help.value < node.value) {
                valuehelper = node.value;
                node.value = help.value;
                help.value = valuehelper;
            } else {
                return;
            }
            node = help;
        }
    }
}
    