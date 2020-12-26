package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day03 {
	public static void main(String[] args) {
    	String input;
    	List<String> sTemp = new ArrayList<String>();
    	int iColumn = 0;
    	int iRight = 0;
    	int iDown = 0;
    	int iTrees = 0;
    	long iProduct = 1;
    	int[][] iSlopes={ {1,1}, {3,1}, {5,1}, {7,1}, {1,2} };
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(".\\input\\day03.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	sTemp.add(input);
                } //if    
            } //while
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      }
    	String[] strMap = sTemp.toArray(new String[0]);
    	int iLineLength = strMap[0].length() - 1;
    	int iMaxRows = strMap.length;

    	for(int i=0; i<iSlopes.length;i++) {
    		iRight = iSlopes[i][0];
    		iDown = iSlopes[i][1];
    		System.out.println("Right: " + iRight + ", Down: " + iDown);
    		iTrees = 0;
    		iColumn = 0;

	    	for(int iRow=0;iRow<iMaxRows;iRow += iDown) {
	    		if(strMap[iRow].charAt(iColumn) == '#') {
	    			iTrees++;
	    		}
	
	    		iColumn += iRight;
	    		if(iColumn > iLineLength) {
	    			iColumn -= iLineLength;
	    			iColumn--;
	    		}
	    		
	    	}//for iRow=0
	    	System.out.println(iTrees);
	    	iProduct = iProduct * iTrees;
    	}//for i
	    System.out.println(iProduct);	
    } //main
} //class
