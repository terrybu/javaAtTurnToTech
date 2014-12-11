package com.split.event.processors;

import java.util.List;
import java.util.Map;

import com.split.event.mainProcessor.EventProcessorMain;

import redis.clients.jedis.Jedis;

public class FriendPollEventProcessor implements Runnable {
	public String eventKey;
	public Jedis jedis;

    public FriendPollEventProcessor (String eventKey) {
    	this.eventKey = eventKey;
    	this.jedis = EventProcessorMain.jedis;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
    	Map <String, String> eventObjectHash = jedis.hgetAll(eventKey);

    	//In To, Subject, Body Email Format
    	System.out.println("\n");
    	
    	List <String> recipientsList = jedis.lrange(eventObjectHash.get("recipients"), 0, -1);
    	
		System.out.println("TO: " +     	recipientsList       );
		String subject = "SUBJECT: Your Friend Created a Poll!";
		System.out.println(subject);
		System.out.println("BODY: ");
		System.out.println("Poll ID:" + eventObjectHash.get("pollID"));
		System.out.println("Poll Author:" + eventObjectHash.get("pollAuthor"));
		System.out.println("Poll TimeStamp:" + eventObjectHash.get("pollTimeStamp"));
		String deviceToken = "<5cf63347 7edaf7c1 1f1159b5 e173723d ffd0261a 80921665 9f42d56d dca38a63>";
		APNSDriver apns = new APNSDriver();			
		try {
			apns.initializeAPNS();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			apns.sendPushNotification(subject, deviceToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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