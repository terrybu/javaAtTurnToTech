package Queue;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList <String> queueArray = new ArrayList<String>(); 
		
		queueArray.add("hi");
		queueArray.add("second");
		queueArray.add("baby");
		
		String last = queueArray.get(queueArray.size()-1);
		System.out.println(last);
		
		//I need Thread 1 to insert a String to the array every 5 seconds
		
				
		
	}

}
