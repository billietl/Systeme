package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import sid.api.AggregationResults;
import sid.api.Task;

public interface Worker extends Remote {

	public abstract void gatherTasks(Collection<Task> s, AggregationResults a) throws RemoteException;
}
