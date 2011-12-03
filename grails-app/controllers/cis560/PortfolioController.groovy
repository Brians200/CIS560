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
	
	def createTransaction = {
		//Here are your variables
		//session.currentPortfolio
		//params.Exchange
		//params.Quantity
		//params.Date
		//params.Type
		//params.Fee
		//params.Price
		//params.Symbol
		
		//WRITE CREATE TRANSACTION QUERY HERE
		/*
		String portfolionameCheck = """INSERT INTO Transactions
        VALUES('${params.portfolio}', '${session.userName}','${params.Exchange}','${params.Symbol}','${params.Date}',
        '${params.Fee}','{params.Price}','${params.Type}','${params.Quantity}')"""
		SqlLogic.SetStatement(portfolionameCheck)
		ResultSet portfolioExists = SqlLogic.ExecuteUpdate()
		*/
		
		//WRITE OWNS UPDATE HERE
		/*
		//TODO: make sure fields are not blank
		String transCheck = """SELECT count(*) FROM Owns WHERE username = '${session.userName}' AND pname ='${parms.PortfolioName}' AND symbol = '${params.Symbol}''"""
		SqlLogic.SetStatement(transCheck)
		ResultSet transExists = SqlLogic.ExecuteQuery()
		if(transExists.next())
		{
			if(1==transExists.getInt(1))
			{
				//IF transaction exists, update that owns transaction 
				
				String updateOwnsTrans = """UPDATE Owns SET quantity = quantity + '${params.Quantity}' WHERE username = '${session.userName}' AND pname = '${parms.PortfolioName}'AND symbol = '${params.Symbol}'"""
				SqlLogic.SetStatement(updateOwnsTrans)
				SqlLogic.ExecuteUpdate()
				transExists.close()
				chain(action:create)
			}
			else
			{
				//Create a new owns transaction. 
				transExists.close()
				String createOwnsTrans = """INSERT INTO Owns VALUES('${parms.PortfolioName}', '${session.userName}','${params.Exchange}','${params.Symbol}','${params.Quantity}')"""
				SqlLogic.SetStatement(createOwnsTrans)
				SqlLogic.ExecuteQuery()
				chain(action:"index")
			}
		}
		else
		{
			//TODO:error with sql
		}
		*/
		//Do not touch this
		chain(controller:"portfolio",action:"singlePortfolio",model:[portfolio:session.currentPortfolio])
	}
	
	def singlePortfolio={
		
		// The portfolio they want to view is in this variable: params.portfolioName
		// The user's name is here: session.userName
		
		
		//This is a mapping of what you want to pass to the GSP, so pass in things you need to display
		
		
		def portfolio = ""
		if(chainModel!=null && chainModel.portfolio!=null)
		{
			portfolio = session.currentPortfolio
		}
		else
		{
			portfolio = params.portfolioName
		}
		
		//This is a SQL statement for getting transactions for portfolios 
		String getPortfolioTransactions = """SELECT  ename, symbol, tdate, fee, price, type, quantity FROM Transactions WHERE username = '${session.userName}' AND pname = '${portfolio}'"""
		SqlLogic.SetStatement(getPortfolioTransactions)
		ResultSet portfolioTrans = SqlLogic.ExecuteQuery()
		
		//Puts results from SQL query into an arrays
		def portfolioTransList = []
		String x;
		while(portfolioTrans.next())
		{
			x = portfolioTrans.getString(1);
			portfolioTransList.add([portfolioTrans.getString(1),portfolioTrans.getString(2),portfolioTrans.getString(3),portfolioTrans.getString(4), portfolioTrans.getString(5),portfolioTrans.getString(6),portfolioTrans.getString(7)])
		}
		
		portfolioTrans.close();

		session.currentPortfolio = params.portfolioName
				
		[portfolioName:portfolio,portfolioTrans:portfolioTransList]
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
