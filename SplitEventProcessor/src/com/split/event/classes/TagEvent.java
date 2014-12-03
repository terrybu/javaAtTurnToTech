package com.split.event.classes;

public class TagEvent {
	private String type;
	private String[] recipients;
	private String pollID;
	private String pollTimeStamp;
	private String taggerID;

	public TagEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TagEvent(String type, String[] recipients, String pollID, String pollTimeStamp, String taggerID) {
		this.type = type;
		this.recipients = recipients;
		this.pollID = pollID;
		this.pollTimeStamp = pollTimeStamp;
		this.taggerID = taggerID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getRecipients() {
		return recipients;
	}

	public void setRecipients(String[] recipients) {
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
