package cps406_bugtracker;

import java.util.ArrayList;

public class Buglist {
	
	private ArrayList<Bug> buglist;
	private int nextID;
	
	public Buglist(){
		buglist = new ArrayList<Bug>();
		nextID = 0;
	}
	
	public Buglist(Buglist copylist){
		buglist = new ArrayList<Bug>();
		nextID = copylist.getNextID();
		for (Bug b : copylist.getBugArrayList()) {
			buglist.add(new Bug(b));
		}
	}
	
	public Buglist(ArrayList<String> stringBugs) {
		nextID = 0;
		for(int i = 0; i < stringBugs.size(); i++) {
			buglist.add(new Bug (stringBugs.get(i)));
			if(buglist.get(i).getID() > nextID) {
				nextID = buglist.get(i).getID() + 1;
			}
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
	
	// Returns the ID of the new bug
	public int addBug(String bugName, String bugDescription) {
		int setID = newID();
		Bug newBug = new Bug(bugName, bugDescription, setID);
		buglist.add(newBug);
		return (setID);
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
	
	public ArrayList<Integer> getBugIDs(){
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		Bug curBug;
		for(int i = 0; i < buglist.size() ; i++) {
			curBug = buglist.get(i);
			returnList.add(curBug.getID());
		}
		return(returnList);
	}
	
	public ArrayList<Integer> getBugIDs(ArrayList<String> artifactList){
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		boolean hasArtifacts;
		Bug curBug;
		for(int i = 0; i < buglist.size() ; i++) {
			hasArtifacts = true;
			curBug = buglist.get(i);
			for(String s : artifactList) {
				if(!curBug.hasArtifact(s)) {
					hasArtifacts = false;
				}
			}
			if(hasArtifacts) {
				returnList.add(curBug.getID());
			}
		}
		return(returnList);
	}
	
}
