package com.split.eventobjects;
import java.util.HashMap;
import java.util.Map;

import com.split.eventobjects.classes.*;

import redis.clients.jedis.*;

public class EventClassesTestMain {

	public static Jedis jedis;
	public static long recipientsKeyCounter;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jedis = new Jedis("localhost");
		
		
		TagEvent dummyTagEvent = new TagEvent("tagEvent", new String[]{"kyuhyunID", "guraID", "jongshinID"}, "34", "2013-9-20", "terryID");
		FriendPollEvent dummyFriendPollEvent = new FriendPollEvent("friendPollEvent", new String[]{"blahID", "britishID"}, "27", "victoria", "2015-1-1");
		TagJoinEvent dummyTagJoinEvent = new TagJoinEvent("tagJoinEvent", new String[]{"jeremyID", "johnsonID"}, "pikachuID");
		
		Map <String, String> hashForDummyTagEvent = EventClassesTestMain.turnTagEventObjectIntoHashMap(dummyTagEvent);
		Map <String, String> hashForDummyFriendPollEvent = EventClassesTestMain.turnFriendPollEventIntoHashMap(dummyFriendPollEvent);
		Map <String, String> hashForDummyTagJoinEvent = EventClassesTestMain.turnTagJoinEventIntoHashMap(dummyTagJoinEvent);

		EventClassesTestMain.createHashinRedisWithUniqueKey(hashForDummyTagEvent);
		EventClassesTestMain.createHashinRedisWithUniqueKey(hashForDummyFriendPollEvent);
		EventClassesTestMain.createHashinRedisWithUniqueKey(hashForDummyTagJoinEvent);

		System.out.println("Done with TerryEventClassesTest");
		
		jedis.close();
	}
	
	public static void createHashinRedisWithUniqueKey (Map <String, String> myEventHash) {
		long eventsCount = jedis.llen("events");
		String uniqueKeyForEventObject = "event:" +eventsCount;
		jedis.hmset(uniqueKeyForEventObject,  myEventHash);		
		jedis.rpush("events", uniqueKeyForEventObject);
	}
	
	public static Map<String, String> turnTagEventObjectIntoHashMap(TagEvent tagEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", tagEvent.type);
		myMap.put("recipients", EventClassesTestMain.turnRecipientsArrayIntoRedisSetAndReturnKey(tagEvent.recipients));
		myMap.put("pollID", tagEvent.pollID);
		myMap.put("pollTimeStamp", tagEvent.pollTimeStamp);
		myMap.put("taggerID", tagEvent.taggerID);
		
		return myMap;
	}
	
	public static Map<String, String> turnFriendPollEventIntoHashMap(FriendPollEvent friendPollEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", friendPollEvent.type);
		myMap.put("recipients", EventClassesTestMain.turnRecipientsArrayIntoRedisSetAndReturnKey(friendPollEvent.recipients));
		myMap.put("pollID", friendPollEvent.pollID);
		myMap.put("pollTimeStamp", friendPollEvent.pollTimeStamp);
		myMap.put("pollAuthor", friendPollEvent.pollAuthor);
		
		return myMap;
	}
	
	public static Map<String, String> turnTagJoinEventIntoHashMap(TagJoinEvent tagJoinEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", tagJoinEvent.type);
		myMap.put("recipients", EventClassesTestMain.turnRecipientsArrayIntoRedisSetAndReturnKey(tagJoinEvent.recipients));
		myMap.put("taggedContact", tagJoinEvent.taggedContact);
		
		return myMap;
	 }
	
	
	public static String turnRecipientsArrayIntoRedisSetAndReturnKey (String[] recipientsArray){
		recipientsKeyCounter += 1;
		String recipientsSetKey = "recipients" + recipientsKeyCounter;
		String concatenatedRecipients = "";
		
		for (int i=0; i < recipientsArray.length; i++) {
			if (concatenatedRecipients.isEmpty()) {
				concatenatedRecipients += recipientsArray[i];
			}
			else {
				concatenatedRecipients += " " + recipientsArray[i];
			}
		}
		
		jedis.sadd(recipientsSetKey, concatenatedRecipients);
		
		return recipientsSetKey;
	}

}
