package com.eventprocessor.main;

import com.eventprocessor.main.classes.*;
import java.util.Map;
import redis.clients.jedis.Jedis;

public class EventSubThread  implements Runnable {

	public String key;
	public static Jedis jedis;

    public EventSubThread (String key) {
    	this.key = key;
    	this.jedis = EventProcessorMain.jedis;
    }
	
    public void run() {
        try {
        	//okay, we have the key of the Hash object ... so we can just get to Events List and pull those stuff out
        	Map <String, String> myEventObjectHash = jedis.hgetAll(key);
			System.out.println("Found Event object with type: " + myEventObjectHash.get("type"));
			System.out.println(myEventObjectHash);
			
        	switch (myEventObjectHash.get("type")) {
				case "tagEvent":
					
					//we do the "send notification" logic here
					
					break;
	
				case "tagJoinEvent":
					//we do the "send notification" logic here
					
					break;
					
				case "voteEvent":
					
					break;
					
				case "commentEvent":
					
					break;
				
				case "likeEvent":
					
					break;
					
				case "friendPollEvent":
					//we do the "send notification" logic here

					break;
					
				default:
					break;
			}
			//and then we remove the event object out of the queue
			//first, from the events queue
			jedis.lrem("events", 0, key);			
			//but there's still the hash existing, so we delete out the hash too
			jedis.del(key);
			if (!jedis.exists(key)) {
				System.out.println("Removed hash with key: " + key + " correctly");
			}
			System.out.println("\n");

        } catch(Exception ex) {
            ex.printStackTrace();
        } 
	}
	
}
