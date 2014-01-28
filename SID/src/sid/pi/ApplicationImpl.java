package sid.pi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
	    System.out.println("Calcul approximatif de la valeur de pi: "+r.getVal()/4);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
