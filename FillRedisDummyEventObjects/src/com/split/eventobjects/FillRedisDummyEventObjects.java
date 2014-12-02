package com.split.eventobjects;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.split.eventobjects.classes.*;

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
		myMap.put("type", commentEvent.type);
		myMap.put("recipients", commentEvent.recipients[0]);
		myMap.put("commentAuthors", commentEvent.commentAuthors[0]);
		myMap.put("pollID", commentEvent.pollID);
		myMap.put("pollTimeStamp", commentEvent.pollTimeStamp);
		myMap.put("commentID", commentEvent.commentID);
		myMap.put("commentTimeStamp", commentEvent.commentTimeStamp);

		return myMap;
	}
	
	public static Map<String, String> turnTagEventObjectIntoHashMap(TagEvent tagEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", tagEvent.type);
		myMap.put("recipients", FillRedisDummyEventObjects.turnRecipientsArrayIntoRedisSetAndReturnKey(tagEvent.recipients));
		myMap.put("pollID", tagEvent.pollID);
		myMap.put("pollTimeStamp", tagEvent.pollTimeStamp);
		myMap.put("taggerID", tagEvent.taggerID);
		return myMap;
	}
	
	public static Map<String, String> turnFriendPollEventIntoHashMap(FriendPollEvent friendPollEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", friendPollEvent.type);
		myMap.put("recipients", FillRedisDummyEventObjects.turnRecipientsArrayIntoRedisSetAndReturnKey(friendPollEvent.recipients));
		myMap.put("pollID", friendPollEvent.pollID);
		myMap.put("pollTimeStamp", friendPollEvent.pollTimeStamp);
		myMap.put("pollAuthor", friendPollEvent.pollAuthor);
		
		return myMap;
	}
	
	public static Map<String, String> turnTagJoinEventIntoHashMap(TagJoinEvent tagJoinEvent) {
		Map<String, String> myMap = new HashMap<String, String> ();
		myMap.put("type", tagJoinEvent.type);
		myMap.put("recipients", FillRedisDummyEventObjects.turnRecipientsArrayIntoRedisSetAndReturnKey(tagJoinEvent.recipients));
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
