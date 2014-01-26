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
	private SetOfTasks set;
	private int lastTask, nbReponse;
	private static final int CHUNK_SIZE=100;

	protected MasterImpl() throws RemoteException {
		super();
		this.set = null;
		this.lastTask = this.nbReponse = 0;
	}

	@Override
	public Result doit(SetOfTasks s) throws RemoteException {
		this.set = s;
		this.lastTask = this.nbReponse = 0;
		while (this.nbReponse != this.set.getSize()/CHUNK_SIZE) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Result r = this.set.getAggregationResults().getFinalResult();
		this.set = null;
		return r;
	}

	@Override
	public synchronized void gatherResult(Result r) throws RemoteException {
		this.set.getAggregationResults().add(r);
		this.nbReponse++;
	}
	
	public synchronized Collection<Task> getTasks() throws RemoteException {
		Collection<Task> taskSet = new ArrayList<Task>();
		if(this.set == null){
			return taskSet;
		}
		for (int i=this.lastTask; i < this.lastTask+CHUNK_SIZE || i<this.set.getSize(); i++) {
			taskSet.add(this.set.getTask(i));
		}
		this.lastTask += CHUNK_SIZE;
		return taskSet; 
	}
	
	public AggregationResults getAggregationResult() throws RemoteException{
		return this.set.getAggregationResults();
	}
	
	public static void usage(){
		System.out.println("Usage : MasterImpl <adresse serveur rmi>");
		System.exit(1);
	}

	public static void main(String args[]) {
		/**
		 * args[0] = adresse su serveur RMI
		 */
		if(args.length < 1) usage();
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
