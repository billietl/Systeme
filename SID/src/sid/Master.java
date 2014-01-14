package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract class Master implements Remote {

	public abstract Result doit(SetOfTask w) throws RemoteException;
	public abstract void delegate();
	
}
