package sid.api;

import java.io.Serializable;

public interface AggregationResults extends Serializable {
	
	public abstract void add(Result r);
	public abstract Result getFinalResult();
	
}
