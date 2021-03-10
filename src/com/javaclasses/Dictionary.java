package com.javaclasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.javaclasses.Dictionary.Bucket.Node; 

public class Dictionary {
    private int Prime_number = 1319; 
    final private Bucket[] Bucket_array;
    public Dictionary() {
        this.Prime_number = Prime_number;

        Bucket_array = new Bucket[Prime_number];
        for (int i = 0; i < Prime_number; i++) {
        	Bucket_array[i] = new Bucket();
        }
    }

    private int hash(String hash_key) {
        return (hash_key.hashCode() & 0x7fffffff) % Prime_number;
    }

    // hash() for bucket selection and put .
    public void add(String add_key) {
    	Bucket_array[hash(add_key)].put(add_key);
    }

    //call hash() to serach  bucket and get from that location,too.
    public boolean contains(String i) {
    	i = i.toLowerCase();
        return Bucket_array[hash(i)].get(i);
    }

    public void build(String FP) {
        try {
            BufferedReader BF = new BufferedReader(new FileReader(FP));
            String line_num;
            while ((line_num = BF.readLine()) != null) {
                add(line_num);
            }
        } catch (IOException exc) {
        	exc.printStackTrace();
        }

    }
    //helps for unit test 
    public String[] getRandomEntries(int number){
        String[] toReturn = new String[number];
        for (int i = 0; i < number; i++){
         
            Node n = Bucket_array[(int)Math.random()*Prime_number].first;
            int random_number = (int)Math.random()*(int)Math.sqrt(number);

            for(int j = 0; j<random_number && n.next_link!= null; j++) n = n.next_link;
            toReturn[i]=n.word;


        }
        return toReturn;
    }

    class Bucket {

        private Node first;

        public boolean get(String input) {         
            Node next = first;
            while (next != null) {
                if (next.word.equals(input)) {
                    return true;
                }
                next = next.next_link;
            }
            return false;
        }

        public void put(String putkey) {
            for (Node curr = first; curr != null; curr = curr.next_link) {
                if (putkey.equals(curr.word)) {
                    return;                     //search hit: return
                }
            }
            first = new Node(putkey, first); //search miss: add new node
        }

        class Node {

            String word;
            Node next_link;

            public Node(String key, Node next_link) {
                this.word = key;
                this.next_link = next_link;
            }

        }

    }
}