package sid.pull;

import java.rmi.RemoteException;

import sid.Master;
import sid.Worker;
import sid.api.Result;
import sid.api.SetOfTasks;

public class MasterImpl implements Master{

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void gatherResult(Result r) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inscription(Worker r) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
