package sid.pi;

import sid.api.Result;
import sid.api.Task;

public class TaskImpl implements Task {

	private static final long serialVersionUID = 1L;
	private int indice;

	protected TaskImpl(int i){
		this.indice = i;
	}
	
	@Override
	public Result execute() {
		return new ResultImpl(Math.pow(-1, this.indice) / ((2*this.indice)+1));
	}

}
