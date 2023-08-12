package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet( urlPatterns = { "/getJSON", "/getHTML", "/getXML", "/getImage" })
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getRequestURI() : /myWeb/test.jsp → 폴더, 파일 얻어옴
		String uri = request.getRequestURI(); 
		String filename = "";
		String contentType = "";
		
		// uri의 끝이 getHTML이라면
		if (uri.endsWith("getHTML")) {
			filename = "C:/Temp/sample/jQuery.html"; // 파일이름은 html파일의 경로
			contentType = "text/html; charset=utf-8"; // html의 콘텐츠 타입과 인코딩 방식
		}else if (uri.endsWith("getXML")) {
			filename = "C:/Temp/sample/chungnam.xml";
			contentType = "application/xml; charset=utf-8";
		}else if (uri.endsWith("getJSON")) {
			filename = "C:/Temp/sample/book.json";
			contentType = "text/json; charset=utf-8";
		}else {
			filename = "C:/Temp/sample/image.jpg";
			contentType = "image/jpg";
		}
		
		// 파일네임을 파일클래스의 인스턴스로 만듦, 파일 타입이 되면 모든 문서를 읽을 수 있게됨
		File f = new File(filename);
		// 파일네임 인스턴스를 읽어오는 인풋스트림 (파일 입력 스트림, 바이트 단위)
		FileInputStream fis = new FileInputStream(f);
		// 응답할 문서 타입은 위에서 설정한걸로
		response.setContentType(contentType);
		
		// 이미지일때
		if(contentType.startsWith("image")) {
			// 이미지는 바이트 단위의 입출력 필요, 바이트 배열 생성
			byte[] content = new byte[ (int)f.length() ]; // f.lenth() : 파일의 크기만큼 읽어오기, 반환타입 long이라서 int로 강제 형변환
			
			// 이미지와 같은 바이너리 파일을 읽을 때는 FileInputStream으로 읽은 뒤
			// 브라우저에 출력할 때는 ServletOutputStream을 사용한다.
			ServletOutputStream sos = response.getOutputStream();
			// 이미지 읽기
			fis.read(content);
			// 이미지 출력
			sos.write(content);
			sos.close();
		}else {
			// FileInputStream 바이트 단위로 읽은 것을 -> InputStreamReader 한 문자씩 읽도록 
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			// -> BufferedReader 한 라인씩 읽도록 함
			BufferedReader br = new BufferedReader(isr);
			// 응답할 out 객체 만듦 : 사용자에게 출력
			PrintWriter out = response.getWriter();
			String line = null;
			
			// 읽을 게 없어질 때까지 읽음
			while((line = br.readLine()) != null ) {
				out.println(line);
			}
			// 다 읽었으면
			out.close();
			br.close();
			isr.close();
		}
		fis.close();
	}

}
