package cis560;
import java.sql.*;
public class SqlLogic {
	
	private static boolean connected = false;
	private static Connection connect = null;
	
	private static PreparedStatement preparedStatement;
	
	private static void createConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://mysql.cis.ksu.edu/bsweeney","bsweeney", "a1b2c3d4e5");
			connected = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static ResultSet ExecuteQuery()
	{
		ResultSet resultSet = null;
		if(!connected)
		{
			createConnection();
		}
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public static void ExecuteUpdate()
	{
		if(!connected)
		{
			createConnection();
		}
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ClearParameters()
	{
		if(!connected)
		{
			createConnection();
		}
		try {
			preparedStatement.clearParameters();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void SetStatement(String statement)
	{
		
		if(!connected)
		{
			createConnection();
		}
		try {
			preparedStatement = connect.prepareStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void SetStringParameter(int column, String parameter)
	{
		if(!connected)
		{
			createConnection();
		}
		
		try {
			preparedStatement.setString(column, parameter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
