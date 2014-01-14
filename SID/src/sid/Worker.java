package sid;

import java.rmi.Remote;

public interface Worker extends Remote {

	public Result compute(Task t);
	
}
