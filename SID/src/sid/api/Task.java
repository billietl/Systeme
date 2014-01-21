package sid.api;

import java.io.Serializable;

public interface Task extends Serializable {

	public abstract Result execute();
}
