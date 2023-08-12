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
	
	private Statement stmt; // 쿼리를 데이터베이스로 전송
	private Connection con;
	
	public List<MemberVO> listMembers(){
		// 컬렉션 프레임워크 ArrayList<> : MemberVO를 ArrayList 객체로 생성, 배열
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			connDB(); // 데이터베이스 연결
			String query = "select *from t_member"; // 전송할 쿼리문
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query); // SQL으로 회원정보 조회, 쿼리를 전송하고 결과를 ResultSet으로 받음
			while(rs.next());{
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO(); // 컬럼값을 다시 MemberVO에 담는다. MemberVO 객체 생성
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo); // MemberVO 객체를 ArrayList에 저장
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
			Class.forName(driver); // 드라이버 매니저 실행
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd); // 오라클에 연결
			System.out.println("Connection 생성 성공");
			stmt = con.createStatement(); // 쿼리문을 전송할 때 사용하는 인터페이스
			System.out.println("Statement 생성 성공");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
