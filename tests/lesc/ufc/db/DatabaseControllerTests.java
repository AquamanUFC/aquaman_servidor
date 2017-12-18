package lesc.ufc.db;

import java.sql.SQLException;

import org.junit.Test;

import lesc.ufc.database.DatabaseController;

public class DatabaseControllerTests {

	@Test
	public void openDatabaseSuccess() throws ClassNotFoundException, SQLException {
		new DatabaseController("data");
	}

	@Test(expected = NullPointerException.class)
	public void openDatabaseNullName() throws ClassNotFoundException, SQLException {
		new DatabaseController(null);
	}
}
