package com.split.fillDummy;
import java.util.HashMap;
import java.util.Map;

import com.split.event.classes.*;

import redis.clients.jedis.*;

public class FillRedisDummyEventObjects {

	public static Jedis jedis;
	public static long recipientsKeyCounter;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jedis = new Jedis("localhost");

		String somePollID = "1619729e-9c9b-4046-b43f-459d5f8e8176:polls:pollID:1";
		String recipientPollAuthor = "1619729e-9c9b-4046-b43f-459d5f8e8176";
		String commentAuthor = "b6798e6f-ec95-462b-a96a-6e84936d6995";
		String commentID = "1619729e-9c9b-4046-b43f-459d5f8e8176:polls:pollID:1:comments:commentID:11";
		String myPollTimeStamp = jedis.hget(somePollID, "polltimeStamp");
		String myCommentTimeStamp = jedis.hget(commentID, "commentTimeStamp");
		
		CommentEvent dummyCommentEvent = new CommentEvent("commentEvent", new String[]{recipientPollAuthor}, new String[]{commentAuthor}, somePollID, commentID, myPollTimeStamp, myCommentTimeStamp);
		Map <String, String> hashForDummyCommentEvent = FillRedisDummyEventObjects.turnCommentEventObjectIntoHashMap(dummyCommentEvent);
		FillRedisDummyEventObjects.createHashinRedisWithUniqueKey(hashForDummyCommentEvent);
		
		//dummy events filling in below
//		TagEvent dummyTagEvent = new TagEvent("tagEvent", new String[]{"kyuhyunID", "guraID", "jongshinID"}, "34", "2013-9-20", "terryID");
//		FriendPollEvent dummyFriendPollEvent = new FriendPollEvent("friendPollEvent", new String[]{"blahID", "britishID"}, "27", "victoria", "2015-1-1");
//		TagJoinEvent dummyTagJoinEvent = new TagJoinEvent("tagJoinEvent", new String[]{"jeremyID", "johnsonID"}, "pikachuID");
//		
//		Map <String, String> hashForDummyTagEvent = FillRedisDummyEventObjects.turnTagEventObjectIntoHashMap(dummyTagEvent);
//		Map <String, String> hashForDummyFriendPollEvent = FillRedisDummyEventObjects.turnFriendPollEventIntoHashMap(dummyFriendPollEvent);
//		Map <String, String> hashForDummyTagJoinEvent = FillRedisDummyEventObjects.turnTagJoinEventIntoHashMap(dummyTagJoinEvent);
//
//		FillRedisDummyEventObjects.createHashinRedisWithUniqueKey(hashForDummyTagEvent);
//		FillRedisDummyEventObjects.createHashinRedisWithUniqueKey(hashForDummyFriendPollEvent);
//		FillRedisDummyEventObjects.createHashinRedisWithUniqueKey(hashForDummyTagJoinEvent);

		System.out.println("Done with Filling Redis With Dummy Event Objects");
		
		jedis.close();
	}
	
	public static void createHashinRedisWithUniqueKey (Map <String, String> myEventHash) {
		long eventsCount = jedis.llen("events");
		String uniqueKeyForEventObject = "events:" +eventsCount;
		jedis.hmset(uniqueKeyForEventObject,  myEventHash);		
		jedis.rpush("events", uniqueKeyForEventObject);
	}
	
	public static Map<String, String> turnCommentEventObjectIntoHashMap(CommentEvent commentEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", commentEvent.getType());
		myMap.put("recipients", commentEvent.getRecipients());
		myMap.put("commentAuthors", commentEvent.getCommentAuthors());
		myMap.put("pollID", commentEvent.getPollID());
		myMap.put("pollTimeStamp", commentEvent.getPollTimeStamp());
		myMap.put("commentID", commentEvent.getCommentID());
		myMap.put("commentTimeStamp", commentEvent.getCommentTimeStamp());

		return myMap;
	}
	
	public static Map<String, String> turnTagEventObjectIntoHashMap(TagEvent tagEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", tagEvent.getType());
		myMap.put("recipients", FillRedisDummyEventObjects.turnRecipientsArrayIntoRedisSetAndReturnKey(tagEvent.getRecipients()));
		myMap.put("pollID", tagEvent.getPollID());
		myMap.put("pollTimeStamp", tagEvent.getPollTimeStamp());
		myMap.put("taggerID", tagEvent.getTaggerID());
		return myMap;
	}
	
	public static Map<String, String> turnFriendPollEventIntoHashMap(FriendPollEvent friendPollEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", friendPollEvent.getType());
		myMap.put("recipients", FillRedisDummyEventObjects.turnRecipientsArrayIntoRedisSetAndReturnKey(friendPollEvent.getRecipients()));
		myMap.put("pollID", friendPollEvent.getPollID());
		myMap.put("pollTimeStamp", friendPollEvent.getPollTimeStamp());
		myMap.put("pollAuthor", friendPollEvent.getPollAuthor());
		
		return myMap;
	}
	
	public static Map<String, String> turnTagJoinEventIntoHashMap(TagJoinEvent tagJoinEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", tagJoinEvent.getType());
		myMap.put("recipients", FillRedisDummyEventObjects.turnRecipientsArrayIntoRedisSetAndReturnKey(tagJoinEvent.getRecipients()));
		myMap.put("taggedContact", tagJoinEvent.getTaggedContact());
		
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
