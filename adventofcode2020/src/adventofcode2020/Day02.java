package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day02 {
	public static void valid_1(ArrayList<String> passwordlist) {
		int iValid = 0;
		
		for(int i = 0; i < passwordlist.size(); i++) {
			String[] sTemp1 = passwordlist.get(i).split(": ");
			String[] sTemp2 = sTemp1[0].split(" ");
			String[] sTemp3 = sTemp2[0].split("-");

			int iCount = 0;
			int iMin = Integer.parseInt(sTemp3[0]);
			int iMax = Integer.parseInt(sTemp3[1]);
			String password = sTemp1[1].strip();
			
			for(int j = 0; j < password.length(); j++) {
				if(Character.toString(password.charAt(j)).equalsIgnoreCase(sTemp2[1])) {
					iCount++;
				}
			}
			
			if(iCount >= iMin && iCount <= iMax) {
				iValid++;
			}
		}//for i
		System.out.println(iValid);
	}//valid_1
	
	public static void valid_2(ArrayList<String> passwordlist) {
		int iValid = 0;
		
		for(int i = 0; i < passwordlist.size(); i++) {
			String[] sTemp1 = passwordlist.get(i).split(": ");
			String[] sTemp2 = sTemp1[0].split(" ");
			String[] sTemp3 = sTemp2[0].split("-");

			int iCount = 0;
			int iMin = Integer.parseInt(sTemp3[0]) - 1;
			int iMax = Integer.parseInt(sTemp3[1]) - 1;
			String password = sTemp1[1].strip();
			
			String char1 = Character.toString(password.charAt(iMin));
			String char2 = Character.toString(password.charAt(iMax));

			if((char1.equalsIgnoreCase(sTemp2[1]) || 
					char2.equalsIgnoreCase(sTemp2[1])) &&
					!char1.equalsIgnoreCase(char2)) {
				iValid++;
			}

		}//for i
		System.out.println(iValid);
	}	
	
	public static void main(String[] args) {
		String input="";
		ArrayList<String> temp = new ArrayList<String>();
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(".\\input\\day02.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	temp.add(input);
                } //if    
            } //while
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      }

    	valid_1(temp);
    	valid_2(temp);
    } //main
}
