package classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMeetingLog implements IRead {
	private String text;
	
	@Override
	public String Load(String fileName) {
		// TODO Auto-generated method stub
		 FileReader reader = null;
		try {
			reader = new FileReader("C:\\"+fileName+".txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
		 
		int ch;        
		try {
			while ((ch = reader.read()) != -1) {
				text += (char)ch;
				System.out.print((char) ch);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text;
	}
	
}