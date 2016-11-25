package narval.trash;

public class TotallyAwesomePrompt {
	private Elevator elevator;
	public TotallyAwesomePrompt(Elevator _e)
	{
		elevator = _e;
	}
	/*public boolean parseNewClient()
	{
		System.out.println("Enter client name");
		String name = System.console().readLine();
		System.out.println("Enter client starting floor");
		int startingFloor = Integer.parseInt(System.console().readLine());
		System.out.println("Enter client target floor");
		int targetFloor = Integer.parseInt(System.console().readLine());
		if (elevator.addClient(new Client(startingFloor, targetFloor, name)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}*/
}
