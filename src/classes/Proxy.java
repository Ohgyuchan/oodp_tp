package classes;

public class Proxy implements IRead {
	IRead reader;

	@Override
	public String Load(String fileName) {
		// TODO Auto-generated method stub
		reader = new ReadMeetingLog();
		return reader.Load(fileName);
	}
	
}
