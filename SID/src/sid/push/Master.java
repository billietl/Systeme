package sid.push;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import sid.api.Result;
import sid.api.SetOfTasks;
import sid.api.Task;

public interface Master extends Remote {

	public abstract Result doit(SetOfTasks s) throws RemoteException;
	public abstract Collection<Task> gatherResult(Result r) throws RemoteException;
	public abstract void inscription(Worker r) throws RemoteException;
	
}
