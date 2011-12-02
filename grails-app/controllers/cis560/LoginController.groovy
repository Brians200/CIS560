package cis560
import java.sql.*;
import cis560.SqlLogic;
class LoginController {


	//blank method. just goes to the login.gsp view
    	def login = { 

	}
	
	def logout = {
		session.userName = null;
		chain(controller:"homescreen",action:"index")
	}
	
	//blank method. just goes to the login.gsp view
	def createUserScreen = { 
			
	}
	
	def createUserMySQL = {
		
		//TODO: make sure fields are not blank
		String usernameCheck = """select count(*) from Users where username='${params.Username}'"""
		SqlLogic.SetStatement(usernameCheck)
		ResultSet userExists = SqlLogic.ExecuteQuery()
		if(userExists.next())
		{
			if(1==userExists.getInt(1))
			{
				//User already exists, prompt for new name
				flash.message = "User already exists, please pick a different name"
				userExists.close()
				chain(action:createUserScreen)
			}
			else
			{
				userExists.close()
				String createUser = """insert into Users values('${params.Username}','${params.Email}',Password('${params.Password}'))"""
				SqlLogic.SetStatement(createUser)
				SqlLogic.ExecuteUpdate()
				session.userName = params.Username
				chain(controller:"homescreen",action:"index")
			}
		}
		else
		{
			//TODO:error with sql
		}
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
