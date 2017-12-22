package lesc.ufc.db;

import java.io.File;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lesc.ufc.database.DatabaseController;

public class DatabaseControllerTests {
	
	private static final String databaseName = "test_database";
	private DatabaseController databaseController;
	
	@Before
	@After
	public void deleteFiles() throws ClassNotFoundException, SQLException
	{
		File file = new File(databaseName + ".db");
		if(file.exists()) {
			file.delete();
			System.out.println("DATABASECONTROLLERTESTS - DELETADO");
		}
		initDatabase();
	}
	
	public void initDatabase() throws ClassNotFoundException, SQLException {

		databaseController = new DatabaseController(databaseName);
	}
	
	@Test(expected = NullPointerException.class)
	public void openDatabaseNullName() throws ClassNotFoundException, SQLException {
		System.out.println("TEST OPENDATABASENULLNAME");
		databaseController = new DatabaseController(null);
	}
	
	@Test
	public void openDatabaseSuccess() throws ClassNotFoundException, SQLException {
		System.out.println("TEST OPENDATABASESUCESS");
		databaseController = new DatabaseController(databaseName);
	}

	
	@Test
	public void createTable() throws ClassNotFoundException, SQLException {
		System.out.println("TEST CREATETABLE");
		databaseController.createTable("table1","name TEXT");
	}
	
	@Test(expected= SQLException.class)
	public void createTableDuplicated() throws ClassNotFoundException, SQLException {
		System.out.println("TEST CREATETABLEDUPLICATED");
		databaseController.createTable("table2","name TEXT");
		databaseController.createTable("table2","name TEXT");
	}

	@Test
	public void associateTableHasMany() throws SQLException {
		databaseController.createTable("users","name TEXT");
		databaseController.createTable("consumption","name TEXT");
		databaseController.addTablesHasMany("users","consumption");
	}
	
}
