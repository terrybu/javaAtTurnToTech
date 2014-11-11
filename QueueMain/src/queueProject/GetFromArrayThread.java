package queueProject;

public class GetFromArrayThread implements Runnable {

	public void run() {
		
		while (true) {
	        try {
	    		String last = Main.queueArray.get(Main.queueArray.size()-1);
	    		System.out.println("Thread 2 " + last);   
	    		Thread.sleep(1000);
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        }
		}
    }
	
}
