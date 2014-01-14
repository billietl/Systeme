package sid;

import java.io.Serializable;

public abstract class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Result execute();
}
