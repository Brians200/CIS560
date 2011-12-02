package cis560

import java.sql.ResultSet

class PortfolioController {

	def beforeInterceptor = [action:this.&auth]
	
	def auth() {
		if(!session.userName&&!session.userName.equals("admin")) {
			flash.message = "You must be logged in to access portfolios"
			redirect(controller:"login", action:"login")
			return false
		}
	}
	
	//This is overview
    def index = { 
		SqlLogic.SetStatement("SELECT pname,description FROM Portfolios WHERE username = '$session.userName'")
		ResultSet portfolios = SqlLogic.ExecuteQuery()
		def portfolioList = []
		while(portfolios.next())
		{
			portfolioList.add([portfolios.getString(1),portfolios.getString(2)])
		}
		
		portfolios.close();
		
		[portfolios:portfolioList]
	}
	
	def delete = { }
	
	def singlePortfolio={
		
		// The portfolio they wanna view is in this variable: params.portfolioName
		// The user's name is here: session.userName
		
		
		//This is a mapping of what you want to pass to the GSP, so pass in things you need to display
		[portfolioName:params.portfolioName]
	}
	
	
	//This will remain blank, just a method to go to the create.gsp view
	def create = { }
	
	def createPortfolioMySQL = {
	
		//TODO: make sure fields are not blank
		String portfolionameCheck = """select count(*) from Portfolios where username='${session.userName}' and pname='${params.PortfolioName}'"""
		SqlLogic.SetStatement(portfolionameCheck)
		ResultSet portfolioExists = SqlLogic.ExecuteQuery()
		if(portfolioExists.next())
		{
			if(1==portfolioExists.getInt(1))
			{
				//portfolio already exists, prompt for new name
				flash.message = "A portfolio already exists, please pick a different name"
				portfolioExists.close()
				chain(action:create)
			}
			else
			{
				portfolioExists.close()
				String createportfolio = """insert into Portfolios values('${session.userName}','${params.PortfolioName}','${params.PortfolioDescription}')"""
				SqlLogic.SetStatement(createportfolio)
				SqlLogic.ExecuteUpdate()
				chain(action:"index")
			}
		}
		else
		{
			//TODO:error with sql
		}
	}
}
