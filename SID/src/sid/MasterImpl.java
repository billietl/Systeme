package sid;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MasterImpl extends UnicastRemoteObject implements Master {

	private static final long serialVersionUID = 1L;
	private List<Worker> workers;
	private AggregationResults aggResults;
	private SetOfTasks set;
	private int lastTask;
	public static final int CHUNK_SIZE_INDICE = 2;

	protected MasterImpl() throws RemoteException {
		super();
		this.workers = new ArrayList<Worker>();
	}

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		this.set = s;
		for (Worker r : workers) {
			Collection<Task> taskSet = this.getTasks(lastTask, lastTask+getChunkSize());
			try {
				r.gatherTasks(taskSet, this.aggResults);
				lastTask += getChunkSize();
			} catch (TooMuchWorkException e) {
			}
		}
		while(this.lastTask < this.set.getSize()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.aggResults.getFinalResult();
	}

	@Override
	public synchronized Collection<Task> gatherResult(Result r)	throws RemoteException {
		this.aggResults.add(r);
		if (this.lastTask < this.set.getSize()) {
			return null;
		} else {
			Collection<Task> taskSet = this.getTasks(lastTask, lastTask+getChunkSize());
			lastTask += getChunkSize();
			return taskSet;
		}
	}
	
	private int getChunkSize(){
		return this.set.getSize() / (workers.size() * CHUNK_SIZE_INDICE);
	}
	
	private Collection<Task> getTasks(int i, int j){
		Collection<Task> taskSet = new ArrayList<Task>();
		for(;i<j;i++){
			taskSet.add(this.set.getTask(i));
		}
		return taskSet;
	}

	@Override
	public void inscription(Worker r) throws RemoteException {
		this.workers.add(r);
	}
	
	public static void main(String args[]) {
		/**
		 * args[0] = adresse su serveur RMI
		 */
		try {
			MasterImpl master = new MasterImpl();
			Naming.bind("rmi://" + args[0] + "/master", master);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
