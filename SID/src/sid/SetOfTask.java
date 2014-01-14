package sid;

import java.io.Serializable;
import java.util.Iterator;

public abstract class SetOfTask implements Serializable {

	private static final long serialVersionUID = 1L;

	private int taille;
	
	public abstract Task getTask(int index);
	
	public abstract Iterator<Task> iterator();
	
}
