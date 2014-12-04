package com.split.event.classes;

import java.util.HashMap;
import java.util.Map;

public class TagEvent {
	private String type;
	private String recipients;
	private String pollID;
	private String pollTimeStamp;
	private String taggerID;

	public TagEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TagEvent(String recipients, String pollID, String pollTimeStamp, String taggerID) {
		this.type = "tagEvent";
		this.recipients = recipients;
		this.pollID = pollID;
		this.pollTimeStamp = pollTimeStamp;
		this.taggerID = taggerID;
	}

	public Map<String, String> turnIntoHashMapForRedis() {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", this.getType());
		myMap.put("recipients", this.getRecipients());
		myMap.put("pollID", this.getPollID());
		myMap.put("pollTimeStamp", this.getPollTimeStamp());
		myMap.put("taggerID", this.getTaggerID());
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

	public String getPollTimeStamp() {
		return pollTimeStamp;
	}

	public void setPollTimeStamp(String pollTimeStamp) {
		this.pollTimeStamp = pollTimeStamp;
	}

	public String getTaggerID() {
		return taggerID;
	}

	public void setTaggerID(String taggerID) {
		this.taggerID = taggerID;
	}	
		
}
