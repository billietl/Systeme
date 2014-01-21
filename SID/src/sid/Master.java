package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface Master extends Remote {

	public abstract Result doit(SetOfTasks s) throws RemoteException;
	public abstract Collection<Task> gatherResult(Result r) throws RemoteException;
	public abstract void inscription(Worker r) throws RemoteException;
	
}
