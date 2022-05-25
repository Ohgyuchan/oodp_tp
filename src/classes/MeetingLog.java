package classes;
import java.io.File;
import java.io.FileWriter;

public class MeetingLog {
	private String txt = "내용이 없는데요?" ;
	private String fileName = "C:\\test11.txt" ;
		
	public void WriteMeetingLog(String text, String fileName) {
		try{	
			if(fileName != null) {//파일 이름 입력
				this.setFileName("C:\\"+fileName+".txt");
			}
			
			if(text !=null) {//내용 입력
				this.txt = text;
			}
			
			File file = new File(fileName);
			
			FileWriter fw = new FileWriter(file, true);
			
			fw.write(txt);
			fw.flush();

			fw.close(); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
