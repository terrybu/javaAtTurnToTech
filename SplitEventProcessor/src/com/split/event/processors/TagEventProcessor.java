package com.split.event.processors;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class TagEventProcessor implements Runnable {

	public String eventKey;
	public Jedis jedis;

    public TagEventProcessor (String eventKey) {
    	this.eventKey = eventKey;
    	this.jedis = EventProcessorMain.jedis;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
    	Map <String, String> eventObjectHash = jedis.hgetAll(eventKey);
    	
    	//In To, Subject, Body Email Format
    	System.out.println("\n");
    	    
    	List <String> taggedContactsList = jedis.lrange(eventObjectHash.get("recipients"), 0, -1);
    	
		System.out.println("TO: " +   taggedContactsList.toString()      );
		System.out.println("SUBJECT: Somebody tagged you in a poll!");
		System.out.println("BODY: ");
		System.out.println("Poll ID:" + eventObjectHash.get("pollID"));
		System.out.println("Poll TimeStamp:" + eventObjectHash.get("pollTimeStamp"));
		System.out.println("Tagger ID:" + eventObjectHash.get("taggerID"));

		//and then we remove the event object out of the queue
		//first, from the events queue
		jedis.lrem("events", 0, eventKey);			
		//but there's still the hash existing, so we delete out the hash too
		jedis.del(eventKey);
		if (!jedis.exists(eventKey)) {
			System.out.println(eventKey + " removed correctly");
		}
	}

}
