package sid;

public abstract class AggregationResults {
	
	public abstract void add(Result r);
	
	public abstract Result getFinalResult();
}
