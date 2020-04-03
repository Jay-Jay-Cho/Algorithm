package api;

import java.util.HashMap;

public class Trie {
	
	class TrieNode{
		char c;
		boolean isLeaf;
		int len;
		HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		HashMap<Integer, Integer> childrenWithLen = new HashMap<Integer,Integer>();
		
		TrieNode(){};
		TrieNode(char c){
			this.c = c;
		}
		
	}
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insert(String str) {
		HashMap<Character, TrieNode> children = root.children;
		HashMap<Integer, Integer> childrenWithLen = root.childrenWithLen;
		
		TrieNode t;
		int str_len = str.length();
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			if(children.containsKey(c)) {
				t = children.get(c);
			}else {
				t = new TrieNode(c);
				children.put(c, t);
			}
			
			if(childrenWithLen.containsKey(str_len)) {
				childrenWithLen.replace(str_len, childrenWithLen.get(str_len)+1);
			}else {
				childrenWithLen.put(str_len, 1);
			}
			
			children = t.children;
			childrenWithLen = t.childrenWithLen;
			
			if(i==str.length()-1) {
				t.isLeaf = true;
			}
		}
	}
	
	public int getChildrenLen(String str, int str_len) {
		int len = 0;
		
		HashMap<Character, TrieNode> children = root.children;
		
		TrieNode t = null;
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			t = children.get(c);
			children = t.children;
		}
		
		HashMap<Integer, Integer> childrenWithLen = t.childrenWithLen;
		if(childrenWithLen.containsKey(str_len)) {
			len = childrenWithLen.get(str_len);
		}else {
			len = 0;
		}
		
		return len;
	}
	
	
	public boolean contains(String str){
		boolean check = true;
		
		HashMap<Character, TrieNode> children = root.children;
		
		TrieNode t;
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			if(children.containsKey(c)) {
				t = children.get(c);
				children = t.children;
			}else {
				check = false;
				break;
			}
		}
		
		
		return check;
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("frodo");
		trie.insert("front");
		trie.insert("frozen");
		//boolean ans = trie.contains("frod");
		int cnt = trie.getChildrenLen("frod", 1);
		System.out.println(cnt);
	}
	
	
	
	

}
