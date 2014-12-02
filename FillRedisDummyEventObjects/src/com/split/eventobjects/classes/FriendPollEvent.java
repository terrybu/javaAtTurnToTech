package com.split.eventobjects.classes;

public class FriendPollEvent {
	public String type;
	public String[] recipients;
	public String pollID; 
	public String pollAuthor;
	public String pollTimeStamp;

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
