package sid.integrale;

import sid.api.AggregationResults;
import sid.api.SetOfTasks;
import sid.api.Task;

public class SetOfTasksImpl implements SetOfTasks {

	private static final long serialVersionUID = 1L;
	protected static final int NB_TIRAGES=10000;

	@Override
	public Task getTask(int index) {
		return new TaskImpl(index);
	}

	@Override
	public int getSize() {
		return NB_TIRAGES;
	}

	@Override
	public AggregationResults getAggregationResults() {
		return new AggregationResultsImpl();
	}

}
