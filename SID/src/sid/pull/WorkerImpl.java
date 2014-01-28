package sid.pull;

import java.net.InetAddress;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
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
		if (col.isEmpty()) {
		    Thread.sleep(100);
		    continue;
		}
		agg = this.master.getAggregationResult();
		for (Task t : col) {
		    agg.add(t.execute());
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

    public static int findLastId(int id, Registry r) {
	try {
	    r.lookup("worker" + id + "@" + InetAddress.getLocalHost().getHostName());
	} catch (NotBoundException e) {
	    return id;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return findLastId(id++, r);
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
	    int id = findLastId(0, registry);
	    id++;
	    worker = new WorkerImpl(m);
	    registry.rebind("worker" + id + "@" + InetAddress.getLocalHost().getHostName(), worker);
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
