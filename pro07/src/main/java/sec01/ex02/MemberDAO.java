package sec01.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private static final String id = "hr";
	private static final String pwd = "hr1234";
	
	/* PreparedStatement
	 * ������ �����ͺ��̽��� �����ϴ� �������̽�
	 * SQL���� �̸� �������ؼ� �����ϹǷ� Statement �������̽����� ������
	 * �����Ϸ��� SQL���� ?�� ���� �� �ִ�. */
	private PreparedStatement pstmt; 
	private Connection conn;
	
	public List<MemberVO> listMembers(){ // listMembers �޼ҵ� ��ȯ�� MemberVO�� ArrayList
		List<MemberVO> list = new ArrayList<MemberVO>(); // MemberVO ��ü ����
		
		try {
			connDB();
			String query = "select *from t_member"; // t_member ���̺� ��ȸ
			System.out.println("prepareStatement: " + query);
			
			pstmt = conn.prepareStatement(query); // ������ ����Ŭ�� ����
			ResultSet rs = pstmt.executeQuery(); // ����� ResultSet�� ����
			
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO(); // �÷����� �ٽ� VO ��ü�� ��´�. VO ��ü ����
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			conn.close();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	private void connDB() {
		try{Class.forName(driver);
			System.out.println("Oracle ����̹� �ε� ����");
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("Connection ���� ����"); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
}
