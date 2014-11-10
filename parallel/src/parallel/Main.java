package parallel;

import parallel.RunnableProcess;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread t1 = new Thread(new RunnableProcess("thread1"));
		Thread t2 = new Thread(new RunnableProcess("thread2"));

		t1.start();
		t2.start();
		
		
	}

}
