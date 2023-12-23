import java.io.*;
import java.util.Iterator;

/**
 * Anagram Finder that takes in a word, a file containing words, and a data structure and returns a list of anagrams
 * in lexicographical order
 * Usage: java AnagramFinder <word> <dictionary file> <bst|avl|hash>
 * @author David Eyal
 * @version 1.0 December 16 2023
 */

public class AnagramFinder {
    public static void main(String[] args) throws IOException {
        //checks argument length
        if(args.length != 3){
            System.err.println("Usage: java AnagramFinder <word> <dictionary file> <bst|avl|hash>\n");
            System.err.println();
            System.exit(1);
        }
        //checks if BST is to be used
        if((args[2].equals("bst"))){
            MyMap<String, MyList<String>> map = new BSTMap<>();
            map = getMap(map,args[1]);
            printAnagrams(map,args[0]);
        }
        // Checks if an AVL Tree is to be used
        else if((args[2].equals("avl"))){
            MyMap<String, MyList<String>> map = new AVLTreeMap<>();
            map = getMap(map, args[1]);
            printAnagrams(map,args[0]);
        }
        //Checks if a HashMap is to be used
        else if (args[2].equals("hash")){
            MyMap<String, MyList<String>> map = new MyHashMap<>();
            map = getMap(map, args[1]);
            printAnagrams(map,args[0]);
        }
        //If the data structure supplied is not bst, avl or hash it prints an error message
        else{
            System.err.println("Error: Invalid data structure '" + args[2] + "' received.");
            System.err.println();
            System.exit(1);
        }
    }

    /**
     * Takes in a map of Key Value pairs consisting of a sorted word key and a list of anagrams
     * Finds the anagrams of the original arguments, and prints them out
     * @param map
     * @param args
     */
    public static void printAnagrams(MyMap<String,MyList<String>> map, String args){
        try{
            MyList<String> f = map.get(String.copyValueOf(insertionSort(args)));
            Iterator<String> b = f.iterator();
            int count = 0;
            //instantiates a String array of the list size
            String[] anagrams = new String[f.size()];
            for(int i = 0; i<anagrams.length;i++){
                anagrams[i] = "";
            }
            //Inserts all the anagrams (except for the word itself) into the array
            while(b.hasNext()){
                String z = b.next();
                if(!z.equals(args)){
                    anagrams[count] = z;
                    count++;
                }}
            //if there are no anagrams besides the word itself, print no anagrams
            if(count == 0){
                System.out.println("No anagrams found.");
                System.out.println();
            }
            //Sorts the anagrams and prints them out
            anagrams = insertionSort(anagrams);
            for(int i = 0; i<anagrams.length;i++){
                if(!anagrams[i].isEmpty()){
                    System.out.println(anagrams[i]);}
            }
        }
        //If the supplied word is not in the dictionary, and has no anagrams, a NullPointer will be thrown
        //as no linkedlist was created
        catch (NullPointerException e) {
            System.out.println("No anagrams found.");
            System.out.println();
        }
    }

    /**
     * Takes in an empty map of any data structure and the original file and builds a map
     * @param map
     * @param file
     * @return Map of all words in the file
     * @throws IOException
     */
    public static MyMap<String, MyList<String>> getMap(MyMap<String, MyList<String>> map, String file) throws IOException {
        BufferedReader dictionary = null;
        // Found documentation for BufferedReader
        // https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
        // Tries opening the file, catches an exception to print the error message
        try {
            dictionary  = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Error: Cannot open file '" + file + "' for input.");
            System.err.println();
            System.exit(1);
        }
        while(dictionary.ready()){
            //Reads a line, sorts it, and checks if it is in the AVL Tree
            //If its anagram is there, it appends it to the linked list
            //If not, it creates a linked list and inserts the anagram key and the linked list into the AVL Tree
            try{
                String x = dictionary.readLine();
                String y = String.copyValueOf(insertionSort(x));
                if(map.get(y) != null){
                    map.get(y).add(x);
                }
                else{
                    MyList<String> z = new MyLinkedList<String>();
                    z.add(x);
                    map.put(y,z);
                }}
            catch (IOException e){
                System.err.println("Error: An I/O error occurred reading '" + file + "'.");
                System.err.println();
            }}
        return map;}
    /**
     * Insertion sort method to sort a word in alphabetical order
     * @param word
     * @return Sorted char array
     */
    public static char[] insertionSort(String word){
        word = word.toLowerCase();
        char[] sorted = word.toCharArray();
        for (int i = 1; i<sorted.length; i++){
            int k, current = (int) sorted[i];
            for(k = i-1; k>=0 && (int)sorted[k] > current; k--){
                sorted[k+1] = sorted[k];}
            sorted[k+1] = (char) current;}
        return sorted;}
    /**
     * Takes in an unsorted list of anagrams and sorts them using insertionSort
     * @param anagrams
     * @return Sorted list of anagrams
     */
    public static String[] insertionSort(String[] anagrams){
        for (int i = 1; i<anagrams.length;i++){
            int k;
            String current = anagrams[i];
            for (k = i-1;k>=0 && anagrams[k].compareTo(current) > 0; k--){
                anagrams[k+1] = anagrams[k];}
            anagrams[k+1] = current;}
        return anagrams;}

























}