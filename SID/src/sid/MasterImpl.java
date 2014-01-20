package sid;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MasterImpl extends UnicastRemoteObject implements Master {

	private ArrayList<Worker> workers;
	private AggregationResults aggResults;
	
	protected MasterImpl() throws RemoteException {
		super();
		this.workers = new ArrayList<Worker>();

	}

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		int subSize = s.getSize()/workers.size();
		for(Worker r : workers) {
			r.gatherTasks(s, a);
		}
		return null;
	}

	@Override
	public void gatherResult(Result r) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void inscription(Worker r) throws RemoteException {
		this.workers.add(r);
		
	}

}
