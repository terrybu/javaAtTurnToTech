package com.split.eventobjects;
import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.*;

public class EventClassesTestMain {

	public static Jedis jedis;
	public static long eventsCount;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jedis = new Jedis("localhost");

		eventsCount = jedis.llen("Events");
		String keyForEventObject = "Event:" + eventsCount;
		TagEvent tagEvent = new TagEvent("tagEvent", new String[]{"kyuhyunID", "guraID", "jongshinID"}, "34", "2013-9-20", "terryID");

		Map <String, String> myMap = EventClassesTestMain.turnEventObjectIntoHashMap(tagEvent);
		
		//we make the Event object hash
		jedis.hmset(keyForEventObject,  myMap);
		
		//we push the Event object hash into the Events List
		jedis.rpush("Events", keyForEventObject);
		
		System.out.println("Done with TerryEventClassesTest");
		
		jedis.close();
	}
	
	public static Map<String, String> turnEventObjectIntoHashMap(TagEvent tagEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", tagEvent.type);
		myMap.put("pollID", tagEvent.pollID);
		myMap.put("pollTimeStamp", tagEvent.pollTimeStamp);
		myMap.put("recipients", EventClassesTestMain.turnRecipientsArrayIntoRedisSetAndReturnKey(tagEvent.recipients));
		myMap.put("taggerID", tagEvent.taggerID);
		
		return myMap;
	}
	
	
	public static String turnRecipientsArrayIntoRedisSetAndReturnKey (String[] recipientsArray){
		String recipientsSetKey = "recipients" + eventsCount;
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
