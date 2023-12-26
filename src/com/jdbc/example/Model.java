package com.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Model {
	
	//select할 내용작성
	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String uid ="hr";
	private String upw ="1234";
	
	public Model() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	public void selectOne() {
		
		 
		
		String sql = "SELECT *FROM EMPLOYEES WHERE EMPLOYEE_ID >= ?";
		
		//모든 jdbc코드는 try~catch구문에서 작성이 들어가야 합니다. (throws를 던지고 있기 떄문)
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. JDBC드라이버 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. conn객체생성
			conn = DriverManager.getConnection(url,uid,upw);
			
			//3. conn으로부터 statment객체 생성 - sql상태를 지정하기 위한 객체
			pstmt = conn.prepareStatement(sql);
			
			// ? 의 개수에 마추어 값을 채운다
			// sql문의 ?에 대한 값을 채우기 setString(순서 문자열)
			//setInt(순서, tntwk)
			//setDouble(순서, 실수)
			pstmt.setString(1, "100");
			
			//4.실행
			//executeQuery - select문에 사용합니다.
			///executeUpdate - insert, update, delete문에 사용한다.
			rs = pstmt.executeQuery();
			
			while (rs.next()) { //다음이 있다면 true, 없으면 false
				
				//rs.getString(컬럼명) -컬럼명을 받아서 문자열 반환
				//rs.getInt(컬럼명) - 컬럼명을 받아서 정수형 반환
				//rs.getDouble(컬럼명) - 컬럼명을 받아서 실수형 반환
				//rs.getDate(컬럼명) - 컬럼명을 받아서 날짜형 반환
				
				int emp_id = rs.getInt("EMPLOYEE_ID");
				String FIRST_NAME = rs.getString("FIRST_NAME");
				String phone_number = rs.getString("PHONE_NUMBER");
				//String hire_date = rs.getString("HIRE_DATE");
				Timestamp hire_date = rs.getTimestamp("HIRE_DATE");
				int salary =rs.getInt("SALARY");
				
				
				System.out.println("======================================");
				System.out.println("아이디 : "+emp_id);
				System.out.println("이름 : "+FIRST_NAME);
				System.out.println("전화번호 : "+phone_number);
				System.out.println("입사일 : "+hire_date);
				System.out.println("급여 : "+salary);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		} finally{
			
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
			}
		}
		
		
		
		
	}


	//insert할 내용작성
	
	public void insertOne(int id, String name, String mId, String lId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//resultSet은 insert에서 필요없다.
		
		String sql = "INSERT INTO DEPTS VALUES(?,?,?,?)";
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//1.conn생성
			
			conn = DriverManager.getConnection(url,uid,upw);
			
			//2. pstmt 생성
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id );
			pstmt.setString(2,name);
			pstmt.setString(3,mId);
			pstmt.setString(4,lId);
			
			//3. sql문 실행
			
			int result = pstmt.executeUpdate(); //성공시 1 or 실패시0
			
			if(result ==1) {
				System.out.println("인서트 성공");
			}else {
				System.out.println("인서트 실패");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				conn.close();
				pstmt.close();
				
			} catch (Exception e2) {
			}
		}
		
		
		
		
		
	}
	
	
	
	
	//update할 내용 작성
	
	public void updateOne (String dName,String maId,int Id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//Main에서 부서아이디, 부서명, 매니저 아이디를만 받아서, 해당 부서의 부서명과 매니저아이디를 수정

		String sql = "update DEPTS set DEPARTMENT_NAME = ?, MANAGER_ID = ? where DepartMENT_id = ?";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			conn = DriverManager.getConnection(url,uid,upw);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dName);
			pstmt.setString(2,maId );
			pstmt.setInt(3, Id);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("Update 성공");
			}else {
				System.out.println("Update 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				conn.close();
				pstmt.close();
				
			} catch (Exception e2) {
			}
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	//delete할 내용 작성
	public void deleteOne(int id) {
		
		//Main에서 employee_id를 받아서 emps테이블에서 해당 아이디를 삭제해주세요.
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM EMPS WHERE EMPLOYEE_ID = ? ";
			
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url,uid,upw);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			int result = pstmt.executeUpdate();
			
			if(result ==1 ) {
				System.out.println("Delete 성공");
			}else {
				System.out.println("Delete 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				conn.close();
				pstmt.close();
				
			} catch (Exception e2) {
			}
			
		}
		
		
		
		
	}
	
	
	
	
	//조인을 통한 select
	public ArrayList<EmployeeVO> selectTwo() {
		
		//값을 담을 ArrayList
		ArrayList<EmployeeVO> list= new ArrayList<>();
		
		
		//사원번호, 이름, 부서명, 급여 - 급여순으로 정렬을해서 10~20번에 속해있는 데이터 
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT *\r\n"
				+ "FROM (SELECT ROWNUM AS RN, A.*\r\n"
				+ "FROM (SELECT EMPLOYEE_ID, FIRST_NAME, DEPARTMENT_NAME, SALARY\r\n"
				+ "FROM EMPLOYEES E\r\n"
				+ "JOIN DEPARTMENTS D\r\n"
				+ "ON E.DEPARTMENT_ID = d.department_id\r\n"
				+ "ORDER BY SALARY)A)\r\n"
				+ "WHERE RN >=10 AND RN <= 20";
		
		
		
		try {
			
			conn = DriverManager.getConnection(url,uid,upw);
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				int rn1 = rs.getInt("RN");
				int emp_id = rs.getInt("EMPLOYEE_ID");
				String FIRST_NAME = rs.getString("FIRST_NAME");
				String de_name = rs.getString("DEPARTMENT_NAME");
				int salary =rs.getInt("SALARY");
				
				System.out.println();
				System.out.println("======================================");
				System.out.print("순서 : "+rn1+"  ");
				System.out.print("아이디 : "+emp_id+"  ");
				System.out.print("이름 : "+FIRST_NAME+"  ");
				System.out.print("부서명 : "+de_name+"  ");
				System.out.print("급여 : "+salary);
				
				EmployeeVO vo = new EmployeeVO(rn1,FIRST_NAME,salary,de_name);
				list.add(vo);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				
				conn.close();
				pstmt.close();
				rs.close();
			
			} catch (Exception e2) {
			}
			
		}
		return list;
		
		
	}
	
	
	
	
	
	
}

