package com.kanaja.index.model;
/**
 * 
 * @author vikas_c
 *
 */
public class PrefixTrie {
	
	static TrieNode createTree()
    {
        return(new TrieNode('\0'));
    }
    
    static void insertWord(TrieNode root, String word)
    {
        int offset = 97;
        int l = word.length();
        char[] letters = word.toCharArray();
        TrieNode curNode = root;
        
        for (int i = 0; i < l; i++)
        {
            if (curNode.links[letters[i]-offset] == null)
                curNode.links[letters[i]-offset] = new TrieNode(letters[i]);
            curNode = curNode.links[letters[i]-offset];
        }
        curNode.fullWord = true;  
    }

    static boolean find(TrieNode root, String word)
    {
        char[] letters = word.toCharArray();
        int l = letters.length;
        int offset = 97;
        TrieNode curNode = root;
        
        int i;
        for (i = 0; i < l; i++)
        {
            if (curNode == null)
                return false;
            curNode = curNode.links[letters[i]-offset];
        }
        
        if (i == l && curNode == null)
            return false;
        
        if (curNode != null && !curNode.fullWord)
            return false;
        
        return true;
    }

}


class TrieNode
{
    char letter;
    TrieNode[] links;
    boolean fullWord;
    
    TrieNode(char letter)
    {
        this.letter = letter;
        links = new TrieNode[26];
        this.fullWord = false;
    }
}