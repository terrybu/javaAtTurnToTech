package com.split.event.classes;

public class FriendPollEvent {
	private String type;
	private String[] recipients;
	private String pollID; 
	private String pollAuthor;
	private String pollTimeStamp;
	
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

	public FriendPollEvent(String type, String[] recipients, String pollID, String pollAuthor,
			String pollTimeStamp) {
		super();
		this.type = type;
		this.recipients = recipients;
		this.pollID = pollID; 
		this.pollAuthor = pollAuthor;
		this.pollTimeStamp = pollTimeStamp;
	}
}