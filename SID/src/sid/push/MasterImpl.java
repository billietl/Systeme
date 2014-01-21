package sid.push;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sid.Master;
import sid.Worker;
import sid.api.AggregationResults;
import sid.api.Result;
import sid.api.SetOfTasks;
import sid.api.Task;

public class MasterImpl extends UnicastRemoteObject implements Master {

	private static final long serialVersionUID = 1L;
	private List<Worker> workers;
	private AggregationResults aggResults;
	private SetOfTasks set;
	private int lastTask, nbReponse;

	protected MasterImpl() throws RemoteException {
		super();
		this.workers = new ArrayList<Worker>();
	}

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		this.set = s;
		this.lastTask = this.nbReponse = 0;
		for (Worker r : workers) {
			Collection<Task> taskSet = this.getTasks(lastTask, lastTask + getChunkSize());
			r.gatherTasks(taskSet, this.aggResults);
			lastTask += getChunkSize();
		}
		while (this.nbReponse != this.workers.size()) {
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

	private int getChunkSize() {
		return this.set.getSize() / this.workers.size();
	}

	private Collection<Task> getTasks(int i, int j) {
		Collection<Task> taskSet = new ArrayList<Task>();
		for (; i < j; i++) {
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
