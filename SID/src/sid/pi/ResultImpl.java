package sid.pi;

import sid.api.Result;

public class ResultImpl implements Result {

	private static final long serialVersionUID = 1L;

	private double value;
	
	public ResultImpl(Double d){
		this.value = d;
	}
	
	public double getVal(){
		return this.value;
	}
}
