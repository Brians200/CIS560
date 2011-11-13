package cis560

class LoginHeaderTagLib {
	def loginHeader ={
		if(session.userName!=null)
		{
			// logged in, display a log out button
			
			out<< "<form method=\"post\" action=\"/cis560/login\">"
			out<< session.userName
			out<< "&nbsp;&nbsp;&nbsp;&nbsp;"
			out<< "<input type=\"submit\" value=\"logout\" name=\"_action_logout\">"
			out<< "</form>"
		}
		else
		{
			// not logged in, display a log out button
			
			out<< "<form id=\"loginScreen\" name=\"loginScreen\" method=\"post\" action=\"/cis560/login\">"
			out<< "Username"
			out<< "<input id=\"userName\" type=\"text\" value=\"\" name=\"userName\">"
			out<< "<br>"
			out<< "Password&nbsp;"
			out<< "<input type=\"password\" name=\"password\">"
			out<< "<br>"
			out<< "<input type=\"submit\" value=\"Login\" name=\"_action_validateUser\">"
			out<< "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			out<< "<input type=\"submit\" value=\"New User\" name=\"_action_createUser\">"
			out<< "</form>"
		}
	}
}
