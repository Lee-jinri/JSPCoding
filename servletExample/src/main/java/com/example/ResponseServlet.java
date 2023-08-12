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
		// getRequestURI() : /myWeb/test.jsp �� ����, ���� ����
		String uri = request.getRequestURI(); 
		String filename = "";
		String contentType = "";
		
		// uri�� ���� getHTML�̶��
		if (uri.endsWith("getHTML")) {
			filename = "C:/Temp/sample/jQuery.html"; // �����̸��� html������ ���
			contentType = "text/html; charset=utf-8"; // html�� ������ Ÿ�԰� ���ڵ� ���
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
		
		// ���ϳ����� ����Ŭ������ �ν��Ͻ��� ����, ���� Ÿ���� �Ǹ� ��� ������ ���� �� �ְԵ�
		File f = new File(filename);
		// ���ϳ��� �ν��Ͻ��� �о���� ��ǲ��Ʈ�� (���� �Է� ��Ʈ��, ����Ʈ ����)
		FileInputStream fis = new FileInputStream(f);
		// ������ ���� Ÿ���� ������ �����Ѱɷ�
		response.setContentType(contentType);
		
		// �̹����϶�
		if(contentType.startsWith("image")) {
			// �̹����� ����Ʈ ������ ����� �ʿ�, ����Ʈ �迭 ����
			byte[] content = new byte[ (int)f.length() ]; // f.lenth() : ������ ũ�⸸ŭ �о����, ��ȯŸ�� long�̶� int�� ���� ����ȯ
			
			// �̹����� ���� ���̳ʸ� ������ ���� ���� FileInputStream���� ���� ��
			// �������� ����� ���� ServletOutputStream�� ����Ѵ�.
			ServletOutputStream sos = response.getOutputStream();
			// �̹��� �б�
			fis.read(content);
			// �̹��� ���
			sos.write(content);
			sos.close();
		}else {
			// FileInputStream ����Ʈ ������ ���� ���� -> InputStreamReader �� ���ھ� �е��� 
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			// -> BufferedReader �� ���ξ� �е��� ��
			BufferedReader br = new BufferedReader(isr);
			// ������ out ��ü ���� : ����ڿ��� ���
			PrintWriter out = response.getWriter();
			String line = null;
			
			// ���� �� ������ ������ ����
			while((line = br.readLine()) != null ) {
				out.println(line);
			}
			// �� �о�����
			out.close();
			br.close();
			isr.close();
		}
		fis.close();
	}

}
