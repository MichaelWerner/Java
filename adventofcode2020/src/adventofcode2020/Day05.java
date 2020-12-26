package adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day05 {
	public static void seats(ArrayList<String> seatslist) {

		ArrayList<Integer> seatids = new ArrayList<Integer>();
		
		for(int i = 0; i < seatslist.size(); i++) {
			String sRow = seatslist.get(i).substring(0, seatslist.get(i).length() - 3);
			String sSeat = seatslist.get(i).substring(seatslist.get(i).length() - 3, seatslist.get(i).length());

			int iFrontRow = 0;
			int iBackRow = 127;
			int iLeftSeat = 0;
			int iRightSeat = 7;
			//get the row
			for(int j = 0; j < sRow.length(); j++) {
				if(sRow.charAt(j) == 'F') {
					iBackRow = iFrontRow + (iBackRow - iFrontRow) / 2;
				}
				else {
					iFrontRow = iFrontRow + (iBackRow - iFrontRow) / 2 + 1;
				}
				
			}//for j
			
			//get the seat
			for(int j = 0; j < sSeat.length(); j++) {
				if(sSeat.charAt(j) == 'L') {
					iRightSeat = iLeftSeat + (iRightSeat - iLeftSeat) / 2;
									}
				else {
					iLeftSeat = iLeftSeat + (iRightSeat - iLeftSeat) / 2 + 1;
				}			
			}//for j
			
			seatids.add(iFrontRow * 8 + iLeftSeat);
			
		}//for i
		Collections.sort(seatids);
		System.out.println(seatids.get(seatids.size() - 1));
		
		//find my seat
		for(int i = 0; i < seatids.size(); i++) {
			if(seatids.get(i + 1) != seatids.get(i) + 1) {
				System.out.println(seatids.get(i) + 1);
				break;
			}
		}//for
	}//seats	
	
	public static void main(String[] args) {
		String input="";
		ArrayList<String> temp = new ArrayList<String>();
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(".\\input\\day05.txt"))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	temp.add(input);
                } //if    
            } //while
		} catch (IOException e) {
			 System.out.println(e.getMessage());
	      }

    	seats(temp);
	}//main
}
