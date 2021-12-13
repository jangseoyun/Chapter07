package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		// 채팅 접속자 (클라이언트 코드구현은 실행해야하는 코드 전부 작성)
		// 3. socket 생성,정의,닫기
		Socket socket = new Socket();
		System.out.println("클라이언트 시작");

		// 4. 서버 IP , 프로그램에서 세팅한 Port 10001
		socket.connect(new InetSocketAddress("192.168.219.100", 10001));
		System.out.println("클라이언트 연결요청");
		
		//메세지 보낼 준비 (빨대 꼽기)
		OutputStream os = socket.getOutputStream(); //주스트림
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");//보조스트림
		BufferedWriter bw = new BufferedWriter(osw);
		
		//10. 메세지 받을 준비 (순서 상관없음 위에 써도됨)
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//12. Scanner 키보드 입력용
		Scanner sc = new Scanner(System.in);
		
		//-------반복구간-----------
		while(true) {
			String str = sc.nextLine();
			if("/q".equals(str)) { //nullpoint 에러 방지를 위한 코드
				System.out.println("종료");
				break;
			}
			
			//5. 메시지 받기
			bw.write(str);
			bw.newLine();
			bw.flush(); //-> buffered 쟁반이 꽉 차야 움직이는 것인데 보내는 데이터가 적으면 null 나옴 
			//가득 채운것으로 치겠다
			
			//11. 메시지 받기
			String reMsg = br.readLine(); //한줄씩 받기 
			System.out.println("server:"+reMsg);
			
		}

		System.out.println("==========================");
		System.out.println("      >클라이언트 종료<       ");
		System.out.println("==========================");
		// 3. 닫기
		sc.close();
		bw.close();
		socket.close();
		
	}

}
