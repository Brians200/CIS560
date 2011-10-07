package cis560
import groovy.sql.Sql

import java.awt.List;
import java.sql.*
import java.util.Date;
class MysqltestController {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
    def index = { 
		
		def names = []
		def prices = []
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup the connection with the DB
		try {
			connect = DriverManager.getConnection("jdbc:mysql://mysql.cis.ksu.edu/bsweeney","bsweeney", "a1b2c3d4e5")
			preparedStatement = connect.prepareStatement("select * from Sales")
			resultSet = preparedStatement.executeQuery()
			while(resultSet.next())
			{
				names.add(resultSet.getString("name"))
				prices.add(resultSet.getString("price"))
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return [names:names,prices:prices]
	}
}
