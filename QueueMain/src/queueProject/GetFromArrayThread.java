package queueProject;

public class GetFromArrayThread implements Runnable {

	public void run() {
		while (true) {
	        try {
	        	if (Main.queueArray.isEmpty()) {
	        		System.out.println("From Thread 2: Queue Array empty - do nothing status");
		    		Thread.sleep(1000);		    				    		
		    		//really weird bug
		    		//if I delete these two lines above, the else statements never seems to run ... 
		    		
	        	}
	        	else {
		    		String last = Main.queueArray.get(Main.queueArray.size()-1);	
		    		Thread printNotifyThread = new Thread(new PrintNotifyThread(last));
		    		printNotifyThread.start();	    		
		    		Thread.sleep(1000);
	        	}
	        
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }
		}
    }
	
}
