package sid;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

public class WorkerImpl extends UnicastRemoteObject implements Worker, Runnable {

	private Master master;
	private Set<Task> tasks;
	private AggregationResults agg;

	public WorkerImpl() throws RemoteException {
		super();
		this.tasks = new HashSet<Task>();
		this.agg = null;
		this.master = null;
	}

	@Override
	public void gatherTasks(Set<Task> s, AggregationResults a)
			throws RemoteException, TooMuchWorkException {
		if (!this.tasks.isEmpty()) {
			throw new TooMuchWorkException();
		} else {
			this.tasks.addAll(s);
			this.agg = a;
		}
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
			}
		}
	}

	public static void main(String args[]) {
		/**
		 * args[0] = adresse su serveur RMI
		 * args[1] = nom du master
		 */
		WorkerImpl worker;
		try {
			worker = new WorkerImpl();
			Naming.bind("rmi://" + args[0] + "/worker", worker);
			new Thread(worker).start();
			((Master) Naming.lookup(args[1])).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
