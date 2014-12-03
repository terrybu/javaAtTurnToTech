package com.split.event.processors;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class TagJoinEventProcessor implements Runnable {

	public String eventKey;
	public Jedis jedis;

    public TagJoinEventProcessor (String eventKey) {
    	this.eventKey = eventKey;
    	this.jedis = EventProcessorMain.jedis;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
    	Map <String, String> eventObjectHash = jedis.hgetAll(eventKey);
    	System.out.println("Event Key:" + eventKey + " EventType: " + eventObjectHash.get("type"));
		System.out.println(eventObjectHash);
		
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
