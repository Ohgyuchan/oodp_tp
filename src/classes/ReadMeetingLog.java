package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMeetingLog implements IRead {
	private String text;

	@Override
	public String Load(String fileName) {
		if(fileName.equals("text11.txt")) {
			System.out.println("There is no log");
			return "";
		}

		try {
			String sen;
			BufferedReader fw = new BufferedReader(new FileReader(fileName));
			while((sen=fw.readLine())!=null) {
				text+=sen+"\n";
				System.out.println(sen);
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}
}
