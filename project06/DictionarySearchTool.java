package project06;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TrieNode{
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }

} 

class Trie{
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode current = root;
        word = word.toLowerCase();

        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());

            current = current.children.get(ch);
        }

        current.isEndOfWord = true;
    }

    public boolean search(String word){
        TrieNode current = root;
        word = word.toLowerCase();

        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }

            current = current.children.get(ch);
        }

        return current.isEndOfWord;
    }

    public boolean startsWith(String prefix){
        TrieNode current = root;

        for (char ch : prefix.toLowerCase().toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }

            current = current.children.get(ch);
        }

        return true;
    }
}

public class DictionarySearchTool{
    public static void main(String [] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("application");
        trie.insert("apply");
        trie.insert("ball");
        trie.insert("bat");
        trie.insert("bun");
        trie.insert("cat");
        trie.insert("card");
        trie.insert("care");
        trie.insert("dog");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==============DICTIONARY SEARCH TOOL================");
            System.out.println("enter a word:");
            System.out.println(" 1. add word");
            System.out.println(" 2. search word");
            System.out.println(" 3. check prefix");
            System.out.println(" 4. exit");

            int choice = scanner.nextInt();
            
            switch(choice){
                case 1:
                    System.out.println("enter a word : ");
                    String wordToAdd = scanner.next();
                    trie.insert(wordToAdd);
                    System.out.println("word added !");
                    break;

                case 2:
                    System.out.println("enter a word : ");
                    String searchWord = scanner.next();

                    if (trie.search(searchWord)) {
                        System.out.println("word found !");
                    } else {
                        System.out.println("word not found !");
                    }
                    break;

                case 3:
                    System.out.println("enter a prefix : ");
                    String prefix = scanner.nextLine();

                    if (trie.startsWith(prefix)) {
                        System.out.println("prefix exists.");
                    } else {
                        System.out.println("prefix not found.");
                    }
                    break;

                case 4:
                    System.out.println("Dictionary Closed");
                    
                    scanner.close();
                    return;

                default:
                    System.out.println("invalid choice, try again.");
                    break;
            }
        }
    }
}