package com.jdbc.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass  {
	public static void main(String[] args) {
		//Scanner scan = new Scanner(System.in);
		Model model = new Model();
		//model.selectOne();
		
//		System.out.print("부서 아이디 > ");
//		int id = scan.nextInt();
//		
//		System.out.print("부서이름 > ");
//		String name = scan.next();
//		
//		System.out.print("매니저 아이디 > ");
//		String mId = scan.next();
//		
//		System.out.print("부서 아이디 > "); 
//		String lId = scan.next();
//		
//		model.insertOne(id, name, mId,lId);
		
		
//		System.out.println("업데이트할 부서 아이디 > ");
//		int id = scan.nextInt();
//		
//		System.out.println("업데이트 할 부서 이름 > ");
//		String name = scan.next();
//		
//		System.out.println("업데이트 할 매니저 명> ");
//		String maName = scan.next();
//		
//		model.updateOne(name,maName,id );
		
		//System.out.println("삭제할 Id >");
		//int id = scan.nextInt();
		
		//model.deleteOne(id);
		
		ArrayList<EmployeeVO> list = model.selectTwo();
		
		for(EmployeeVO vo:list) {
		System.out.println("===================================");
		System.out.println(vo.getEmployeeId());
		System.out.println(vo.getFirstName());
		System.out.println(vo.getDepartmentName());
		System.out.println(vo.getSalary());
	}
}
}