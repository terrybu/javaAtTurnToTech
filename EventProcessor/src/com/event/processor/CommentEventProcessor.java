package com.event.processor;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class CommentEventProcessor implements Runnable {
	public String eventKey;
	public Jedis jedis;

    public CommentEventProcessor (String eventKey) {
    	this.eventKey = eventKey;
    	this.jedis = EventProcessor.jedis;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
    	Map <String, String> eventObjectHash = jedis.hgetAll(eventKey);
//    	System.out.println("Event Key is " + eventKey + " EventType: " + eventObjectHash.get("type"));
    	
    	//In To, Subject, Body Email Format
    	System.out.println("\n");
		System.out.println("TO: " + eventObjectHash.get("recipients"));
		System.out.println("SUBJECT: Somebody commented on your poll!");
		System.out.println("BODY: ");
		System.out.println("Poll ID:" + eventObjectHash.get("pollID"));
		System.out.println("Comment ID:" + eventObjectHash.get("commentID"));
		System.out.println("Poll TimeStamp:" + eventObjectHash.get("pollTimeStamp"));
		System.out.println("Comment TimeStamp:" + eventObjectHash.get("commentTimeStamp"));
		System.out.println("Comment Author:" + eventObjectHash.get("commentAuthors"));

		//and then we remove the event object out of the queue
		//first, from the events queue
		jedis.lrem("events", 0, eventKey);			
		//but there's still the hash existing, so we delete out the hash too
		jedis.del(eventKey);
		if (!jedis.exists(eventKey)) {
			System.out.println("Removed hash with key: " + eventKey + " correctly");
		}

	}

}