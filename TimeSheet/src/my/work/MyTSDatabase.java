package my.work;

import java.io.File;
import java.sql.*;
import java.util.Date;

public class MyTSDatabase {
	private Connection c = null;

	public void openDB () {
		boolean createTable = true;
		File f = new File ("C:/temp/TimeSheetDb.db");
		if (f.exists()) {
			System.out.println("DB already exists. Connect only");
			createTable = false;
		}
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:C:/temp/TimeSheetDb.db");
			System.out.println("Opened database successfully");
		} catch ( Exception e ) {
			System.err.println("openDB :" + e.getClass().getName() + ": " + e.getMessage() );
		}

		if (createTable) {
			try {
				stmt = c.createStatement();
				String sql = "CREATE TABLE Events " +
						"(UID INT PRIMARY KEY     NOT NULL," +
						" Description    TEXT     NOT NULL, " + 
						" Day            DATE     NOT NULL, " + 
						" StartTime      TIME     NOT NULL, " +
						" EndTime        TIME     NOT NULL, " +
						" Duration       INT)"; 
				stmt.executeUpdate(sql);
				stmt.close();
			} catch ( Exception e ) {
				System.err.println("openDB :" + e.getClass().getName() + ": " + e.getMessage() );
			}
			System.out.println("Table created successfully");
		}
	}

	public void closeDB() {
		try {
			c.close();
		} catch (SQLException e) {
			System.err.println("closeDB :" + e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public void startTask (String task, int uid) {
		Statement stmt = null;
		Date dt = new java.sql.Date(new java.util.Date().getTime());
		Timestamp ts = new java.sql.Timestamp(new java.util.Date().getTime());
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO Events (UID, Description, Day, Starttime, EndTime, Duration) " +
					"VALUES ("+uid+",'"+task+"','"+dt+"','"+ts+ "','" + ts+"',0);"; 
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			System.err.println("startTask :" + e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public void endTask (int uid) {
		Statement stmt = null;
		Timestamp ts = new java.sql.Timestamp(new java.util.Date().getTime());
		try {
			stmt = c.createStatement();
			String sql = "UPDATE Events set EndTime = '"+ts+"' where UID="+uid+";";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("endTask :" + e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public void pauseTask (int uid) {

	}

	public void resumeTask (int uid) {

	}
}
