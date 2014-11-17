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
		
		String key = "fruits";
		
//      Lists don't care about uniquness. So you keep adding same strings by doing this below
//		jedis.rpush("ninjas", "Naruto", "Sasuke");
//		System.out.println("Ninjas list from Redis: " + jedis.lrange("ninjas", 0, -1));
//		System.out.println("Ninjas list length: " + jedis.llen("ninjas"));
		
		
		//You can see that Sets never add two same elements twice
		jedis.sadd("fruits", "pineapple");
		System.out.println("Fruits set: " + jedis.smembers("fruits"));
		System.out.println("Fruits set count: " + jedis.scard(key)); //scard key is like len for Sets
		
		
		
	}

}
