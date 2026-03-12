package cps406_bugtracker;

import java.util.ArrayList;

public class Bug {
	private String bugName;
	private String bugDiscription;
	private ArrayList<String> artifactList;
	
	public Bug (String initName, String initDiscription, ArrayList<String> initArtiList) {
		bugName = initName;
		bugDiscription = initName;
		artifactList = new ArrayList<String>();
		for (String s : initArtiList) {
			artifactList.add(s);
		}
	}
	
	public Bug (Bug copyBug) {
		// TODO: Add code for init from another bug (copy)
	}
	
	public Bug (String loadstr) {
		// TODO: Add code for init from a string
	}
}
