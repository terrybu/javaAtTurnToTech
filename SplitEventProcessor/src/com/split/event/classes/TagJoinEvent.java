package com.split.event.classes;

import java.util.HashMap;
import java.util.Map;

public class TagJoinEvent {
	private String type;
	private String[] recipients;
	private String taggedContact;
	
	public TagJoinEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TagJoinEvent(String[] recipients, String taggedContact) {
		super();
		this.type = "tagJoinEvent";
		this.recipients = recipients;
		this.taggedContact = taggedContact;
	}
	
	public Map<String, String> turnIntoHashMapForRedis() {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", this.getType());
		myMap.put("recipients", this.getRecipients()[0]);
		myMap.put("taggedContact", this.getTaggedContact());
		
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

	public String getTaggedContact() {
		return taggedContact;
	}

	public void setTaggedContact(String taggedContact) {
		this.taggedContact = taggedContact;
	}
	
}
