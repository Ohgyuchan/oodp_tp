package classes.log.strategy;

public class MemberWrite implements WriteLog{

	@Override
	public String writing() {
		return "[Member] ";
	}

}
