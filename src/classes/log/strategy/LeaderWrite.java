package classes.log.strategy;

public class LeaderWrite implements WriteLog{

	@Override
	public String writing() {
		return "[Leader] ";
	}
}
