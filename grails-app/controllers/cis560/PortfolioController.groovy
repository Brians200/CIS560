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
			def nameid = """${portfolios.getString(1)}"""
			def checkbox = """<input name="_${nameid}" type="hidden"><input name="${nameid}" id="${nameid}" type="checkbox">"""
			
			portfolioList.add([checkbox,createPortfolioLink(portfolios.getString(1)),portfolios.getString(2)])
		}
		
		portfolios.close();
		
		[portfolios:portfolioList]
	}
	
	def createPortfolioLink(String pname)
	{
		return """<a href="http://localhost:8080/cis560/portfolio/singlePortfolio?portfolioName=${pname}">${pname}</a>"""
	}
	
	def deletePortfolio = {
		for(def a:params)
		{
			if( a.value.equals("on"))
			{
				def portfolioToDelete = a.key
				//pname 	
				def deleteSqlString = """Delete from Transactions where username='${session.userName}' and pname='${a.key}'"""
				SqlLogic.SetStatement(deleteSqlString)
				SqlLogic.ExecuteUpdate()
				
				def deleteOwns = """Delete from Owns where username='${session.userName}' and pname='${a.key}'"""
				SqlLogic.SetStatement(deleteOwns)
				SqlLogic.ExecuteUpdate()
				
				
				def deletePortfolio = """Delete from Portfolios where username='${session.userName}' and pname='${a.key}'"""
				SqlLogic.SetStatement(deletePortfolio)
				SqlLogic.ExecuteUpdate()
				
				chain(controller:"portfolio",action:"index",model:[portfolio:session.currentPortfolio])
				
			}
		}
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
		
		String portfolionameCheck = """INSERT INTO Transactions VALUES('${session.currentPortfolio}','${session.userName}','${params.Exchange.toUpperCase()}', '${params.Symbol.toUpperCase() }','${params.Date}','${params.Fee}','${params.Price}','${params.Type.equals("buy")?'b':'s'}' ,'${params.Quantity}')"""
		SqlLogic.SetStatement(portfolionameCheck)
		ResultSet portfolioExists = SqlLogic.ExecuteUpdate()
		
		
		//WRITE OWNS UPDATE HERE
		
		//TODO: make sure fields are not blank
		String transCheck = """SELECT * FROM Owns WHERE username = '${session.userName}' AND pname ='${session.currentPortfolio}' AND symbol = '${params.Symbol.toUpperCase()}'"""
		SqlLogic.SetStatement(transCheck)
		ResultSet transExists = SqlLogic.ExecuteQuery()
		if(transExists.next())
		{
			
				//IF transaction exists, update that owns transaction 
				if(	params.Type.equals("buy"))
				{
			
					String updateOwnsTrans = """UPDATE Owns SET quantity = quantity + '${params.Quantity}' WHERE username = '${session.userName}' AND pname = '${session.currentPortfolio}'AND symbol = '${params.Symbol.toUpperCase()}'"""
					
					SqlLogic.SetStatement(updateOwnsTrans)
					SqlLogic.ExecuteUpdate()
				
				}
				else
				{
					//Get current quantity. 
					String getQuantity = """SELECT quantity FROM Owns WHERE username = '${session.userName}' AND pname ='${session.currentPortfolio}' AND symbol = '${params.Symbol.toUpperCase()}'"""
					SqlLogic.SetStatement(getQuantity)
					ResultSet currentQuantity = SqlLogic.ExecuteQuery()
					if (currentQuantity.next())
					{
					
						int currentQuan = currentQuantity.getInt(1)
						int sellQuantity = params.int('Quantity')
						
						if(currentQuan - sellQuantity > 0)
						{
						//Update quantity 
							String updateOwnsTrans = """UPDATE Owns SET quantity = quantity - '${params.Quantity}' WHERE username = '${session.userName}' AND pname = '${session.currentPortfolio}'AND symbol = '${params.Symbol.toUpperCase()}'"""
							SqlLogic.SetStatement(updateOwnsTrans)
							SqlLogic.ExecuteUpdate()
						}
						else
						{
							//Delete Transaction with quantity 
							String deleteOwnsTrans = """DELETE FROM Owns WHERE username = '${session.userName}' AND pname = '${session.currentPortfolio}' AND symbol = '${params.Symbol.toUpperCase()}'"""
							SqlLogic.SetStatement(deleteOwnsTrans)
							SqlLogic.ExecuteUpdate()
						}
					}
				}
					
				
				
		}
		else
		{
				//Create a new owns transaction. 
				
				String createOwnsTrans = """INSERT INTO Owns VALUES('${session.currentPortfolio}', '${session.userName}','${params.Exchange.toUpperCase()}','${params.Symbol.toUpperCase()}','${params.Quantity}')"""
				SqlLogic.SetStatement(createOwnsTrans)
				SqlLogic.ExecuteUpdate()
				
		}
		transExists.close()
		
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
		
		while(portfolioTrans.next())
		{
			def nameid = """${portfolioTrans.getString(1)};${portfolioTrans.getString(2)};${portfolioTrans.getString(3)};${portfolioTrans.getString(4)};${portfolioTrans.getString(5)};${portfolioTrans.getString(6)};${portfolioTrans.getString(7)}"""
			def checkbox = """<input name="_${nameid}" type="hidden"><input name="${nameid}" id="${nameid}" type="checkbox">"""
			portfolioTransList.add([checkbox,portfolioTrans.getString(1),createSymbolLink(portfolioTrans.getString(2),portfolioTrans.getString(1)),portfolioTrans.getString(3),portfolioTrans.getString(4), portfolioTrans.getString(5),portfolioTrans.getString(6).equals('b')?"buy":"sell",portfolioTrans.getString(7)])
		}
		
		
		portfolioTransList.add(createTransactionBoxes())
		
		portfolioTrans.close();

		session.currentPortfolio = portfolio
				
		[portfolioName:portfolio,portfolioTrans:portfolioTransList]
	}
	
	String createSymbolLink(String symbol,String exchange)
	{
		"""<a href="/cis560/stock/viewStock?symbol=${symbol}&amp;exchange=${exchange}">${symbol}</a>"""
	}
	
	def createTransactionBoxes()
	{
		["",
		"""<input type="text" class="transactionCreate" name="Exchange" id="Exchange" value="" />"""
		,"""<input type="text" class="transactionCreate" name="Symbol" id="Symbol" value="" />"""
		,"""<input type="text" class="transactionCreate" name="Date" id="Date" value="" />"""
		,"""<input type="text" class="transactionCreate" name="Fee" id="Fee" value="" />"""

		,"""<input type="text" class="transactionCreate" name="Price" id="Price" value="" />"""

		,"""Buy <input type="radio" name="Type" checked="checked" value="buy" />Sell <input type="radio" name="Type" value="sell" />"""

		
		,"""<input type="text" class="transactionCreate" name="Quantity" id="Quantity" value="" />"""]

	}

	//This will remain blank, just a method to go to the create.gsp view
	def create = { }
	
	def deleteTransactions = {
		for(def a:params)
		{
			if( a.value.equals("on"))
			{
				def stockToDelete = a.key
				def stockList=stockToDelete.split(";")
				//pname 	username 	ename 	symbol 	tdate 	fee 	price 	type 	quantity
				def deleteSqlString = """Delete from Transactions where pname='${params.portfolioName}' and username='${session.userName}' and ename='${stockList[0]}' and symbol='${stockList[1]}' and tdate='${stockList[2]}' and fee='${stockList[3]}' and price='${stockList[4]}' and type='${stockList[5]}' and quantity='${stockList[6]}'"""
				SqlLogic.SetStatement(deleteSqlString)
				SqlLogic.ExecuteUpdate()
				
				def findOwnsQuantity = """SELECT sum(quantity) FROM Transactions WHERE username='${session.userName}' and ename='${stockList[0]}'  and symbol='${stockList[1]}';"""
				
			SqlLogic.SetStatement(findOwnsQuantity)
				ResultSet result = SqlLogic.ExecuteQuery()
				def quantitynew
				if(result.next())
				{
					
					quantitynew=result.getString(1)
					if(quantitynew!=null)
					{
						def upadateOwnsQuantity = """UPDATE Owns SET quantity=${quantitynew} WHERE username='${session.userName}' and ename='${stockList[0]}'  and symbol='${stockList[1]}';"""
						SqlLogic.SetStatement(upadateOwnsQuantity)
						SqlLogic.ExecuteUpdate()
					}
					else
					{
						def upadateOwnsQuantity = """Delete from Owns  WHERE username='${session.userName}' and ename='${stockList[0]}'  and symbol='${stockList[1]}';"""
						SqlLogic.SetStatement(upadateOwnsQuantity)
						SqlLogic.ExecuteUpdate()
					}
				}
				result.close()
				
				def deleteOwnsQuantity = """Delete from Owns Where quantity <= 0 or quantity = NULL;"""
				SqlLogic.SetStatement(deleteOwnsQuantity)
				SqlLogic.ExecuteUpdate()
				
			}
		}
		chain(controller:"portfolio",action:"singlePortfolio",model:[portfolio:session.currentPortfolio])
	}
	
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
	
	def viewOwns = {
		String ownershipString = """SELECT ename,symbol,sum(quantity) FROM `Owns` WHERE username='${session.userName}' Group By symbol"""
		SqlLogic.SetStatement(ownershipString)
		ResultSet ownershipResult = SqlLogic.ExecuteQuery()
		
		def ownershipList = []
		while(ownershipResult.next())
		{
			ownershipList.add([ownershipResult.getString(1),createSymbolLink(ownershipResult.getString(2),ownershipResult.getString(1)),ownershipResult.getString(3)])
		}
		[ownershipList:ownershipList]
	}
}
