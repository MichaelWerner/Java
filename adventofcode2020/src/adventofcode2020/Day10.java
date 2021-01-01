package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 {
	
	public static long countPartial(List<Integer> iNumbers, int iStart, int iEnd) {
		long iCombinations = 0;
		
		if(iEnd - iStart == 1) {
			iCombinations = 1;
		}
		else {
			for(int i = iStart, j = i; i <= iEnd; i++) {
				j++;
				if(j <= iEnd) {
					if(iNumbers.get(j) - iNumbers.get(i) <= 2 && i < j) {
						iCombinations++;
						iCombinations += countPartial(iNumbers, j, iEnd - 1);
					}//if iNumbers
				}//if j
			}// for i
		}// else
		
		return iCombinations;
	}
	
	public static long countCombinations(List<Integer> iNumbers) {
		//inspired by: https://github.com/romellem/advent-of-code/pull/139
		long iCombinations = 1;
		long iTotalCombinations = 1;
		int iStart = 0; //the start element of the part of the list to analyze
		for(int i = 0; i < iNumbers.size(); i++) {
			int j = i + 1;
			if(j < iNumbers.size()) {
				if(iNumbers.get(j) - iNumbers.get(i) == 3 && iStart < i) {
					//i is now the end of the partial list
					//get the combinations for this part of the list 
					iCombinations = countPartial(iNumbers, iStart, i);
					
					iTotalCombinations = iTotalCombinations * iCombinations;
					System.out.println("Start: " + iStart + ", End: " + i + ", Combinations: " + iCombinations + ", Total: " + iTotalCombinations);
					
					//j is the start of the next part, save that value
					iStart = j;
				}// if iNumbers.get(j)	
			}// if j
		}// for i
		
		return iTotalCombinations;
		
	}//countCombinations
	
	public static void countOneThree(List<Integer> iNumbers) {
		int iOne = 0;
		int iThree = 0; 

    	for(int i = 0; i < iNumbers.size() - 1; i++) {
    		if(iNumbers.get(i + 1) - iNumbers.get(i) == 1) {
    			iOne++;
    		}
    		
    		if(iNumbers.get(i + 1) - iNumbers.get(i) == 3) {
    			iThree++;
    		}
    		
    	}// for i
    	
    	System.out.println("One: " + iOne + ", Three: " + iThree + ", Product: " + iOne * iThree);		
	}//countOneThree
	
	public static void main(String[] args) {
		String input="";
		List<Integer> iNumbers = new ArrayList<Integer>();

		//add the outlet
		iNumbers.add(0);
		
    	try (BufferedReader br = new BufferedReader(new FileReader(".\\input\\day10.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	iNumbers.add(Integer.parseInt(input));
                } //if    
            } //while
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      } //catch

    	//add the end point
    	Collections.sort(iNumbers);
    	iNumbers.add(iNumbers.get(iNumbers.size() - 1) + 3);
    	//System.out.println(iNumbers.toString());
    	countOneThree(iNumbers);
    	
   		long iCombinations = countCombinations(iNumbers);
   		System.out.println("Possible combinations: " + iCombinations);
	}//main
}
