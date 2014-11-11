package queueProject;
import java.util.ArrayList;

public class Main {
	
	public static ArrayList <String> queueArray;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		queueArray = new ArrayList<String>(); 
		
		Thread t1 = new Thread(new AddToArrayThread());
		Thread t2 = new Thread(new GetFromArrayThread());
		
		t1.start();		
		Thread.sleep(1000);
		t2.start();
		
	}

}
