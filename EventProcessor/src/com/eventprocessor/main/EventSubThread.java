package com.eventprocessor.main;

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
        	Map <String, String> myEventHash = jedis.hgetAll(key);
        	System.out.println(myEventHash);
        	
        	
        	//    		TerryRedis.jedis.lrem(TerryRedis.key, 0, this.name);
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
	}
	
}
