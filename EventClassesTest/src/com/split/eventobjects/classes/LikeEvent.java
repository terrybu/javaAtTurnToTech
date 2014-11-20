package com.split.eventobjects.classes;

public class LikeEvent {
	public String type;
	public String[] recipients; 
	public String[] likers; 
	public String commentID;
	public String commentTimeStamp;
	public String pollID;
	public String pollTimeStamp;
	
	public LikeEvent(String type, String[] recipients, String[] likers, String commentID, String commentTimeStamp, String pollID,
			String pollTimeStamp) {
		super();
		this.type = type;
		this.recipients = recipients;
		this.likers = likers;
		this.commentID = commentID;
		this.commentTimeStamp = commentTimeStamp;
		this.pollID = pollID;
		this.pollTimeStamp = pollTimeStamp;
	} 
	
	
	
	
	
	
}
