package sid.pull;

import java.rmi.RemoteException;
import java.util.Collection;

import sid.Master;
import sid.Worker;
import sid.api.Result;
import sid.api.SetOfTasks;
import sid.api.Task;

public class MasterImpl implements Master{

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Task> gatherResult(Result r) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inscription(Worker r) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
