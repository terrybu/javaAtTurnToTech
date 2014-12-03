package com.split.event.classes;

public class VoteEvent {
	private String type;
	private String[] recipients; 
	private String[] voters; 
	private String pollID; 
	private String pollTimeStamp;
	
	public VoteEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoteEvent(String[] recipients, String[] voters, String pollID, String pollTimeStamp) {
		super();
		this.type = "voteEvent";
		this.recipients = recipients;
		this.voters = voters;
		this.pollID = pollID;
		this.pollTimeStamp = pollTimeStamp;
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

	public String[] getVoters() {
		return voters;
	}

	public void setVoters(String[] voters) {
		this.voters = voters;
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
	
}
