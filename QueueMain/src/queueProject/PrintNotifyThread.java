package queueProject;

public class PrintNotifyThread implements Runnable {

	private String name;
	
    public PrintNotifyThread(String name) {
    	this.name = name;
    }
	
    public void run() {
        try {
    		System.out.println("From printNotify Thread: " + this.name);
    		Main.queueArray.remove(Main.queueArray.size()-1);
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
	}

	
}
