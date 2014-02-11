package sid.integrale;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sid.Master;
import sid.api.Application;

public class ApplicationImpl implements Application {

	public static void usage() {
		System.out.println("Usage : ApplicationImpl <adresse registre RMI> <taille du travail>");
		System.exit(1);
	}

    public static void main(String args[]){
	/**
	 * args[0] = adresse su serveur RMI
	 */
    	if (args.length < 2)
			usage();
		SetOfTasksImpl.setWorkSize(Integer.parseInt(args[1]));
	try {
	    Registry registry = LocateRegistry.getRegistry(args[0],1100);
	    Master m = ((Master) registry.lookup("master"));
	    ResultImpl r = (ResultImpl) m.doit(new SetOfTasksImpl());
	    System.out.println("Calcul d'integrale de f(x,y)=cos(y*y) sur l'interval [0,5]: "+r.getVal());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
