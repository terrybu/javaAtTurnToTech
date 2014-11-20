package com.eventprocessor.main;

public class EventSubThread  implements Runnable {

private String name;
	
    public EventSubThread (String name) {
    	this.name = name;
    }
	
    public void run() {
        try {
    		System.out.println("From EventSub Thread: " + this.name);
//    		TerryRedis.jedis.lrem(TerryRedis.key, 0, this.name);
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
	}
	
}
