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
	 * 쿼리를 데이터베이스로 전송하는 인터페이스
	 * SQL문을 미리 컴파일해서 재사용하므로 Statement 인터페이스보다 빠르다
	 * 실행하려는 SQL문에 ?를 넣을 수 있다. */
	private PreparedStatement pstmt; 
	private Connection conn;
	
	public List<MemberVO> listMembers(){ // listMembers 메소드 반환형 MemberVO의 ArrayList
		List<MemberVO> list = new ArrayList<MemberVO>(); // MemberVO 객체 생성
		
		try {
			connDB();
			String query = "select *from t_member"; // t_member 테이블 조회
			System.out.println("prepareStatement: " + query);
			
			pstmt = conn.prepareStatement(query); // 쿼리를 오라클에 전송
			ResultSet rs = pstmt.executeQuery(); // 결과를 ResultSet에 담음
			
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO(); // 컬럼값을 다시 VO 객체에 담는다. VO 객체 생성
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
			System.out.println("Oracle 드라이버 로딩 성공");
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("Connection 생성 성공"); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
}
