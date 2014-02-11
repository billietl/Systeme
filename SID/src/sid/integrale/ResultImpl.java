package sid.integrale;

import sid.api.Result;

public class ResultImpl implements Result {

	private static final long serialVersionUID = 1L;
	private double value;
	
	public ResultImpl(double val){
		this.value = val;
	}
	
	public double getVal(){
		return this.value;
	}

}
