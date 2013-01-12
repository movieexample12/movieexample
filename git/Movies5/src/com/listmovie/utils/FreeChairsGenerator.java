package com.listmovie.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** there are in most situations no information bout cinemas, when information exists, there are no info about free places*/
public class FreeChairsGenerator {
	public static final int COUNT_OF_SEATS = 30;
	private static List<Integer> list = new ArrayList<Integer>();
	
	//initiation list of seats because we have no information about it
	static {
		for (int i=1; i<=COUNT_OF_SEATS; ++i ) {
	     	list.add(i);
	    }
	}
	
	public Seats getNewFreeChairs() {
		
        /* Genereating random set of free seats */
        Collections.shuffle(list);
        
        int numberFree = new Random().nextInt(COUNT_OF_SEATS);
        return new Seats(numberFree, list.subList(0, numberFree));
	}
	
	public final class Seats {
		int number;
		List<Integer> array;
		public Seats(int number, List<Integer> array) {
			this.number = number;
			this.array = array;
		}
		
		public int getNumber() {
			return number;
		}
		
		public List<Integer> getNumbers() {
			return array;
		}
	}
	
}