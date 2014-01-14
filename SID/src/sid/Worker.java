package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Worker extends Remote {

	public abstract Result gatherTasks(Set<Task> s, AggregationResults a) throws RemoteException;
	
}
