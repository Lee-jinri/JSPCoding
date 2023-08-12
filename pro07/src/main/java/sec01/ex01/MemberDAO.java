package sec01.ex01;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private static final String user = "hr";
	private static final String pwd = "hr1234";
	
	private Statement stmt; // ������ �����ͺ��̽��� ����
	private Connection con;
	
	public List<MemberVO> listMembers(){
		// �÷��� �����ӿ�ũ ArrayList<> : MemberVO�� ArrayList ��ü�� ����, �迭
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			connDB(); // �����ͺ��̽� ����
			String query = "select *from t_member"; // ������ ������
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query); // SQL���� ȸ������ ��ȸ, ������ �����ϰ� ����� ResultSet���� ����
			while(rs.next());{
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO(); // �÷����� �ٽ� MemberVO�� ��´�. MemberVO ��ü ����
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo); // MemberVO ��ü�� ArrayList�� ����
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void connDB() {
		try {
			Class.forName(driver); // ����̹� �Ŵ��� ����
			System.out.println("Oracle ����̹� �ε� ����");
			con = DriverManager.getConnection(url, user, pwd); // ����Ŭ�� ����
			System.out.println("Connection ���� ����");
			stmt = con.createStatement(); // �������� ������ �� ����ϴ� �������̽�
			System.out.println("Statement ���� ����");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
