package cis560
import java.sql.*;
import cis560.SqlLogic
class UserController {

	def beforeInterceptor = [action:this.&auth]
	
	def auth() {
		if(!session.userName&&!session.userName.equals("admin")) {
			flash.message = "You must be an admin to access the user control screen"
			redirect(controller:"login", action:"login")
			return false
		}
	}
	
	
    def index = {
		SqlLogic.SetStatement("select username from Users")
		ResultSet users = SqlLogic.ExecuteQuery()
		def userList = []
		while(users.next())
		{
			userList.add(users.getString(1))
		}
		users.close();
		render("hey")	
	}
}
