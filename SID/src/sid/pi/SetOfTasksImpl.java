package sid.pi;

import sid.api.AggregationResults;
import sid.api.SetOfTasks;
import sid.api.Task;

public class SetOfTasksImpl implements SetOfTasks {

	private static final long serialVersionUID = 1L;

	@Override
	public Task getTask(int index) {
		return new TaskImpl(index);
	}

	@Override
	public int getSize() {
		return 100000;
	}

	@Override
	public AggregationResults getAggregationResults() {
		return new AggregationResultsImpl();
	}

}
