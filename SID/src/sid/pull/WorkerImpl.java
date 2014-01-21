package sid.pull;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashSet;

import sid.Master;
import sid.Worker;
import sid.api.AggregationResults;
import sid.api.Task;

public class WorkerImpl extends UnicastRemoteObject implements Worker, Runnable{

	private static final long serialVersionUID = 1L;
	private Master master;
	private Collection<Task> tasks;
	private AggregationResults agg;

	public WorkerImpl(Master m) throws RemoteException {
		super();
		this.tasks = new HashSet<Task>();
		this.agg = null;
		this.master = m;
		m.inscription(this);
	}

	@Override
	public void gatherTasks(Collection<Task> s, AggregationResults a)
			throws RemoteException {
		this.tasks.addAll(s);
		this.agg = a;
	}

	public void run() {
		while (true) {
			if (this.tasks.isEmpty()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			} else {
				for (Task t : this.tasks) {
					agg.add(t.execute());
				}
				try {
					this.master.gatherResult(this.agg.getFinalResult());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				this.tasks.clear();
			}
		}
	}

	public static void main(String args[]) {
		/**
		 * args[0] = adresse su serveur RMI
		 */
		WorkerImpl worker;
		try {
			Master m = ((Master) Naming.lookup("rmi://" + args[0] + "/master"));
			worker = new WorkerImpl(m);
			Naming.bind("rmi://" + args[0] + "/worker", worker);
			new Thread(worker).start();
			m.inscription(worker);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
