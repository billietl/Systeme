package sid.integrale;

import sid.api.Result;
import sid.api.Task;

public class TaskImpl implements Task {

	private static final long serialVersionUID = 1L;

	public TaskImpl(int index){}
	
	@Override
	public Result execute() {
		double x, y, f;
		x = Math.random()*5;
		y = Math.random()*5;
		f = Math.cos(x*y);
		return new ResultImpl(f);
	}

}
