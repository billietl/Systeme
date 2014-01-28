package sid.integrale;

import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import sid.Master;
import sid.api.Application;

public class ApplicationImpl implements Application {

    public static void main(String args[]){
	/**
	 * args[0] = adresse su serveur RMI
	 */
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
