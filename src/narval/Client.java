package narval;

public class Client {
	/*------------------------------------------------------------------*\
	|*							Private Attributes						*|
	\*------------------------------------------------------------------*/
	private int delta;
	private String name;
	private int actualFloor;
	private int targetFloor;
	/*------------------------------------------------------------------*\
	|*							Public Attributes						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setActualFloor(int _actualFloor) {
		actualFloor = _actualFloor;
	}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public int getActualFloor() {
		return actualFloor;
	}
	
	public int getTargetFloor() {
		return targetFloor;
	}
	public String getName() {
		return name;
	}
	
	/*------------------------------------------------------------------*\
	|*							Ctors									*|
	\*------------------------------------------------------------------*/
	public Client(int _startingFloor, int _targetFloor, String _n){
		actualFloor = _startingFloor;
		targetFloor = _targetFloor;
		name = _n;
		delta = targetFloor - actualFloor;
	}
	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Public Methods							*|
	\*------------------------------------------------------------------*/	

	public void Show(int _order){
		System.out.println(_order + ": " + name + " is at floor #" + actualFloor + ", he wants to go to floor #" + targetFloor);
	}
	public int getDelta() {
		return delta;
	}


}
