package sid.pull;

import java.rmi.RemoteException;
import java.util.Collection;

import sid.Worker;
import sid.api.AggregationResults;
import sid.api.Task;

public class WorkerImpl implements Worker{

	@Override
	public void gatherTasks(Collection<Task> s, AggregationResults a)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
