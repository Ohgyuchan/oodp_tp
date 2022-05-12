// package classes.auth;

// import java.io.*;
// import java.util.*;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;

// import classes.Member;
// import classes.User;

// public class Login {
	
// 	boolean login() {
// 		Object ob = new JSONParser().parse(new FileReader("../../asset/data/users_data.json"));

//         JSONObject js = (JSONObject) ob;
// 		ArrayList<Member> account = new ArrayList<Member>();
// 		Scanner sc = new Scanner(System.in);
		
// 		String uid;
// 		String upw;

// 		System.out.print("Input your ID: ");
// 		uid=sc.next();
// 		System.out.print("Input your Password: ");		
// 		upw=sc.next();
		
// 		try {
// 			BufferedReader br = new BufferedReader(new FileReader(filename));
			
// 			String sen;
// 			String id, pw, name;
			
// 			while((sen=br.readLine())!=null) {
// 				User inM = new User();
// 				String[] temp = sen.split("/");
// 				id =temp[0];
// 				pw =temp[1];
// 				name=temp[2];
// 				inM.setId(id);
// 				inM.setPassword(pw);
// 				inM.setName(name);
// 				account.add(inM);
// 			}
// 				br.close();
			
// 			for(Member mem : account ) {
// 				if(mem.getId().equals(uid)) {
// 					if(mem.getPw().equals(upw)) {
// 						System.out.println("�α��μ���");
// 						System.out.println(mem.getName()+"�� �α����� ȯ���մϴ�");
// 						return true;
// 					}
// 				}
// 			}
// 		} catch (FileNotFoundException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		} catch (IOException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}
// 		return false;
// 	}
// }
