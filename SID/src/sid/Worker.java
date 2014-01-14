package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract class Worker implements Remote {

	public abstract Result compute(Task t) throws RemoteException;
	
}
