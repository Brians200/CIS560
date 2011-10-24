package cis560

class HomescreenController {

    def index = { 
		if(chainModel==null||chainModel.userName==null)
		{
			//NO ONE IS LOGGED IN
			//send to login screen
			flash.message = "You must log in first"
			chain(controller:"login", action:"login",model:[notLoggedIn:true])
		}
		
		//MODEL TO BE PASSED TO homescreen/index.gsp
		[userName:chainModel.userName]		
    }
}
