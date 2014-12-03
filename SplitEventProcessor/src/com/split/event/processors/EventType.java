package com.split.event.processors;

public enum EventType {
	
	TagEvent("tagEvent"), 
	TagJoinEvent("tagJoinEvent"), 
	VoteEvent("voteEvent"), 
	CommentEvent("commentEvent"), 
	LikeEvent("likeEvent"), 
	FriendPollEvent("friendPollEvent");

	String name;

	EventType(String name) {
		this.name = name;
	} 

	
	public String getName() {
		return name;
	}


	public static EventType getEventType(String type){
		
		if(type == null || type == "") throw new IllegalArgumentException("type cannont be empty or null");
		
		if(type.equals(TagEvent.getName())){
			return TagEvent;
		} 
		else if(type.equals(TagJoinEvent.getName())){
			return TagJoinEvent;
		} 
		else if(type.equals(VoteEvent.getName())){
			return VoteEvent;
		} 
		else if(type.equals(CommentEvent.getName())){
			return CommentEvent;
		} 
		else if(type.equals(LikeEvent.getName())){
			return LikeEvent;
		} 
		else if(type.equals(FriendPollEvent.getName())){
			return FriendPollEvent;
		} 
		
		throw new RuntimeException("unable to create neumerator value...");
	}
	

	
}
