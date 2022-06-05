package classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMeetingLog implements IRead {
	private String text;

	@Override
	public String Load(String fileName) {
		FileReader reader = null;
		try {
			reader = new FileReader("./asset/" + fileName + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int ch;
		try {
			while ((ch = reader.read()) != -1) {
				text += (char) ch;
				System.out.print((char) ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}

}
