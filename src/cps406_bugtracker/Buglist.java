package cps406_bugtracker;

import java.util.ArrayList;

public class Buglist {
	
	private ArrayList<Bug> buglist;
	private int nextID;
	
	public Buglist(){
		buglist = new ArrayList<Bug>();
		nextID = 0;
	}
	
	// TODO: Add code for copying the contents of the copylist (MAKE NEW BUGS, DON'T JUST GRAB THE POINTERS)
	public Buglist(Buglist copylist){
		buglist = new ArrayList<Bug>();
		nextID = copylist.getNextID();
		for (Bug b : copylist.getBugArrayList()) {
			buglist.add(new Bug(b));
		}
	}
	
	private ArrayList<Bug> getBugArrayList() {
		return (buglist);
	}
	
	private int newID() {
		int newID = nextID;
		nextID += 1;
		return(newID);
	}
	
	private int getNextID() {
		return (nextID);
	}
	
	public void addBug(String bugName, String bugDescription) {
		Bug newBug = new Bug(bugName, bugDescription, newID());
		buglist.add(newBug);
	}
	
	
	private void addBug(Bug addedBug) {
		Bug newBug = new Bug(addedBug);
		buglist.add(newBug);
	}
	
	private int getBugIndexFromID(int fetchID) {
		Bug curBug;
		for(int i = 0; i < buglist.size(); i++) {
			curBug = buglist.get(i);
			if(curBug.getID() == fetchID) {
				return(i);
			}
		}
		return(-1);
	}
}
