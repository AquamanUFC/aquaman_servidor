package lesc.ufc.db;

import java.io.File;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lesc.ufc.database.DatabaseController;

public class DatabaseControllerTests {
	
	private static final String db = "test_database";
	
	@Before
	@After
	public void deleteFiles()
	{
		File file = new File(db + ".db");
		if(file.exists())
			file.delete();
		System.out.println("DATABASECONTROLLERTESTS - DELETADO");
	}
	
	@Test(expected = NullPointerException.class)
	public void openDatabaseNullName() throws ClassNotFoundException, SQLException {
		new DatabaseController(null);
	}
	
	@Test
	public void openDatabaseSuccess() throws ClassNotFoundException, SQLException {
		new DatabaseController(db);
	}

	
	@Test
	public void createTable() throws ClassNotFoundException, SQLException {
		deleteFiles();
		DatabaseController databaseController = new DatabaseController(db);
		databaseController.createTable("users","name TEXT");
	}
	
	@Test(expected= SQLException.class)
	public void createTableDuplicated() throws ClassNotFoundException, SQLException {
		DatabaseController databaseController = new DatabaseController(db + '2');
		databaseController.createTable("users","name TEXT");
		databaseController.createTable("users","name TEXT");
	}
	
}
