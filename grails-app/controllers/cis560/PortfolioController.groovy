package cis560

import java.sql.ResultSet

class PortfolioController {

	//This is overview
    def index = { 
		SqlLogic.SetStatement("SELECT pname FROM Portfolios WHERE username = '$session.userName'")
		ResultSet portfolios = SqlLogic.ExecuteQuery()
		def portfolioList = []
		while(portfolios.next())
		{
			portfolioList.add(portfolios.getString(1))
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
	
	
	def create = { }
}
