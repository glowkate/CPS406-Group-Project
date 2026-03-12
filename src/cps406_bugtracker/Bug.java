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
		bugName = copyBug.getName();
		bugDiscription = copyBug.getDiscription();
		for (String s : copyBug.getArtifactList()) {
			artifactList.add(s);
		}
	}
	
	public Bug (String loadstr) {
		// TODO: Add code for init from a string
	}
	
	public String getName() {
		return bugName;
	}
	
	public String getDiscription() {
		return bugDiscription;
	}
	
	public boolean hasArtifact(String artifact) {
		boolean hasArtifact = false;
		for (String s : artifactList) {
			if (s.equals(artifact)) {
				hasArtifact = true;
				break;
			}
		}
		return hasArtifact;
	}
	
	public String getArtifactsString() {
		String returnStr = "";
		boolean isFirstStr = true;
		for (String s : artifactList) {
			if (!isFirstStr) {
				returnStr = returnStr + ", ";
			}
			returnStr = returnStr + s;
		}
		return (returnStr);
	}
	
	private ArrayList<String> getArtifactList() {
		return artifactList;
	}
}
