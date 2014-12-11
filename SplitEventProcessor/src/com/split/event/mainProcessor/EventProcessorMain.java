package com.split.event.mainProcessor;
import java.util.List;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.split.event.processors.CommentEventProcessor;
import com.split.event.processors.FriendPollEventProcessor;
import com.split.event.processors.LikeEventProcessor;
import com.split.event.processors.TagEventProcessor;
import com.split.event.processors.TagJoinEventProcessor;
import com.split.event.processors.VoteEventProcessor;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class EventProcessorMain {

	public static JedisPool jedisPool;
	public static Jedis jedis;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(8);
		config.setBlockWhenExhausted(true);
		//Adding testOnBorrow=true so that connections that time out are handled correctly
		config.setTestOnBorrow(true);
		jedisPool = new JedisPool(config, "localhost");
		jedis = jedisPool.getResource();
		
		while (true) {
			if (jedis.llen("events") > 0 ) {
				List <String> listOfEventKeys = jedis.lrange("events", 0, -1);
				for (String eventKey: listOfEventKeys) {
		        	Map <String, String> myEventObjectHash = jedis.hgetAll(eventKey);
		        	String eventType = myEventObjectHash.get("type");
		        	EventType type = EventType.getEventType(eventType); 
		    		switch (type) {
		    		case TagEvent:
						Thread tagEventThread = new Thread(new TagEventProcessor(eventKey));
						tagEventThread.run();
						break;
					case TagJoinEvent:
						Thread tagJoinEventThread = new Thread(new TagJoinEventProcessor(eventKey));
						tagJoinEventThread.run();
						break;
					case VoteEvent:
						Thread voteEventThread = new Thread(new VoteEventProcessor(eventKey));
						voteEventThread.run();
						break;
					case CommentEvent:
						Thread commentEventThread = new Thread(new CommentEventProcessor(eventKey));
						commentEventThread.run();
						break;
					case LikeEvent:
						Thread likeEventThread = new Thread(new LikeEventProcessor(eventKey));
						likeEventThread.run();
						break;
					case FriendPollEvent:
						Thread friendPollEvent = new Thread(new FriendPollEventProcessor(eventKey));
						friendPollEvent.run();
						break;
					default:
						break;
					}
				}
			}
			
			else {
				System.out.println("Events Processor: Listening .... Don't do anything because Events queue is empty");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
		
		
		
	}

}