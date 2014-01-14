package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Master extends Remote {

	public abstract Result doit(SetOfTasks s) throws RemoteException;
	public abstract void delegate();
	public abstract void gatherResult(Result r) throws RemoteException;
	
}
