package narval;
import java.util.LinkedList;
import java.util.Random;

//import narval.Elevator.EState;

public class SmartElevator {
	/*------------------------------------------------------------------*\
	|*							Private Attributes						*|
	\*------------------------------------------------------------------*/
	private int elevatorActualFloor;
	private int elevatorTargetFloor;
	private EState state;
	private LinkedList<Client> peopleInElevator;
	private LinkedList<Client> peopleWaiting;
	private Random prng;
	private int goingUpSum = 0;
	private int goingDownSum = 0;
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
	public SmartElevator(){
		peopleInElevator = new LinkedList<Client>();
		peopleWaiting = new LinkedList<Client>();
		state = EState.idle;
		prng = new Random();
		elevatorActualFloor = 0;
		elevatorTargetFloor = 0;
	}
	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/
	private void letClientsGo()
	{
		for (Client p : peopleInElevator)
		{
			if (p.getTargetFloor() == elevatorActualFloor)
			{
				String n = p.getName();
				if (peopleInElevator.remove(p))
				{
					System.out.println("# " + n + " has reached his most desired floor and is walking away. Bye, " + n);					
				}
				else
				{
					System.out.println("error : cannot get this folk out");
				}
			}
		}
	}
	private void getClients()
	{	if (state == EState.idle)
		{
			calculateSum(peopleWaiting);
		}
		LinkedList<Client> toRemove = new LinkedList<Client>();
		for (Client p : peopleWaiting)
		{
			if (p.getActualFloor() == elevatorActualFloor)
			{
				if (state != EState.idle)
				{
					//if the lift is going down
					if ((state == EState.goingDown) && p.getDelta() < 0)
					{
						System.out.println("(the lift is going down, adding people who wants to go down. this case, this is "+p.getName());
						if (peopleInElevator.add(p))
							System.out.println("#" + p.getName() + " has entered the almighty lift");
						toRemove.add(p);
					}
					//if the lift is going up
					if ((state == EState.goingUp) && p.getDelta() > 0)
					{
						System.out.println("(the lift is going up, adding people who wants to go up. this case, this is "+p.getName());
						if (peopleInElevator.add(p))
							System.out.println("#" + p.getName() + " has entered the almighty lift");
						toRemove.add(p);
					}	
				}
				else
				{	//if the lift is idle
					if ((goingUpSum > goingDownSum) && p.getDelta() > 0)	// and if the majority wants to go up
					{
						System.out.println("(idle, majority is moving up) - "+p.getName()+" delta is "+p.getDelta());
						if (peopleInElevator.add(p))
							System.out.println("#" + p.getName() + " has entered the almighty lift");
						toRemove.add(p);
					}
					if ((goingDownSum > goingUpSum) && p.getDelta() < 0)	// and if the majority wants to go down
					{
						System.out.println("(idle, majority is moving down) - "+p.getName()+" delta is "+p.getDelta());
						if (peopleInElevator.add(p))
							System.out.println("#" + p.getName() + " has entered the almighty lift");
						toRemove.add(p);
					}
				}
			}
		}
		peopleWaiting.removeAll(toRemove);
	}
	
	private void calculateSum(LinkedList<Client> ll){
		goingUpSum = 0;
		goingDownSum = 0;
		for (Client p : ll)
		{
			if (p.getDelta() >= 0)
				goingUpSum += p.getDelta();
			else
				goingDownSum -= p.getDelta();		
		}
	}
	
