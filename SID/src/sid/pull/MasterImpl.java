package sid.pull;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

import sid.Master;
import sid.Worker;
import sid.api.AggregationResults;
import sid.api.Result;
import sid.api.SetOfTasks;
import sid.api.Task;

public class MasterImpl extends UnicastRemoteObject implements Master{

	private static final long serialVersionUID = 1L;
	private AggregationResults aggResults;
	private SetOfTasks set;
	private int lastTask, nbReponse;
	private static final int CHUNK_SIZE=5;

	protected MasterImpl() throws RemoteException {
		super();
	}

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		this.set = s;
		this.lastTask = this.nbReponse = 0;
		while (this.nbReponse != this.set.getSize()/this.getChunkSize()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.aggResults.getFinalResult();
	}

	@Override
	public synchronized void gatherResult(Result r) throws RemoteException {
		this.aggResults.add(r);
		this.nbReponse++;
	}
	
	public synchronized Collection<Task> getTasks() throws RemoteException{
		Collection<Task> col = this.getTasks(this.lastTask, this.lastTask+this.getChunkSize());
		this.lastTask += this.getChunkSize();
		return col; 
	}
	
	public AggregationResults getAggregationResult(){
		return this.aggResults;
	}

	private int getChunkSize() {
		return this.set.getSize() / (CHUNK_SIZE);
	}

	private Collection<Task> getTasks(int i, int j) {
		Collection<Task> taskSet = new ArrayList<Task>();
		for (; i < j; i++) {
			taskSet.add(this.set.getTask(i));
		}
		return taskSet;
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

	@Override
	public void inscription(Worker r) throws RemoteException {}

}
