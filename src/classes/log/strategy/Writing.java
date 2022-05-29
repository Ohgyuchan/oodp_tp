package classes.log.strategy;

public class Writing {
	private WriteLog writeLog;
	
	public Writing(WriteLog writelog) {
		this.writeLog = writelog;	
				}
	
	public String stWrite() {
		return writeLog.writing();
	}
	
	public void setState(WriteLog writeLog) {
		this.writeLog=writeLog;
	}
	
}
