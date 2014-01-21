package sid.integrale;

import java.rmi.Naming;

import sid.api.Application;
import sid.pull.MasterImpl;

public class ApplicationImpl implements Application {

	public static void main(String args[]){
		/**
		 * args[0] = adresse su serveur RMI
		 */
		try {
			MasterImpl m = ((MasterImpl) Naming.lookup("rmi://" + args[0] + "/master"));
			ResultImpl r = (ResultImpl) m.doit(new SetOfTasksImpl());
			System.out.println("Calcul d'integrale de f(x,y)=cos(y*y) sur l'interval [0,5]: "+r.getVal());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
