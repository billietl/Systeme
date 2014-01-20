package sid;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MasterImpl extends UnicastRemoteObject implements Master {

	private List<Worker> workers;
	private AggregationResults aggResults;
	private int lastTask;
	public static final int TASK_SIZE_INDICE=2;
	
	protected MasterImpl() throws RemoteException {
		super();
		this.workers = new ArrayList<Worker>();
	}

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		int subSize = s.getSize()/(workers.size()*TASK_SIZE_INDICE);
		for(Worker r : workers) {
			Collection<Task> taskSet = new ArrayList<Task>();
			for(int i=lastTask; i<lastTask; i++){
				taskSet.add(s.getTask(i));
			}
			r.gatherTasks(taskSet, this.aggResults);
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
