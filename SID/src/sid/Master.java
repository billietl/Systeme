package sid;

import java.rmi.Remote;

public interface Master extends Remote {

	public Result Do(Work w);
	
}
