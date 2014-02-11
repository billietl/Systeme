package sid.integrale;

import sid.api.AggregationResults;
import sid.api.SetOfTasks;
import sid.api.Task;

public class SetOfTasksImpl implements SetOfTasks {

	private static final long serialVersionUID = 1L;
	protected static int NB_TIRAGES=-1;

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
	
	public static void setWorkSize(int size){
		if(NB_TIRAGES == -1)
			NB_TIRAGES = size;
	}

}
