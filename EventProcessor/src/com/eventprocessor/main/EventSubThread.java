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
        	
        	switch (myEventObjectHash.get("type")) {
				case "tagEvent":
					
					break;
	
				case "tagJoinEvent":
					
					break;
					
				case "voteEvent":
					
					break;
					
				case "commentEvent":
					
					break;
				
				case "likeEvent":
					
					break;
					
				case "friendPollEvent":
					
					break;
					
				default:
					break;
			}
        	
        	
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
	}
	
}
