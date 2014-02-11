package sid.pull;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import sid.Master;
import sid.Worker;
import sid.api.AggregationResults;
import sid.api.Result;
import sid.api.SetOfTasks;
import sid.api.WorkUnit;

public class MasterImpl extends UnicastRemoteObject implements Master {

	private static final long serialVersionUID = 1L;
	private SetOfTasks set;
	private AggregationResults agg;
	private int lastTask, nbReponse;
	private static int CHUNCK_SIZE;

	protected MasterImpl() throws RemoteException {
		super();
		this.set = null;
		this.agg = null;
		this.lastTask = this.nbReponse = 0;
	}

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		this.set = s;
		this.agg = s.getAggregationResults();
		this.lastTask = 0;
		this.nbReponse = 0;
		while (this.nbReponse < this.set.getSize()/CHUNCK_SIZE) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Result r = this.agg.getFinalResult();
		this.set = null;
		return r;
	}

	@Override
	public synchronized void gatherResult(Result r) throws RemoteException {
		this.agg.add(r);
		this.nbReponse++;
	}

	public synchronized WorkUnit getTasks() throws RemoteException {
		if (this.set == null) {
			return null;
		}
		if (this.set.getSize() == this.lastTask) {
			return null;
		}
		int dernier = this.lastTask + CHUNCK_SIZE >= this.set.getSize() ? this.set
				.getSize() : this.lastTask + CHUNCK_SIZE;
		WorkUnit taskSet = new WorkUnit(this.set, this.lastTask, dernier);
		this.lastTask = dernier;
		return taskSet;
	}

	public static void usage() {
		System.out.println("Usage : MasterImpl <taille des taches>");
		System.exit(1);
	}

	public static void main(String args[]) {
		if (args.length < 1)
			usage();
		CHUNCK_SIZE = Integer.parseInt(args[0]);
		try {
			Registry registry = LocateRegistry.createRegistry(1100);
			MasterImpl master = new MasterImpl();
			registry.rebind("master", master);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inscription(Worker r) throws RemoteException {
	}

}
