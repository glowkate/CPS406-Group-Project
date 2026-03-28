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
	
	
	private void addLoadedBug(String bugStr) {
		Bug newBug = new Bug(bugStr);
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
	
	public ArrayList<String> getBugInfo(int fetchID) {
		int bugIndex = getBugIndexFromID(fetchID);
		Bug foundBug;
		ArrayList<String> returnStrings = new ArrayList<String>();
		if(bugIndex != -1) {
			foundBug = buglist.get(bugIndex);
			returnStrings.add(foundBug.getName());
			returnStrings.add(foundBug.getDiscription());
			returnStrings.add(foundBug.getArtifactsString());
		}
		else {
			returnStrings.add("");
			returnStrings.add("");
			returnStrings.add("");
		}
		return (returnStrings);
	}
	
	public void removeBug(int fetchID) {
		int bugIndex = getBugIndexFromID(fetchID);
		if(bugIndex != -1) {
			buglist.remove(bugIndex);
		}
	}
	
	public void modifyBugDesc(int fetchID, String newDesc) {
		int bugIndex = getBugIndexFromID(fetchID);
		Bug foundBug;
		if(bugIndex != -1) {
			foundBug = buglist.get(bugIndex);
			foundBug.setDiscription(newDesc);
		}
	}
	
	public void addArtifactToBug(String newArtifact, int fetchID) {
		int bugIndex = getBugIndexFromID(fetchID);
		Bug foundBug;
		if(bugIndex != -1) {
			foundBug = buglist.get(bugIndex);
			foundBug.addArtifact(newArtifact);
		}
	}
	
	public ArrayList<String> getBugsAsStrings(){
		ArrayList<String> returnList = new ArrayList<String>();
		for(Bug b : buglist) {
			returnList.add(bugToString(b));
		}
		return(returnList);
	}
	
	public ArrayList<String> getBugsAsStrings(ArrayList<String> artifactList){
		ArrayList<String> returnList = new ArrayList<String>();
		boolean hasArtifacts;
		for(Bug b : buglist) {
			hasArtifacts = true;
			for(String s : artifactList) {
				if(!b.hasArtifact(s)) {
					hasArtifacts = false;
				}
			}
			if(hasArtifacts) {
				returnList.add(bugToString(b));
			}
		}
		return(returnList);
	}
	
	public ArrayList<Integer> getAllBugIDs(){
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for(Bug b : buglist) {
			returnList.add(b.getID());
		}
		return (returnList);
	}
	
	private String bugToString(Bug inBug) {
		return(inBug.getName() + "(ID: " + String.valueOf(inBug.getID()) + ")\n" + inBug.getDiscription() + "\n" + inBug.getArtifactsString());
	}
}
