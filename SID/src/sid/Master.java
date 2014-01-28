package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import sid.api.AggregationResults;
import sid.api.Result;
import sid.api.SetOfTasks;
import sid.api.Task;

public interface Master extends Remote {

	public Result doit(SetOfTasks s) throws RemoteException;
	public void gatherResult(Result r) throws RemoteException;
	public void inscription(Worker r) throws RemoteException;
	public Collection<Task> getTasks() throws RemoteException;
	public AggregationResults getAggregationResult() throws RemoteException;
}
