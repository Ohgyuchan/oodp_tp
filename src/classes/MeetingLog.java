package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;

import classes.log.strategy.LeaderWrite;
import classes.log.strategy.MemberWrite;
import classes.log.strategy.Writing;
import classes.user.User;

public class MeetingLog {
	private String txt;
	private String fileName =  "text11.txt";

	public void WriteMeetingLog(String text, String fileName, User currentUser, Project currentProject) {

		try {
			fileName = fileName + ".txt";
			if (fileName != null) {// 파일 이름 입력
				this.fileName = fileName;
			}

			if (text != null && currentUser.getId().equals(currentProject.getLeaderId())) { // 내용 입력, [strategy패턴]
																							// leader이면
				Writing leader = new Writing(new LeaderWrite());
				String state = leader.stWrite();
				this.txt = state + text;
			} else if (text != null) { // strategy 패턴 멤버이라면
				Writing leader = new Writing(new MemberWrite());
				String state = leader.stWrite();
				this.txt = state + text;
			}

			BufferedWriter fw = new BufferedWriter(new FileWriter(fileName));

			fw.write(txt);
			fw.flush();

			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName + ".txt";
	}

	@Override
	public String toString() {
		return "MeetingLog [fileName=" + fileName + "]";
	}

}
