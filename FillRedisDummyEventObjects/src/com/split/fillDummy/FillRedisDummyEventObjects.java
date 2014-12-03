package com.split.fillDummy;
import java.util.Map;
import redis.clients.jedis.Jedis;
import com.split.event.classes.*;

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
		CommentEvent dummyCommentEvent = new CommentEvent(new String[]{recipientPollAuthor}, new String[]{commentAuthor}, somePollID, commentID, myPollTimeStamp, myCommentTimeStamp);
		Map <String, String> hashForDummyCommentEvent = dummyCommentEvent.turnIntoHashMapForRedis();
		FillRedisDummyEventObjects.createHashRedisWithEventsKey(hashForDummyCommentEvent);
		
		String someUserID = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7";
		String somePollID2 = someUserID + ":polls:pollID:1";
		FriendPollEvent dummyFriendPollEvent = new FriendPollEvent(someUserID + ":firstDegreeFriends", somePollID2, someUserID, jedis.hget(somePollID2, "polltimeStamp"));
		Map <String, String> hashForDummyFriendPollEvent = dummyFriendPollEvent.turnFriendPollEventIntoHashMap();
		FillRedisDummyEventObjects.createHashRedisWithEventsKey(hashForDummyFriendPollEvent);
		
		String commentAuthor3 = "1619729e-9c9b-4046-b43f-459d5f8e8176";
		String commentLikerID3 = "abd1807e-1851-472c-a54d-b6e9b6b02799";
		String commentID3 = "1619729e-9c9b-4046-b43f-459d5f8e8176:polls:pollID:1:comments:commentID:1";
		String commentTimeStamp3 = jedis.hget(commentID3, "commentTimeStamp");
		String pollID3 = "1619729e-9c9b-4046-b43f-459d5f8e8176:polls:pollID:1";
		String pollTimeStamp3 = jedis.hget(pollID3, "polltimeStamp");
		LikeEvent dummyLikeEvent = new LikeEvent(new String[] {commentAuthor3}, new String[] {commentLikerID3}, commentID3, commentTimeStamp3, pollID3, pollTimeStamp3);
		FillRedisDummyEventObjects.sendEventObjectToRedis(dummyLikeEvent);
		
		System.out.println("Done with Filling Redis With Dummy Event Objects");
		jedis.close();
	}
	
	public static void sendEventObjectToRedis (LikeEvent likeEvent) {
		Map <String, String> myEventHash = likeEvent.turnIntoHashMapForRedis();
		FillRedisDummyEventObjects.createHashRedisWithEventsKey(myEventHash);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void createHashRedisWithEventsKey (Map <String, String> myEventHash) {
		long eventsCount = jedis.llen("events");
		String uniqueKeyForEventObject = "events:" +eventsCount;
		jedis.hmset(uniqueKeyForEventObject,  myEventHash);		
		jedis.rpush("events", uniqueKeyForEventObject);
	}
	
	
	
//	public static String turnRecipientsArrayIntoRedisSetAndReturnKey (String[] recipientsArray){
//		recipientsKeyCounter += 1;
//		String recipientsSetKey = "recipients" + recipientsKeyCounter;
//		String concatenatedRecipients = "";
//		
//		for (int i=0; i < recipientsArray.length; i++) {
//			if (concatenatedRecipients.isEmpty()) {
//				concatenatedRecipients += recipientsArray[i];
//			}
//			else {
//				concatenatedRecipients += " " + recipientsArray[i];
//			}
//		}
//		
//		jedis.sadd(recipientsSetKey, concatenatedRecipients);
//		
//		return recipientsSetKey;
//	}

}
