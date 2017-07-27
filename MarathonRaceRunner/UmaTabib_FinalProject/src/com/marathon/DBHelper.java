package com.marathon;
import java.util.*;
import java.sql.*;


/**
 * Helper class to connect and fetch data from Derby database(FinalDB) present under 'Resources' folder of eclipse project.
 * @author Uma
 *
 */
public class DBHelper {

	/**
	 * Function that creates and returns database connection.
	 * @return database connection.
	 */
	private Connection connect()
	{
		Connection connection = null;
		try
		{
			String dbDirectory = "Resources";
			System.setProperty("derby.system.home", dbDirectory);

			String url = "jdbc:derby:FinalDB";
			String user = "";
			String password = "";
			connection = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e)
		{
			System.err.println("Error loading database driver: " + e);
		}
		return connection;
	}

	/**
	 * Function that fetches runners information from database.
	 * @return ArrayList consisting of Runner DAOs'
	 */
	public ArrayList<Runner> fetchDBRunners()
	{
		ArrayList<Runner> ar = new ArrayList<>();
		try
		{
			Connection connection = connect();

			String query = "SELECT * FROM RunnerInfo";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				String name = rs.getString("Name");
				double runnersSpeed = rs.getDouble("RunnersSpeed");
				double restPercentage = rs.getDouble("RestPercentage");

				Runner r = new Runner(name, restPercentage, runnersSpeed);	                
				ar.add(r);
			}
			System.out.println();
			rs.close();
			ps.close();
			connection.close();
		}
		catch(SQLException sqle)
		{
			System.out.println("Error:" + sqle.toString());
			//sqle.printStackTrace();  // for debugging
		}
		return ar;
	}

}
