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
	 *  Returns 0 on successful addition
	 *  Returns 1 on failed addition
	 */
	public int addBug(Bug addedBug) {
		return (1);
	}
}
