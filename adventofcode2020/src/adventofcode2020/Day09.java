package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day09 {
	
	public static long findWeakness(List<Long> iNumbers, long iInvalid) {
		long iWeakness = 0;
		for(int i = 0; i < iNumbers.size(); i++) {
			long iSum = iNumbers.get(i);
			for(int j = i + 1; j < iNumbers.size(); j++) {
				iSum += iNumbers.get(j);
				if(iSum == iInvalid) {
					List<Long> iRange = new ArrayList<Long>();
					for(int k = i; k <= j; k++) {
						iRange.add(iNumbers.get(k));
					}
					Collections.sort(iRange);
					iWeakness = iRange.get(0) + iRange.get(iRange.size() - 1);
				}
				
			}// for j
		}//for i
		
		return iWeakness;
	}
	
	public static long checkEncoding(List<Long> iNumbers) {
		
		int iPreamble = 25;
		long iVerify = 0;
		boolean lisValid = false;
		for(int i = 0; i < iNumbers.size() - iPreamble; i++) {
			iVerify = iNumbers.get(i + iPreamble);
			for(int j=i; j < i + iPreamble; j++) {
				lisValid = false;
				for(int k = j +1; k < i + iPreamble; k++) {
					if(iNumbers.get(j) + iNumbers.get(k) == iVerify) {
						lisValid = true;
						break;
					}
				}//for k
				
				if(lisValid) {
					break;
				}
			}// int j
			
			if(!lisValid) {
				//System.out.println(iVerify + " is not valid!");
				break;
			}
			
		}// for i
		return iVerify;
	}//checkEncoding
	
	public static void main(String[] args) {
		String input="";
		List<Long> iNumbers = new ArrayList<Long>();
		
    	try (BufferedReader br = new BufferedReader(new FileReader(".\\input\\day09.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	iNumbers.add(Long.parseLong(input));
                } //if    
            } //while
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      } //catch

    	long iInvalid = checkEncoding(iNumbers);
    	System.out.println(iInvalid + " is invalid!");
    	
    	long iWeakness = findWeakness(iNumbers, iInvalid);
    	System.out.println(iWeakness + " is the weakness!");
   		
	}//main
	
}//class
