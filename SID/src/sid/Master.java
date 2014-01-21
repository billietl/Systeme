package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sid.api.Result;
import sid.api.SetOfTasks;

public interface Master extends Remote {

	public abstract Result doit(SetOfTasks s) throws RemoteException;
	public abstract void gatherResult(Result r) throws RemoteException;
	public abstract void inscription(Worker r) throws RemoteException;
	
}
