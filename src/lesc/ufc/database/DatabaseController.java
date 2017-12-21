package lesc.ufc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
	
	private String database;
	private Connection con; 
	
	private final String className = "org.sqlite.JDBC";
	
	public DatabaseController(String databaseName) throws ClassNotFoundException, SQLException {
		if(databaseName == null)
			throw new NullPointerException();
		
		this.database = databaseName;
		Class.forName(className);
		con = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
		System.out.println("DATABASECONTROLLER - Open Database");
	}

	public void createTable(String table_name, String ...attributes) throws SQLException {
		String sql = "CREATE TABLE " + table_name + "(";
		for(String attribute : attributes) {
			sql = sql.concat(attribute + "");
		}
		
		sql = sql.concat(")" ); 
		System.out.println("DATABASECONTROLLER - " + sql);
		Statement statement = con.createStatement();
		statement.executeUpdate(sql);
	}
}
