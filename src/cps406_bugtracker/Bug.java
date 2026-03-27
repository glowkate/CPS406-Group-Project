package cps406_bugtracker;

import java.util.TreeSet;

public class Bug {
	private String bugName;
	private String bugDiscription;
	private TreeSet<String> artifactList;
	private int bugID;
	
	public Bug (String initName, String initDiscription, int initID) {
		bugName = initName;
		bugDiscription = initDiscription;
		artifactList = new TreeSet<String>();
		bugID = initID;
	}
	
	public Bug (Bug copyBug) {
		bugName = copyBug.getName();
		bugDiscription = copyBug.getDiscription();
		artifactList = new TreeSet<String>();
		for (String s : copyBug.getArtifactList()) {
			artifactList.add(s);
		}
		bugID = copyBug.getID();
	}
	
	public Bug (String loadStr) {
		artifactList = new TreeSet<String>();
		boolean loadSuccsess = false;
		boolean loadingStr = false;
		int curPhase = 0;
		String curLoadedString = "";
		char c;
		for(int i = 0; i < loadStr.length(); i++) {
			c = loadStr.charAt(i);
			if(loadingStr && c == ']') {
				switch(curPhase) {
					case 0:
						bugName = curLoadedString;
						System.out.println("Loaded bug name: " + curLoadedString);
						curPhase = 1;
						break;
					case 1:
						bugDiscription = curLoadedString;
						System.out.println("Loaded bug description: " + curLoadedString);
						curPhase = 2;
						break;
					case 2:
						artifactList.add(curLoadedString);
						System.out.println("Loaded bug artifact: " + curLoadedString);
						break;
					case 3:
						bugID = Integer.parseInt(curLoadedString);
						System.out.println("Loaded bug ID: " + curLoadedString);
						loadSuccsess = true;
						break;
				}
				curLoadedString = "";
				loadingStr = false;
			}
			else if(c == '}' && curPhase == 2) {
				curPhase = 3;
			}
			else if(c == '[') {
				loadingStr = true;
			}
			else if(loadingStr) {
				curLoadedString = curLoadedString + c;
			}
		}
		if (!loadSuccsess) {
			throw(new IllegalArgumentException("Bug failed to load from String: " + loadStr));
		}
	}
	
	public String getName() {
		return bugName;
	}
	
	public String getDiscription() {
		return bugDiscription;
	}
	
	public void setDiscription(String newDesc) {
		bugDiscription = newDesc;
	}
	
	public boolean hasArtifact(String artifact) {
		return artifactList.contains(artifact);
	}
	
	public void addArtifact(String newArti) {
		artifactList.add(newArti);
	}
	
	public void removeArtifact(String removeArti) {
		artifactList.remove(removeArti);
	}
	
	public String getArtifactsString() {
		String returnStr = "{";
		boolean isFirstStr = true;
		for (String s : artifactList) {
			if (!isFirstStr) {
				returnStr = returnStr + ", ";
			}
			returnStr =  returnStr + "[" + s + "]";
		}
		returnStr = returnStr + "}";
		return (returnStr);
	}
	
	private TreeSet<String> getArtifactList() {
		return artifactList;
	}
	
	public int getID() {
		return bugID;
	}
	
	public String saveBug() {
		String returnStr =  "Bug Name: [" + bugName;
		returnStr = returnStr + "], Bug Discripion: [" + bugDiscription;
		returnStr = returnStr +  "], Artifacts: " + getArtifactsString();
		returnStr = returnStr + ", ID: [" + String.valueOf(bugID) + "]";
		return returnStr;
	}
}
