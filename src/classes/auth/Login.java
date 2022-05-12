package classes.auth;

import java.io.*;
import java.util.*;

import classes.Member;
import classes.User;

public class Login {
	
	boolean login() {
		String filename ="accs/account.text";
		ArrayList<Member> account = new ArrayList<Member>();
		Scanner sc = new Scanner(System.in);
		
		String uid;
		String upw;

		System.out.println("id�� �Է��ϼ���:");
		uid=sc.next();
		System.out.println("pw�� �Է��ϼ���:");		
		upw=sc.next();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String sen;
			String id, pw, name;
			
			while((sen=br.readLine())!=null) {
				User inM = new User();
				String[] temp = sen.split("/");
				id =temp[0];
				pw =temp[1];
				name=temp[2];
				inM.setId(id);
				inM.setPassword(pw);
				inM.setName(name);
				account.add(inM);
			}
				br.close();
			
			for(Member mem : account ) {
				if(mem.getId().equals(uid)) {
					if(mem.getPw().equals(upw)) {
						System.out.println("�α��μ���");
						System.out.println(mem.getName()+"�� �α����� ȯ���մϴ�");
						return true;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
