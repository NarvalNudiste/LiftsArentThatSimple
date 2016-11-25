package narval;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
	public static void main(String[] arg) throws IOException
	{
		SmartElevator se = new SmartElevator();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String buffer = new String();
		greetings();
		while (true)
		{
			printMenu();
			buffer = in.readLine();
			int choice = Integer.parseInt(buffer);
			switch(choice)
			{
			case 0:
				System.out.println("Enter a name : ");
				String name = in.readLine();
				System.out.println("Enter his/her floor position");
				int startingPos = Integer.parseInt(in.readLine());
				System.out.println("Enter his/her target floor");
				int targetFloor = Integer.parseInt(in.readLine());
				se.callElevator(new Client(startingPos, targetFloor, name));
				break;
			case 1:
				se.process();
				break;
			case 2:
				se.fullReport();
				break;
			case 3:
				while (!se.isDoneE() || !se.isDoneW())
				{
					se.process();
				}
				break;
			}
		}
	}
	
	public static void printMenu(){
		System.out.println("*********************");
		System.out.println("0. Enter a new client");
		System.out.println("1. Process events once");
		System.out.println("2. Show a report of the situation");
		System.out.println("3. Run simulation until everyone is home");
	}
	public static void greetings(){
	System.out.println("Hai, this is awesome lift simulator 1.0");
	System.out.println("");
	System.out.println("this awesome elevator might be bugged a bit, but is there any which doesn't ?");
	System.out.println("Supports multiple users at the same floor, and secretly plays crappy mobiles games when asleep");
	System.out.println("Commands : ");
	System.out.println("");
	}
}
