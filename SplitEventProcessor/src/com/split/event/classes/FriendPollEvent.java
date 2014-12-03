package com.split.event.classes;

import java.util.HashMap;
import java.util.Map;

public class FriendPollEvent {
	private String type;
	private String recipients;
	private String pollID; 
	private String pollAuthor;
	private String pollTimeStamp;
	
	public FriendPollEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FriendPollEvent(String recipients, String pollID, String pollAuthor, String pollTimeStamp) {
		super();
		this.type = "friendPollEvent";
		this.recipients = recipients;
		this.pollID = pollID; 
		this.pollAuthor = pollAuthor;
		this.pollTimeStamp = pollTimeStamp;
	}
	
	public Map<String, String> turnFriendPollEventIntoHashMap() {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", this.getType());
		myMap.put("recipients",this.getRecipients());
		myMap.put("pollID", this.getPollID());
		myMap.put("pollTimeStamp", this.getPollTimeStamp());
		myMap.put("pollAuthor", this.getPollAuthor());
		
		return myMap;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getPollID() {
		return pollID;
	}

	public void setPollID(String pollID) {
		this.pollID = pollID;
	}

	public String getPollAuthor() {
		return pollAuthor;
	}

	public void setPollAuthor(String pollAuthor) {
		this.pollAuthor = pollAuthor;
	}

	public String getPollTimeStamp() {
		return pollTimeStamp;
	}

	public void setPollTimeStamp(String pollTimeStamp) {
		this.pollTimeStamp = pollTimeStamp;
	}

	
}