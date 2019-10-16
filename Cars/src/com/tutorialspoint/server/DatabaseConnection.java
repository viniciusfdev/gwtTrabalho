package com.tutorialspoint.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
	
	private static Connection conn = null;
	private static final String USER = "root";
	private static final String PASS = "32813538";
	private static final String DB_NAME = "garagem";
	private static final String TIME_ZONE = "?useTimezone=true&serverTimezone=America/Sao_Paulo";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + TIME_ZONE;	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PROXY_HOST = "proxy.monte.ufu.br";
	private static final String PROXY_PORT = "3128";
	private static final String PROXY_USER = "eccampos@ufu.br";
	private static final String PROXY_PASS = "1464176";
	
	
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		if (conn == null) {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conexao estabelecida com sucesso!");
		}
		return conn;
	}
	
	public static Connection getDBConnectionWithProxy() throws ClassNotFoundException, SQLException {
		if (conn == null) {
			Class.forName(DRIVER);
			Properties info = new Properties();
			
			// info.put("proxy_type", "4"); // SSL Tunneling
			info.put("proxy_host", PROXY_HOST);
			info.put("proxy_port", PROXY_PORT);
			info.put("proxy_user", PROXY_USER);
			info.put("proxy_password", PROXY_PASS);
			info.put("user", USER);
			info.put("password", PASS);
			conn = DriverManager.getConnection(URL, info);
		}
		return conn;
	}
	
	public static void closeDBConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
