package narval.trash;
import java.util.LinkedList;

import narval.Client;
import narval.EState;

public class Elevator {
	/*------------------------------------------------------------------*\
	|*							Private Attributes						*|
	\*------------------------------------------------------------------*/
	public int elevatorActualFloor;
	private int elevatorTargetFloor;
	private EState state;
	private LinkedList<Client> peopleInElevator;
	private LinkedList<Client> peopleWaiting;
	private Client currentClient;
	/*------------------------------------------------------------------*\
	|*							Public Attributes						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
		
	/*------------------------------------------------------------------*\
	|*							Ctors									*|
	\*------------------------------------------------------------------*/
	public Elevator(){
		peopleInElevator = new LinkedList<Client>();
		peopleWaiting = new LinkedList<Client>();
		state = EState.idle;
		elevatorActualFloor = 0;
		elevatorTargetFloor = 0;
	}
	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/
	private void report()	//pasur
	{
		System.out.println("---------------------");
		System.out.println("Currently at floor #" + elevatorActualFloor);
		System.out.println("---------------------");
	}
	private void kickOutCurrentClientAndGetNext()
	{
		System.out.println(currentClient.getName() + " has reached his most desired floor and is walking away. Bye, " + currentClient.getName());
		if (peopleInElevator.remove(currentClient))
		{
			//System.out.println(currentClient.getName() + " has been kicked from the elevator. yeah, like that bitc*");
			currentClient = null;
		}
		else
		{
			System.out.println("err");
		}
		
		if (peopleWaiting.isEmpty())	//erreur ici
		{
			System.out.println("Out of work, back to pokemon go");
			state = EState.idle;
		}
		else
		{
			currentClient = peopleWaiting.getFirst();	
			System.out.println("New Client : " + currentClient.getName());
		}
	}
	private void goWhereYouAreMeantToGo(boolean insult){
		if (elevatorTargetFloor > elevatorActualFloor)
		{
			state = EState.goingUp;
			elevatorActualFloor++;
			System.out.println("the mighty elevator is going up");
			report();
		}
		else if (elevatorTargetFloor < elevatorActualFloor)
		{
			state = EState.goingDown;
			elevatorActualFloor--;
			System.out.println("The holy lift is going down");
			report();
		}
		else
		{
			if (insult == true)
			{
				System.out.println(currentClient.getName() + " has been kicked out this almighty place (elevators are sensible creatures you shit! cun*)");
				peopleInElevator.remove(currentClient);
				currentClient = null;

			}
		}
		
	}

	private void isIdle()
	{
		for (Client c : peopleWaiting)
		{
			System.out.println("waiting : " + c.getName());
		}
		if (!peopleWaiting.isEmpty())
		{
			if (peopleWaiting.peek().getActualFloor() == elevatorActualFloor)	//si la personne est au même étage on la prends
			{	
				peopleInElevator.add(peopleWaiting.remove());
				System.out.println("People waiting removed");
				currentClient = peopleInElevator.peek();
				elevatorTargetFloor = currentClient.getTargetFloor();
				System.out.println(currentClient.getName() + " entered the mighty lift (wants to go to floor #" + currentClient.getTargetFloor() + ")");
			}
			else	//sinon on va la chercher
			{
				currentClient = peopleWaiting.peek();
				elevatorTargetFloor = currentClient.getActualFloor();
			}
			
			goWhereYouAreMeantToGo(true);
		}
		else
		{
			System.out.println("nobody's waiting, the holy elevator shall keep playing pokemon go");
		}
	}

	private void isntIdle(){
		if (peopleWaiting.isEmpty())
		{
				if (currentClient.getTargetFloor() == elevatorActualFloor)	
				{
					kickOutCurrentClientAndGetNext();
				}
				else
				{
					goWhereYouAreMeantToGo(false);		
				}			
		}
		else
		{
			elevatorTargetFloor = currentClient.getTargetFloor();
			goWhereYouAreMeantToGo(false);
		}
	}
	/*------------------------------------------------------------------*\
	|*							Public Methods							*|
	\*------------------------------------------------------------------*/	
	public void fullReport()
	{
		System.out.println();
		System.out.println("***** report *****");
		int i = 0;
		int j = 0;
		if (currentClient != null)
			{
			System.out.println("current client : "+ currentClient.getName() + ", wants to go to floor #" + currentClient.getTargetFloor());
			}
		else
		{
			System.out.println("current client : null");
		}
		System.out.println("People in elevator : ");
		for (Client c : peopleInElevator)
			System.out.println(i++ + " - "+ c.getName() + ", wants to go to floor #" + c.getTargetFloor());
		System.out.println("People waiting : ");
		for (Client c : peopleWaiting)
			System.out.println(j++ + " - "+ c.getName());
		if (state != EState.idle)
		System.out.println("Elevator currently at floor #" + elevatorActualFloor + ", going for " + elevatorTargetFloor);
		else
		{
		System.out.println("Elevator currently at floor #" + elevatorActualFloor);	
		}
		System.out.println("state : " + state);
		System.out.println("*****************");
		System.out.println();
	}
	
	public void process()
	{
		if (state == EState.idle)
		{
			isIdle();
		}
		else
		{
			isntIdle();
		}
	}

	
	public boolean callElevator(Client _c){
	if (peopleWaiting.add(_c))
	{
		System.out.println(_c.getName() + " has awaken the mighty lift"); 
		return true;
	}
	else
		return false;
}
	public void letGo(Client _c){
		System.out.println(_c.getName() + " is at his desired floor, leaving. Bye, " + _c.getName());
	}
	public void showClients()
	{
		int i = 0;
		for (Client c : peopleInElevator)
		{
			c.Show(i++);
		}
	}
	public boolean isEmpty(){
		return peopleInElevator.isEmpty();
	}
	public LinkedList<Client> getPeopleWaiting() {
		return peopleWaiting;
	}
}
