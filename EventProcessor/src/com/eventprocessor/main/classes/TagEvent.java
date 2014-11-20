package com.eventprocessor.main.classes;

public class TagEvent {
		public String type;
		public String[] recipients;
		public String pollID;
		public String pollTimeStamp;
		public String taggerID;

		public TagEvent(String type, String[] recipients, String pollID, String pollTimeStamp, String taggerID) {
			this.type = type;
			this.recipients = recipients;
			this.pollID = pollID;
			this.pollTimeStamp = pollTimeStamp;
			this.taggerID = taggerID;
		}	
		
}
