package cis560
import java.sql.*;
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
		render("hey")	
	}
}