	private void determinateNextTarget()
	{
		if (peopleInElevator.isEmpty() && peopleWaiting.isEmpty())
		{
			if (state != EState.idle)
			{
				System.out.println("*Out of work, back to pokemon go");
				state = EState.idle;
			}
			else
			{
				System.out.println(">nobody's waiting, the holy elevator shall keep playing pokemon go");
				sillyP();
				System.out.println("");
				state = EState.idle;
			}

		}
		else if (peopleInElevator.isEmpty() && !peopleWaiting.isEmpty())
		{
			System.out.println("*The mighty lift has gloriously decided to go grab good ol' " + peopleWaiting.peek().getName());
			elevatorTargetFloor = peopleWaiting.peek().getActualFloor();
		}
		else if (!peopleInElevator.isEmpty() && peopleWaiting.isEmpty())
		{
			elevatorTargetFloor = peopleInElevator.peek().getTargetFloor();
		}
		else
		{
			elevatorTargetFloor = peopleInElevator.peek().getTargetFloor();
		}
	}
	private void upDownYouDecide(){
		if (elevatorActualFloor < elevatorTargetFloor)
		{
			state = EState.goingUp;
			elevatorActualFloor++;
			System.out.println(">the mighty elevator is going up");
		}
		else if (elevatorActualFloor > elevatorTargetFloor)
		{
			state = EState.goingDown;
			elevatorActualFloor--;
			System.out.println(">The holy lift is going down");
		}
		else
		{
			System.out.println(">SuperLift decided it would be wiser if it'd stopped");
			state = EState.idle;
		}
	}
	/*------------------------------------------------------------------*\
	|*							Public Methods							*|
	\*------------------------------------------------------------------*/	
	public void fullReport()
	{
		System.out.println();
		System.out.println("******   PSA   ******");
		int i = 0;
		int j = 0;
		System.out.println("- Lift target floor: "+elevatorTargetFloor);

		if (!peopleInElevator.isEmpty())
		{
			System.out.println("- People in elevator : ");
			for (Client c : peopleInElevator)
				System.out.println(" --" + i++ + " - "+ c.getName() + ", wants to go to floor #" + c.getTargetFloor());	
		}
		else
		{
			System.out.println("- People in elevator : none");
		}
		if (!peopleWaiting.isEmpty())
		{
			System.out.println("- People waiting : ");
			for (Client c : peopleWaiting)
				System.out.println(" --" + j++ + " - "+ c.getName() + " at floor #" + c.getActualFloor());		
		}
		else
		{
			System.out.println("- People waiting : none");
		}
		if (state != EState.idle)
		System.out.println("- Elevator currently at floor #" + elevatorActualFloor + ", going for " + elevatorTargetFloor);
		else
		{
		System.out.println("- Elevator currently at floor #" + elevatorActualFloor);	
		}
		System.out.println("- State : " + state);
		System.out.println("********************");
		System.out.println();
	}
	
	public void process(){
		letClientsGo();
		getClients();
		determinateNextTarget();
		upDownYouDecide();
	}
	private void sillyP()
	{
		int r = Math.abs(prng.nextInt()%10);
		String sillyp = new String();
		switch(r){
		case 0:
			sillyp = "Our lift caught his 57th metapod";
			break;
		case 1:
			sillyp = "Look who's doing secrets microtransactions again";
			break;
		case 2:
			sillyp = "Aww, this pokestop was this close";
			break;
		case 3:
			sillyp = "He's concerned about his life";
			break;
		case 4:
			sillyp = "He's considering buying a dog";
			break;
		case 5:
			sillyp = "He thinks of pasta";
			break;
		case 6:
			sillyp = "His smartphone freezed for a bit";
			break;
		case 7:
			sillyp = "This damn app crashed again";
			break;
		case 8:
			sillyp = "His thumbs are beginning to hurt";
			break;
		case 9:
			sillyp = "Thinks the last consumer should consider a weight loss";
			break;
		default:
			sillyp = "All his base are belong to us";
		}
		System.out.println(">> " + sillyp);
	}
	public boolean callElevator(Client _c){
		if (peopleWaiting.add(_c))
		{
			System.out.println("#" + _c.getName() + " has awaken the mighty lift"); 
			return true;
		}
		else
			return false;
	}
	
	public boolean isDoneE(){
		return peopleInElevator.isEmpty();
	}
	public boolean isDoneW(){
		return peopleWaiting.isEmpty();
	}
	
}
