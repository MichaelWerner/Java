package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {

	static ArrayList<String> sResultList = new ArrayList<String>();
	static int iBagsinBag = 0;
	
	public static void countBagsInside(ArrayList<String> sBagList, String sSearch, int iNum) {
		Pattern pattern = Pattern.compile("^" + sSearch);

		for(int i = 0; i < sBagList.size(); i++) {
    		Matcher matcher = pattern.matcher(sBagList.get(i));

    		if(matcher.find() ) {
    			Pattern findnext = Pattern.compile("(\\d+.+)");
    			Matcher remainder = findnext.matcher(sBagList.get(i));
    			System.out.println(sBagList.get(i));
    			if(remainder.find()) {
    				String[] sTemp = remainder.group().split(", ");
    				for(int j = 0; j < sTemp.length; j++) {
    					//get the number
    					int iQty = Integer.parseInt(sTemp[j].substring(0, sTemp[j].indexOf(" ")));
    					
    					//get the bag
    					String sNewBag = sTemp[j].substring(sTemp[j].indexOf(" "),sTemp[j].length());
    					sNewBag = sNewBag.replace('.', ' ').strip();
    					
    					iBagsinBag += iQty * iNum;
    					countBagsInside(sBagList, sNewBag, iQty * iNum);
    				}
    			}

    		}
		}	
	}
	
	public static void countBags(ArrayList<String> sBagList, String sSearch) {
		Pattern pattern = Pattern.compile(".+" + sSearch);
		
    	for(int i = 0; i < sBagList.size(); i++) {
    		Matcher matcher = pattern.matcher(sBagList.get(i));

    		if(matcher.find() ) {
    			
    			String[] sTemp = sBagList.get(i).split(" ");
    			String sNewSearch = sTemp[0] + " " + sTemp[1];
    			if(!sResultList.contains(sNewSearch)) {
    				sResultList.add(sNewSearch);
    			}
    			countBags(sBagList, sNewSearch);		
    		} 	
    	}		
		
	}
	
	public static void main(String[] args) {
    	String input;
    	ArrayList<String> sBags = new ArrayList<String>();
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(".\\input\\day07.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	sBags.add(input);
                } //if    
                else {  //new group

                } //else	
            } //while
            
            //the last group
            
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      }

    	countBags(sBags, "shiny gold");
    	countBagsInside(sBags, "shiny gold",1);
    	
    	System.out.println(sResultList.size());
    	System.out.println(iBagsinBag);
    	
    } //main
}
