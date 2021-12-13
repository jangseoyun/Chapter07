package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// 서버 
		// 1. 예외처리 해줘야함 ! (통신이 안될 수 있는 상황)
		ServerSocket serverSocket = new ServerSocket();
		System.out.println("서버시작");
		
		// 2. IP 192.168.219.100 Port 10001
		serverSocket.bind(new InetSocketAddress("192.168.219.100", 10001));
		System.out.println("연결을 기다리고 있습니다");

		//5. 쓰레드 구현코드 [socket연결, 쓰레드 업캐스팅, start(출장 선언)]
		while (true) {//서버가 계속 켜져있는 상황

			//대기하고 있다가 누가 오면 실행(연결) / ★소켓 통신 
			Socket socket = serverSocket.accept();// socket이 누구랑 연결되어있는 번호라고 생각
			
			//★ Thread run 구현코드 클래스 생성(스레드 + 소켓통신)
			Thread thread = new ServerThread(socket); //생성자로 socket 연결 넣어주기
			thread.start(); // 출장나가고, run[선보강, 메세지 주고받기], 출장종료

			/*
			 * if() { break; }
			 */
		}

		/*
		System.out.println("==========================");
		System.out.println("         >서버종료<         ");
		System.out.println("==========================");
		// 1. 닫기
		socket.close();
		serverSocket.close();
		*/
		
	}
}
