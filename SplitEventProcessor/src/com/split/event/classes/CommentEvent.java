package com.split.event.classes;

import java.util.HashMap;
import java.util.Map;

public class CommentEvent {
	private String type;
	private String[] recipients; 
	private String[] commentAuthors;
	private String pollID;
	private String commentID;
	private String pollTimeStamp; 
	private String commentTimeStamp;
	
	
	public CommentEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentEvent(String[] recipients, String[] commentAuthors, String pollID, String commentID,String pollTimeStamp, String commentTimeStamp) {
		super();
		this.type = "commentEvent";
		this.recipients = recipients;
		this.commentAuthors = commentAuthors;
		this.pollID = pollID;
		this.commentID = commentID;
		this.pollTimeStamp = pollTimeStamp;
		this.commentTimeStamp = commentTimeStamp;
	}
	
	public Map<String, String> turnIntoHashMapForRedis () {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", this.getType());
		myMap.put("recipients", this.getRecipients()[0]);
		myMap.put("commentAuthors", this.getCommentAuthors()[0]);
		myMap.put("pollID", this.getPollID());
		myMap.put("pollTimeStamp", this.getPollTimeStamp());
		myMap.put("commentID", this.getCommentID());
		myMap.put("commentTimeStamp", this.getCommentTimeStamp());

		return myMap;
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

	public String[] getCommentAuthors() {
		return commentAuthors;
	}

	public void setCommentAuthors(String[] commentAuthors) {
		this.commentAuthors = commentAuthors;
	}

	public String getPollID() {
		return pollID;
	}

	public void setPollID(String pollID) {
		this.pollID = pollID;
	}

	public String getCommentID() {
		return commentID;
	}

	public void setCommentID(String commentID) {
		this.commentID = commentID;
	}

	public String getPollTimeStamp() {
		return pollTimeStamp;
	}

	public void setPollTimeStamp(String pollTimeStamp) {
		this.pollTimeStamp = pollTimeStamp;
	}

	public String getCommentTimeStamp() {
		return commentTimeStamp;
	}

	public void setCommentTimeStamp(String commentTimeStamp) {
		this.commentTimeStamp = commentTimeStamp;
	} 
	
}
