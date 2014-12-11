package com.split.event.redisConverter;
import java.util.Map;
import redis.clients.jedis.Jedis;
import com.split.event.classes.*;


public class EventRedisConverter {
	public static Jedis jedis;

	public static void sendEventObjectToRedis (CommentEvent commentEvent) {
		Map <String, String> myEventHash = commentEvent.turnIntoHashMapForRedis();
		EventRedisConverter.createHashRedisWithEventsKey(myEventHash);
	}
	
	public static void sendEventObjectToRedis (FriendPollEvent friendPollEvent) {
		Map <String, String> myEventHash = friendPollEvent.turnIntoHashMapForRedis();
		EventRedisConverter.createHashRedisWithEventsKey(myEventHash);
	}
	
	public static void sendEventObjectToRedis (LikeEvent likeEvent) {
		Map <String, String> myEventHash = likeEvent.turnIntoHashMapForRedis();
		EventRedisConverter.createHashRedisWithEventsKey(myEventHash);
	}
	
	public static void sendEventObjectToRedis (TagEvent tagEvent) {
		Map <String, String> myEventHash = tagEvent.turnIntoHashMapForRedis();
		EventRedisConverter.createHashRedisWithEventsKey(myEventHash);
	}
	
	public static void sendEventObjectToRedis (TagJoinEvent tagJoinEvent) {
		Map <String, String> myEventHash = tagJoinEvent.turnIntoHashMapForRedis();
		EventRedisConverter.createHashRedisWithEventsKey(myEventHash);
	}
	
	public static void sendEventObjectToRedis (VoteEvent voteEvent) {
		Map <String, String> myEventHash = voteEvent.turnIntoHashMapForRedis();
		EventRedisConverter.createHashRedisWithEventsKey(myEventHash);
	}
	
	
	public static void createHashRedisWithEventsKey (Map <String, String> myEventHash) {
		jedis = new Jedis("localhost");
		long eventsCount = jedis.llen("events");
		String uniqueKeyForEventObject = "events:" +eventsCount;
		jedis.hmset(uniqueKeyForEventObject,  myEventHash);		
		jedis.rpush("events", uniqueKeyForEventObject);
		jedis.close();
	}
	
	
	
}
