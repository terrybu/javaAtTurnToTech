import java.util.Random;

import redis.clients.jedis.*;

public class JedidsPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Jedis jedis = new Jedis("localhost");
		
		//You can see that Sets never add two same elements twice
		jedis.sadd("fruits", "pineapple");
		System.out.println("Fruits set: " + jedis.smembers("fruits"));
		System.out.println("Fruits set count: " + jedis.scard("fruits")); //scard key is like len for Sets
//      Lists don't care about uniqueness. So you can end up adding duplicate items
		
		
		String key = "ninjas";	
    	
		jedis.rpush(key, "Sasuke");
		System.out.println("ninjas List in Redis: " + jedis.lrange(key, 0, -1));
		System.out.println("ninjas list length: " + jedis.llen(key));
		
		//From TerryRedis
//    	Random random = new Random();
//    	String randomString = TerryRedis.generateString(random, "abcdefghijklmnopqrstuvwxyz", 5);
//    	
//		String key = "notifications";
//		jedis.rpush(key, randomString);
//		System.out.println("Notifications List in Redis: " + jedis.lrange(key, 0, -1));
//		System.out.println("Notifications list length: " + jedis.llen(key));
		
		jedis.close();
	}

    private static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
	
}
