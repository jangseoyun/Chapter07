package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	// 출장 나가서 할 일 (쓰레드를 받아서 run할 구현코드 작성)
	// 필드
	private Socket socket; // 값을 담는 곳 (Socket -> 자료형)

	// 생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 메소드 g,s

	// 메소드 일반

	@Override
	public void run() {
		System.out.println("클라이언트가 연결되었습니다");

		try {
			// 6. 메세지 '받을' 준비 (빨대 꼽기)
			InputStream is = socket.getInputStream();// socket으로 연결해서 소통하는 것 생각
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// 8. 메세지 '보낼' 준비 (순서상관없음 위에 써도됨)
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			while (true) {
				// 7. 메세지 받기
				String msg = br.readLine(); // 한줄 받아오기
				System.out.println("받은 메세지:" + msg);

				if (msg == null) {
					System.out.println("클라이언트 종료키 입력");// null이 오는 경우 클라이언트에서 /q 입력
					break;
				}

				// 9. 메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}

			br.close();
			bw.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
