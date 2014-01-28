package sid.pull;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import sid.Master;
import sid.Worker;
import sid.api.AggregationResults;
import sid.api.Task;
import sid.api.WorkUnit;

public class WorkerImpl extends UnicastRemoteObject implements Worker {

	private static final long serialVersionUID = 1L;
	private Master master;

	public WorkerImpl(Master m) throws RemoteException {
		super();
		this.master = m;
	}

	public void run() {
		WorkUnit col;
		AggregationResults agg;
		while (true) {
			try {
				col = this.master.getTasks();
				if (col == null) {
					Thread.sleep(100);
					continue;
				}
				agg = col.getWork().getAggregationResults();
				for (int i = col.getDebut(); i < col.getFin(); i++) {
					agg.add(col.getWork().getTask(i).execute());
				}
				this.master.gatherResult(agg.getFinalResult());
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void usage() {
		System.out.println("Usage : WorkerImpl <adresse serveur rmi>");
		System.exit(1);
	}

	public static void main(String args[]) {
		/**
		 * args[0] = adresse su serveur RMI
		 */
		if (args.length < 1)
			usage();
		WorkerImpl worker = null;
		Master m = null;
		try {
			Registry registry = LocateRegistry.getRegistry(args[0], 1100);
			m = ((Master) registry.lookup("master"));
			worker = new WorkerImpl(m);
			worker.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void gatherTasks(Collection<Task> s, AggregationResults a)
			throws RemoteException {
	}

}
