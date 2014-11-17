package com.terryredis.main;
import java.util.List;
import redis.clients.jedis.*;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class TerryRedis {
	public static JedisPool jedisPool;
	public static Jedis jedis;
	public static String key;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub	
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(8);
		config.setBlockWhenExhausted(true);
		//Adding testOnBorrow=true so that connections that time out are handled correctly
		config.setTestOnBorrow(true);
		jedisPool = new JedisPool(config, "localhost");
		jedis = jedisPool.getResource();
		key = "notifications";
		
		//this program, after it's started, will keep checking to see if the Notifications list in Redis is ever >= limit
		//once it hits limit, it will grab all the strings and run a console log thread for each string
		
		int limit = 20;
		System.out.println("Running TerryRedis Program ... Waiting for " + key + " to hit " + limit + " elements limit");

		while (true) {			
			if (jedis.llen(key) >= limit) {
				
				System.out.println("Detected that List " + key + " hit limit at " + limit);
				List <String> notificationsList = jedis.lrange(key, 0, -1);
				
				for (int i = 0; i < limit; i++) {
		    		Thread LogNotifyThread = new Thread(new LogNotifyThread(notificationsList.get(i)));
		    		LogNotifyThread.run();
				}
				
				break;
			}
			else {
				System.out.println("List count at " + jedis.llen(key) + " elements: Waiting 2 seconds");
				Thread.sleep(3000);
			}			
		}
		
		System.out.println("End of Program: Length of List: " + jedis.llen(key));
		jedisPool.returnResource(jedis);
	}
	

}
