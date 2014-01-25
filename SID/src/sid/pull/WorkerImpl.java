package sid.pull;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import sid.Master;
import sid.Worker;
import sid.api.AggregationResults;
import sid.api.Task;

public class WorkerImpl extends UnicastRemoteObject implements Worker {

	private static final long serialVersionUID = 1L;
	private Master master;

	public WorkerImpl(Master m) throws RemoteException {
		super();
		this.master = m;
	}

	public void run() {
		Collection<Task> col;
		AggregationResults agg;
		while (true) {
			try {
				col = this.master.getTasks();
				agg = this.master.getAggregationResult();
				if(col.isEmpty()){
					Thread.sleep(100);
					continue;
				}
				for(Task t : col){
					agg.add(t.execute());
				}
				this.master.gatherResult(agg.getFinalResult());
			}
			catch (RemoteException e) {e.printStackTrace();} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	public static void usage(){
		System.out.println("Usage : WorkerImpl <adresse serveur rmi>");
		System.exit(1);
	}

	public static void main(String args[]) {
		/**
		 * args[0] = adresse su serveur RMI
		 */
		if(args.length < 1) usage();
		try {
			MasterImpl m = ((MasterImpl) Naming.lookup("rmi://" + args[0] + "/master"));
			WorkerImpl worker = new WorkerImpl(m);
			Naming.bind("rmi://" + args[0] + "/worker@" + InetAddress.getLocalHost().getHostName(), worker);
			worker.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void gatherTasks(Collection<Task> s, AggregationResults a)
			throws RemoteException {}

}
