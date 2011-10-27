package cis560
import java.sql.*;
import cis560.SqlLogic;
class LoginController {

	private static Connection connect = null;
	
    def login = { 

	}
	
	def validateUser = {
		
		try {
			String loginStatement = "select count(*) from Users where username=? and passwd=PASSWORD(?)"
			SqlLogic.SetStatement(loginStatement)
			SqlLogic.ClearParameters();
			SqlLogic.SetStringParameter(1, params.userName)
			SqlLogic.SetStringParameter(2,params.password)
			
			
			ResultSet loginResult = SqlLogic.ExecuteQuery();
			loginResult.next()
			//0 bad login 
			//1 good login
			int loginInt = loginResult.getInt(1)
			loginResult.close()
		
			if(loginInt==1)
			{
				session.userName = params.userName;
				chain(controller:"homescreen",action:"index")
			}
			else
			{
				flash.message = "Invalid Username/Password combination"
				chain(controller:"login", action:"login",model:[badLogin:true])
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			render("There is an error")
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			render("There is an error")
		}
		
		
		
	
	}
}
