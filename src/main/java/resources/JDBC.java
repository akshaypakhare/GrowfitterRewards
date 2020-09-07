package resources;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host  + ":" + port  + "/qa_db", "root", "");
		
	}

}
*/

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBC {

	public static Properties loadPropertiesFile() throws Exception {

		Properties prop = new Properties();
		InputStream in = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/growfitter/jdbc.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	static Connection conn = null;
	public static Connection getConnection() throws Exception {
      if (conn != null) return conn;
      
      	Properties prop = loadPropertiesFile();
      	
      	String driverClass = prop.getProperty("MYSQLJDBC.driver");
		String url = prop.getProperty("MYSQLJDBC.url");
		String username = prop.getProperty("MYSQLJDBC.username");
		String password = prop.getProperty("MYSQLJDBC.password");
		
      return getConnection(username, password, url, driverClass);
   }
   
	private static Connection getConnection(String username, String password, String url, String driverClass) {
      try {
         Class.forName(driverClass);
         conn = DriverManager.getConnection(url + "?user=" + username + "&password=" + password);
         
         if (conn != null) {
				System.out.println("connection created successfully using properties file");
			}

			else {
				System.out.println(" unable to create connection");
			}

         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return conn;
   }

}

