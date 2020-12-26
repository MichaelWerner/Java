package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day08 {
	static ArrayList<String> sBoot 	= new ArrayList<String>();
	static int iChanged = 0;
	
	public static void changeBootOrder() {
		
		List<Integer> iBootOrder = new ArrayList<Integer>();
		
		for(int i = 0; i < sBoot.size(); i++) {
			
			String[] sSplit = sBoot.get(i).split(" ");
			String sAction = sSplit[0];
			String sValue = "";
			boolean lTest = false;
			
			switch(sAction) {
				case "nop":
					lTest = true;
					sValue = "jmp " + sSplit[1];
					break;
					
				case "acc":
					break;
					
				case "jmp":
					lTest = true;
					sValue = "nop " + sSplit[1];
					break;
			}// switch
			
			if(lTest) {
				iBootOrder.clear();
				int iAcc = 0;
				System.out.println("Try fix at pos " + i + " using value " + sValue);
				sBoot.set(i, sValue);
				boolean linfinite = infiniteloop(0, iBootOrder, iAcc);
				
				if(!linfinite) {
					System.out.println("Fixed it! Line " + i + " was changed from " + sAction + " to " + sValue);
					break;
				}
				else {
					//reset the value
					sValue = sAction + " " + sSplit[1];
					sBoot.set(i, sValue);
				}
			}// if lTest
		}// for i
	}// changeBootOrder
	
	public static boolean infiniteloop(int iPos, List<Integer> iBootOrder, int iAcc) {
		
		int i = iPos;
		boolean lInfinite = false;
		String[] sSplit = sBoot.get(iPos).split(" "); 
		String sAction = sSplit[0];
		int iAction = Integer.parseInt(sSplit[1]);
		
		switch(sAction) {
			case "nop":
				i++;
				break;
				
			case "acc":
				iAcc += iAction;
				i++;
				break;
				
			case "jmp":
				i += iAction;
				break;
		}//switch
		if(iBootOrder.contains(i)) {
			System.out.println("Infinite loop detected, Acc is: " + iAcc + "; Pos is: " + i);
			lInfinite = true;
		}// if(iBootOrder.contains(i)
		else {
			if(i < sBoot.size()) {
				iBootOrder.add(i);
				lInfinite = infiniteloop(i, iBootOrder, iAcc);
			}// if(i < sBoot.size()
			else {
				System.out.println("Terminated, Acc is: " + iAcc);
			}
		}	
		
		return lInfinite;
	}	
	
	
	public static void main(String[] args) {
		String input="";
		List<Integer> iBootOrder = new ArrayList<Integer>();
		int iAcc = 0;
		
    	try (BufferedReader br = new BufferedReader(new FileReader(".\\input\\day08.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	sBoot.add(input);
                } //if    
            } //while
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      }
    	
    	boolean lInfinite = infiniteloop(0, iBootOrder, iAcc);
    	
    	if(lInfinite) {
    		System.out.println("Try to  fix it.");
    		changeBootOrder();
    	}
	}//main
}
