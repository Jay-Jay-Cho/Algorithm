package hash;

import java.util.Arrays;

public class Programmers_PhoneBook {
	
	
	static boolean getAnswer(String[] phone_book) {
		for(int i=0;i<phone_book.length;i++) {
			String temp = phone_book[i];
			for(int j=0;j<phone_book.length;j++) {
				if(j==i) continue;
				if(phone_book[j].length()<temp.length()) continue;
				if(phone_book[j].startsWith(temp)) return false;
			}
		}
		
		return true;
	}

	
	public static void main(String[] args) {
		String[] phone_book = {"1192456","119"};
		String[] phone_book2 = {"213","456","789"};
		String[] phone_book3 = {"12","123","1235","567","88"};
		System.out.println(getAnswer(phone_book));
		//System.out.println("97674223".substring(0, 3));
	}

}
