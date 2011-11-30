package cis560
class HomescreenController {

	/*def beforeInterceptor = [action:this.&auth]
	
	def auth() {
		if(!session.userName) {
			flash.message = "You must log in first"
			redirect(controller:"login", action:"login")
			return false
		}
	}*/
	
	
    def index = { 
		
		if(session.userName!=null&&session.userName!="admin")
		{
			chain(controller:"portfolio",action:"index")
		}
		//MODEL TO BE PASSED TO homescreen/index.gsp
		[userName:session.userName]		
    }
}
