package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day04 {
	
	public static boolean lcheckValidPassport(String sPassport) {
    	boolean lbyr = sPassport.contains("byr:");
    	boolean liyr = sPassport.contains("iyr:");
    	boolean leyr = sPassport.contains("eyr:");
    	boolean lhgt = sPassport.contains("hgt:");
    	boolean lhcl = sPassport.contains("hcl:");
    	boolean lecl = sPassport.contains("ecl:");
    	boolean lpid = sPassport.contains("pid:");
    	boolean lcid = sPassport.contains("cid:");
    	
    	if(lbyr && liyr && leyr && lhgt && lhcl && lecl && lpid) {
    		return true;
    	}
    	else {
    		return false;
    	}
    		
	}

	public static void main(String[] args) {
    	String input;
    	String sTemp = "";
    	int iValid = 0;
    	
    	try (BufferedReader br = new BufferedReader(new FileReader("passports.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	sTemp = sTemp + " " + input;
                } //if    
                else {  //I have a passport
                	boolean lValid = lcheckValidPassport(sTemp);
                	if(lValid) {
                		iValid++;
                	}
                	sTemp = "";
                } //else	
            } //while
            
            //to get the last passport
        	boolean lValid = lcheckValidPassport(sTemp);
        	if(lValid) {
        		iValid++;
        	}
        	
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      }

	    System.out.println(iValid);	
    } //main
} //class

