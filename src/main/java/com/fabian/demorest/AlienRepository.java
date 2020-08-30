package com.fabian.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
	
	Connection con = null;
	
	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/restdb";
		String username= "root";
		String password = "1234";
		try {
			con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public List<Alien> getAliens(){
		List<Alien> alien = new ArrayList<>();
		String sql = "select * from alien;";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getNString(2));
				a.setPoints(rs.getInt(3));
				
				alien.add(a);
			}
				
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return alien;
	}
	
	public Alien getAlien(int id) {
		
		String sql = "select * from alien where id ="+ id +";";
		Alien a = new Alien();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			if(rs.next()) {
				
				a.setId(rs.getInt(1));
				a.setName(rs.getNString(2));
				a.setPoints(rs.getInt(3));
				
			}
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return a; 
	}

	public void create(Alien a1) {
		String sql = "insert into alien values(?,?,?)";
		Alien a = new Alien();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			st.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public String toString() {
		return "AlienRepository [aliens=" + getAliens().toString() + "]";
	}
}
