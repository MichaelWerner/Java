package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day06 {
	public static String removeDuplicates(String input){
	    String result = "";
	    for (int i = 0; i < input.length(); i++) {
	        if(!result.contains(String.valueOf(input.charAt(i)))) {
	            result += String.valueOf(input.charAt(i));
	        }
	    }
	    return result;
	}
	
	public static int anyone(List<String> input) {
		String s = "";

		for(int i=0; i<input.size(); i++) {
			s += input.get(i);
		}

       	return removeDuplicates(s).length();
		
	}
	
	public static int everyone(List<String> input) {
		if(input.size() == 1) {
			return input.get(0).length();
		}
		else {
			int result = 0;
			String temp = "";
			int i = input.size();
            int j = 0;
            
			for(j = 0; j < i; j++) {
				temp += input.get(j);
			}
			
			char[] sUnique = removeDuplicates(temp).toCharArray();

			for(j = 0; j < sUnique.length; j++) {
				char x = sUnique[j];
				long iCount = temp.chars().filter(ch -> ch == x).count();
				if(iCount == i) {
					result++;
				}
			}
			
			return result;
		}
	}
	
	public static void main(String[] args) {
    	String input;
    	List<String> sTemp = new ArrayList<String>();
    	int iTotalEveryone = 0;
    	int iTotalAnyone = 0;
    	
    	try (BufferedReader br = new BufferedReader(new FileReader("customs.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	sTemp.add(input);
                } //if    
                else {  //new group
                	iTotalAnyone += anyone(sTemp);
                	iTotalEveryone += everyone(sTemp);
                	sTemp.clear();
                } //else	
            } //while
            
            //the last group
            iTotalAnyone += anyone(sTemp);
            iTotalEveryone += everyone(sTemp);
            
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      }

    	System.out.println(iTotalAnyone);
    	System.out.println(iTotalEveryone);
		
    } //main
}
