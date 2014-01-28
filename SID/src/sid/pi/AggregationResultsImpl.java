package sid.pi;

import sid.api.AggregationResults;
import sid.api.Result;

public class AggregationResultsImpl implements AggregationResults {

	private static final long serialVersionUID = 1L;
	private double agg;

	public AggregationResultsImpl(){
		this.agg = 0;
	}
	
	@Override
	public void add(Result r) {
		this.agg += ((ResultImpl)r).getVal();
	}

	@Override
	public Result getFinalResult() {
		return new ResultImpl(this.agg*4);
	}

}
