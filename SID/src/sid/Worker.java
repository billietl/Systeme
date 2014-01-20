package sid;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface Worker extends Remote {

	public abstract void gatherTasks(Collection<Task> s, AggregationResults a) throws RemoteException, TooMuchWorkException;
	
}
