package com.split.event.classes;

public class TagJoinEvent {
	private String type;
	private String[] recipients;
	private String taggedContact;
	
	public TagJoinEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TagJoinEvent(String type, String[] recipients, String taggedContact) {
		super();
		this.type = type;
		this.recipients = recipients;
		this.taggedContact = taggedContact;
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

	public String getTaggedContact() {
		return taggedContact;
	}

	public void setTaggedContact(String taggedContact) {
		this.taggedContact = taggedContact;
	}
	
}
