package sid;

public interface AggregationResults {
	
	public abstract void add(Result r);
	public abstract Result getFinalResult();
	
}
