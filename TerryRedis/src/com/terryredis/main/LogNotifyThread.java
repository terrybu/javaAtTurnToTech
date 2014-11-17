package com.terryredis.main;

public class LogNotifyThread implements Runnable {

private String name;
	
    public LogNotifyThread (String name) {
    	this.name = name;
    }
	
    public void run() {
        try {
    		System.out.println("From LogNotify Thread: " + this.name);
    		//Now do something to remove this element from the Redis list
        
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
	}

	
}
