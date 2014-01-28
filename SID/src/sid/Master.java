package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sid.api.Result;
import sid.api.SetOfTasks;
import sid.api.WorkUnit;

public interface Master extends Remote {

	public Result doit(SetOfTasks s) throws RemoteException;
	public void gatherResult(Result r) throws RemoteException;
	public void inscription(Worker r) throws RemoteException;
	public WorkUnit getTasks() throws RemoteException;
}
