package Queue;

public class RunnableProcess implements Runnable {
 
	private String name;
	
    public RunnableProcess(String name) {
    	this.name = name;
    }
 
    public void run() {
        try {
            System.out.println("hi I'm " + name);
            for (int i = 0; i <= 100; i++) {
            	System.out.println("I am " + name + " : and I'm at " + i);
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
	
}
