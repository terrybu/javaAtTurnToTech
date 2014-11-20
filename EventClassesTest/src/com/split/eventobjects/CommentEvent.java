package com.split.eventobjects;

public class CommentEvent {
	public String type;
	public String[] recipients; 
	public String[] commentAuthors;
	public String pollID;
	public String commentID;
	public String pollTimeStamp; 
	public String commentTimeStamp;
	
	public CommentEvent(String type, String[] recipients, String[] commentAuthors, String pollID, String commentID,String pollTimeStamp, String commentTimeStamp) {
		super();
		this.type = type;
		this.recipients = recipients;
		this.commentAuthors = commentAuthors;
		this.pollID = pollID;
		this.commentID = commentID;
		this.pollTimeStamp = pollTimeStamp;
		this.commentTimeStamp = commentTimeStamp;
	} 
	
}
