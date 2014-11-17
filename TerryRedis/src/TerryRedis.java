import java.util.Random;
import redis.clients.jedis.*;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class TerryRedis {
	private static JedisPool jedisPool;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(8);
		config.setBlockWhenExhausted(true);
		//Adding testOnBorrow=true so that connections that time out are handled correctly
		config.setTestOnBorrow(true);

		jedisPool = new JedisPool(config, "localhost");
		Jedis jedis = jedisPool.getResource();
		
//      Lists don't care about uniqueness. So you can end up adding duplicate items
		
		String key = "notifications";	
    	Random random = new Random();
    	String randomString = TerryRedis.generateString(random, "abcdefghijklmnopqrstuvwxyz", 5);
    	
		jedis.rpush(key, randomString);
		System.out.println("Notifications List in Redis: " + jedis.lrange(key, 0, -1));
		System.out.println("Notifications list length: " + jedis.llen(key));
		
		
		
		
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
