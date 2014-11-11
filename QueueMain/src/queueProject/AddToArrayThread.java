package queueProject;

import java.util.Random;

public class AddToArrayThread implements Runnable{
		
    public void run() {
    	while (true) {
	        try { 
	        	Random random = new Random();
	        	String randomString = this.generateString(random, "abcdefghijklmnopqrstuvwxyz", 5);
	        	Main.queueArray.add(randomString);
	        	System.out.println("Added random string " + randomString + " From Thread 1");
	        	Thread.sleep(5000);
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }
    	}
    }
    
    
    private String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
    
}
