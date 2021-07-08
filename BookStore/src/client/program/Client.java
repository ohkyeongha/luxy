package client.program;

import java.io.IOException;
import java.net.Socket;

import client.frame.LoginFrame;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost",7777);
			System.out.println("서버연결 완료");
			
			LoginFrame login = new LoginFrame();
			
			//서버와 통신하는 클라이언트 핸들러 쓰레드로 동작
			Thread clientHandler = new ClientHandler(socket, login);
			clientHandler.start();
			
		
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
