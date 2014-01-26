package sid.integrale;

import sid.api.AggregationResults;
import sid.api.Result;

public class AggregationResultImpl implements AggregationResults {

	private static final long serialVersionUID = 1L;
	private double acc;
	private int count;
	
	public AggregationResultImpl(){
		this.acc = 0;
		this.count = 0;
	}
	
	@Override
	public void add(Result r) {
		ResultImpl result = (ResultImpl) r;
		this.acc += result.getVal();
		this.count++;
	}

	@Override
	public Result getFinalResult() {
		return new ResultImpl((5.0*acc)/this.count);
	}

}
