package cps406_bugtracker;

import java.util.ArrayList;

public class Buglist {
	
	private ArrayList<Bug> buglist;
	
	public Buglist(){
		buglist = new ArrayList<Bug>();
	}
	
	// TODO: Add code for copying the contents of the copylist (MAKE NEW BUGS, DON'T JUST GRAB THE POINTERS)
	public Buglist(Buglist copylist){
		buglist = new ArrayList<Bug>();
	}
	
	/*  
	 * Bug must have a unique name
	 *  
	 * Returns 0 on successful addition
	 * Returns 1 on failed addition for unknown reason
	 * Returns 2 on failed addition due to duplicate bug name
	 */
	public int addBug(String bugName, String bugDescription) {
		return (1);
	}
	
	private int addBug(Bug addedBug) {
		if(findBug(addedBug.getName()) != -1) {
			return(2);
		}
		return (1);
	}
	
	/*
	 * Returns index of given bug. If bug wasn't found, returns -1
	 */
	private int findBug(String bugName) {
		Bug b;
		for(int i = 0; i < buglist.size(); i++) {
			b = buglist.get(i);
			if(b.getName().equals(bugName)) {
				return(i);
			}
		}
		return(-1);
	}
}
