package sid.api;

import java.io.Serializable;

public interface SetOfTasks extends Serializable {
	
	public abstract Task getTask(int index);
	public abstract int getSize();
	public abstract AggregationResults getAggregationResults();
	
}
