import redis.clients.jedis.*;

public class JedidsPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Jedis jedis = new Jedis("localhost");
		
		//You can see that Sets never add two same elements twice
		jedis.sadd("fruits", "pineapple");
		System.out.println("Fruits set: " + jedis.smembers("fruits"));
		System.out.println("Fruits set count: " + jedis.scard("fruits")); //scard key is like len for Sets
		
		
		jedis.close();
	}

}
