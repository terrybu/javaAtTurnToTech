package com.eventprocessor.main.classes;

public class VoteEvent {
	public String type;
	public String[] recipients; 
	public String[] voters; 
	public String pollID; 
	public String pollTimeStamp;
	
	public VoteEvent(String type, String[] recipients, String[] voters, String pollID, String pollTimeStamp) {
		super();
		this.type = type;
		this.recipients = recipients;
		this.voters = voters;
		this.pollID = pollID;
		this.pollTimeStamp = pollTimeStamp;
	} 
	
}
