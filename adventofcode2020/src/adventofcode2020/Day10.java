package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 {
	//hold the combinations
	public static List<String> sCombinations = new ArrayList<String>();
	
	public static long countPartial(List<Integer> iNumbers, int iStart, int iEnd) {
		long iCombinations = 1;
		List<Integer> iNewList = new ArrayList<Integer>();
		
		/*
        //test output of the current list
		for(int i = iStart; i <= iEnd; i++) {
			System.out.print(iNumbers.get(i));
			if(i < iEnd) System.out.print(",");
		}
		System.out.println();
		*/
		
		for(int i = iStart; i <= iEnd; i += 1) {
			// test the next but one number. The next number does not need 
			// to be checked. It is always part of the solution
			if(i + 2 < iEnd && iNumbers.get(i + 2) - iNumbers.get(i) <= 3) {
				iNewList.clear();
				//System.out.println("i is: " + i + " : " + iNumbers.toString());
				
				//create the new list, skip the number one before the number
				//that was tested
				for(int k = 0; k <= iEnd; k++) {
					if(k != i + 1) {
						iNewList.add(iNumbers.get(k));
					}	
				}// for k
				
				/* check it the current list has already been identified as
				   a combination. If it has not, add it.
				   It can happen, that a combination is created more than once.
				*/
				
				if(!sCombinations.contains(iNewList.toString())) {
					sCombinations.add(iNewList.toString());
					iCombinations += countPartial(iNewList, iStart, iNewList.size() - 1);
				}// if !sCombinations
			}// if i + 2	
		} // for i
		
		return iCombinations;
	}// countPartial
	
	public static long countCombinations(List<Integer> iNumbers) {
		//inspired by: https://github.com/romellem/advent-of-code/pull/139
		long iCombinations = 1;
		long iTotalCombinations = 1;
		int iStart = 0; //the start element of the part of the list to analyze
		for(int i = 0; i < iNumbers.size(); i++) {
			int j = i + 1;
			if(j < iNumbers.size()) {
				if(iNumbers.get(j) - iNumbers.get(i) == 3) {
					//j is the end of the partial list and the beginning of the next
					//get the combinations for this part of the list 

					if(iStart == i) { //when there are consecutive jumps of 3, like 38, 41, 44
						iCombinations = 1;
					}
					else {
						//no need to keep the combinations of the previous run
						sCombinations.clear();
						iCombinations = countPartial(iNumbers, iStart, j);
					}// if iSart
					
					iTotalCombinations = iTotalCombinations * iCombinations;
					//System.out.println("Start: " + iStart + ", End: " + j + ", Combinations: " + iCombinations + ", Total: " + iTotalCombinations);
					
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
    		if(iNumbers.get(i + 1) - iNumbers.get(i) == 1) 	iOne++;
    		
    		if(iNumbers.get(i + 1) - iNumbers.get(i) == 3) 	iThree++;
    		
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
 
    	/*
    	// a small test case. The result is 14. (7 * 2)
    	iNumbers.clear();
    	iNumbers.add(0);
    	iNumbers.add(1);
    	iNumbers.add(2);
    	iNumbers.add(3);
    	iNumbers.add(4);
    	iNumbers.add(7);
    	iNumbers.add(9);
    	iNumbers.add(10);
       	*/
    	
    	//add the end point
    	Collections.sort(iNumbers);
    	iNumbers.add(iNumbers.get(iNumbers.size() - 1) + 3);
    	//System.out.println(iNumbers.toString());
    	countOneThree(iNumbers);
    	
   		long iCombinations = countCombinations(iNumbers);
   		System.out.println("Possible combinations: " + iCombinations);
	}//main
}
