package sid.api;

import java.io.Serializable;

public class WorkUnit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private SetOfTasks set;
	private int debut, fin;
	
	public SetOfTasks getWork() {
		return set;
	}

	public int getDebut() {
		return debut;
	}

	public int getFin() {
		return fin;
	}

	public WorkUnit(SetOfTasks set, int debut, int fin){
		this.set = set;
		this.debut = debut;
		this.fin = fin;
	}

}
