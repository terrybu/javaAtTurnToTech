package com.eventprocessor.main;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

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
			if (jedis.llen("Events") > 0 ) {
				List <String> myListOfEvents = jedis.lrange("Events", 0, -1);
//				System.out.println(myListOfEvents);
				
				for (String event: myListOfEvents) {
		    		Thread eventSubThread = new Thread(new EventSubThread(event));
		    		eventSubThread.run();
				}
				
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
