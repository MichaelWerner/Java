package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day01 {
	
	public static void sum2020_2(ArrayList<Integer> expenses) {
		boolean lBreak = false;
		for(int i = 0; i<expenses.size(); i++) {
			for(int j = i + 1; j < expenses.size(); j++) {
				if(expenses.get(i) + expenses.get(j) == 2020) {
					System.out.println(expenses.get(i) * expenses.get(j));
					lBreak = true;
					break;
				}
				
				if(lBreak) {
					break;
				}
			}
		}
	}
	
	public static void sum2020_3(ArrayList<Integer> expenses) {
		boolean lBreak = false;
		for(int i = 0; i<expenses.size(); i++) {
			for(int j = i + 1; j < expenses.size(); j++) {
				for(int k = 1; k < expenses.size(); k++) {
					if(expenses.get(i) + expenses.get(j) + expenses.get(k) == 2020) {
						System.out.println(expenses.get(i) * expenses.get(j) * expenses.get(k));
						lBreak = true;
						break;
					}
				} // for k
				
				if(lBreak) {
					break;
				}
			}// for j

			if(lBreak) {
				break;
			}
		}// for i
	}	
	public static void main(String[] args) {
		String input="";
		ArrayList<Integer> temp = new ArrayList<Integer>();
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(".\\input\\day01.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	temp.add(Integer.parseInt(input));
                } //if    
            } //while
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      }

    	sum2020_2(temp);
    	sum2020_3(temp);
    } //main
}
