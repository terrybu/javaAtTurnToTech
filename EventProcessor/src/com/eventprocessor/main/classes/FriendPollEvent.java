package com.eventprocessor.main.classes;

public class FriendPollEvent {
	public String type;
	public String[] recipients; 
	public String pollAuthor;
	public String pollTimeStamp;

	public FriendPollEvent(String type, String[] recipients, String pollAuthor,
			String pollTimeStamp) {
		super();
		this.type = type;
		this.recipients = recipients;
		this.pollAuthor = pollAuthor;
		this.pollTimeStamp = pollTimeStamp;
	}
}
