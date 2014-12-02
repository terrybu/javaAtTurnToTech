package com.split.eventobjects.classes;

public class TagJoinEvent {
	public String type;
	public String[] recipients;
	public String taggedContact;
	
	public TagJoinEvent(String type, String[] recipients, String taggedContact) {
		super();
		this.type = type;
		this.recipients = recipients;
		this.taggedContact = taggedContact;
	}
	
}
