package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Day04 {
	
	public static boolean lcheckValidPassport(String sPassport) {
    	boolean lbyr = false;
    	boolean liyr = false;
    	boolean leyr = false;
    	boolean lhgt = false;
    	boolean lhcl = false;
    	boolean lecl = false;
    	boolean lpid = false;

    	String[] sElements = sPassport.split(" ");

    	for(int i = 1; i < sElements.length; i++) {
    		String[] sTemp = sElements[i].split(":");

    		if(sTemp[0].equalsIgnoreCase("byr")) {
    			if(Integer.parseInt(sTemp[1]) >= 1920 && Integer.parseInt(sTemp[1]) <= 2002 ) {
    				lbyr = true;
    			}
    		}
    		
    		if(sTemp[0].equalsIgnoreCase("iyr")) {
    			if(Integer.parseInt(sTemp[1]) >=2010 && Integer.parseInt(sTemp[1]) <= 2020 ) {
    				liyr = true;
    			}
    		}

    		if(sTemp[0].equalsIgnoreCase("eyr")) {
    			if(Integer.parseInt(sTemp[1]) >= 2020 && Integer.parseInt(sTemp[1]) <= 2030 ) {
    				leyr = true;
    			}
    		}

    		if(sTemp[0].equalsIgnoreCase("hgt")) {
    			String sUnit = sTemp[1].substring(sTemp[1].length() - 2);
				String sHeight = sTemp[1].replace(sUnit,"");
				Pattern pattern = Pattern.compile("\\d{2,3}");

				if(pattern.matcher(sHeight).matches() ){
					int iHeight = Integer.parseInt(sHeight);

					if(sUnit.equalsIgnoreCase("cm")){
						if(iHeight >= 150 && iHeight <= 193){
							lhgt = true;
						}
					}// if cm

					if(sUnit.equalsIgnoreCase("in")){
						if(iHeight >= 59 && iHeight <= 76){
							lhgt = true;
						}
					}//if in

				}// if pattern
    		} //if hgt

    		if(sTemp[0].equalsIgnoreCase("hcl")) {
				Pattern pattern = Pattern.compile("(#)(\\d|[a-f]){6}");
				if(pattern.matcher(sTemp[1]).matches() ) {
					lhcl = true;
    			}
    		}
    		

    		if(sTemp[0].equalsIgnoreCase("ecl")) {
    			if( sTemp[1].equalsIgnoreCase("amb") ||
						sTemp[1].equalsIgnoreCase("blu") ||
						sTemp[1].equalsIgnoreCase("brn") ||
						sTemp[1].equalsIgnoreCase("gry") ||
						sTemp[1].equalsIgnoreCase("grn") ||
						sTemp[1].equalsIgnoreCase("hzl") ||
						sTemp[1].equalsIgnoreCase("oth")) {
    				lecl = true;
    			}
    		}

    		if(sTemp[0].equalsIgnoreCase("pid")) {
				Pattern pattern = Pattern.compile("\\d{9}");

    			if(pattern.matcher(sTemp[1]).matches() ) {
    				lpid = true;
    			}
    		}

    	}
    	
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

