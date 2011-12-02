package cis560
import java.sql.ResultSet


class StockController {

    def index = {
		
		
		chain(controller:"stock",action:"listExchanges")
	}
	
	def listExchanges = {
		String exchangesSqlString = """select ename from Exchange"""
		SqlLogic.SetStatement(exchangesSqlString)
		ResultSet exchangesResult = SqlLogic.ExecuteQuery()
		
		def exchanges = []
		
		while(exchangesResult.next())
		{
			exchanges.add(exchangesResult.getString(1))
		}
		exchangesResult.close()
		[exchanges:exchanges]
	}
	
	def listStocks = {
		if (params.exchange ==null)
		{
			chain (controller:"stock", action:"listExhanges")
		}
		
		String symbolsSqlString = """select symbol, cname, ipoyear, marketCap, industry, sector from Stock where ename='${params.exchange}'"""
		SqlLogic.SetStatement(symbolsSqlString)
		ResultSet symbolsResult = SqlLogic.ExecuteQuery()
		
		def symbols = []
		
		while(symbolsResult.next())
		{
			symbols.add([symbolsResult.getString(1),symbolsResult.getString(2),symbolsResult.getString(3),symbolsResult.getString(4),symbolsResult.getString(5),symbolsResult.getString(6)])
		}
		
		symbolsResult.close()
		[symbols:symbols,exchange:params.exchange]
	}
	
	def transaction = { }
	
	def updateChartDates = {
		//TODO: ERROR on date checking
		Date start = params.startDate
		Date finish = params.finishDate
		def graphstart = start.year.toString()+"_"+start.month.tostring()+"_"+start.day()
		def graphfinish = finish.year.toString()+"_"+finish.month.tostring()+"_"+finish.day()
		chain (action:viewStock, model:[start:start,finish:finish])
	}
	
	
	def viewStock = {
		
		def stockSymbol = "FCCY"
		def stockExchange = "NASDAQ"
		if(params.symbol!=null&&params.exchange!=null)
		{
			stockSymbol = params.symbol
			stockExchange = params.exchange
		}
		else if(params.stockSymbol!=null)
		{
			stockSymbol = params.stockSymbol.toUpperCase()
			String exchangeQuery = """select ename from Stock where symbol='${params.stockSymbol}'""".toString()
			SqlLogic.SetStatement(exchangeQuery)
			ResultSet exchangeResult = SqlLogic.ExecuteQuery()
			
			if(exchangeResult.next())
			{
				stockExchange=exchangeResult.getString(1)
			}
			else
			{
//TODO: RENDER THAT THE SYMBOL DOES NOT EXIST
			}
			exchangeResult.close()
			
		}
		else if(chainModel!=null&&chainModel.Symbol!=null&&chainModel.Exchange!=null)
		{
			stockSymbol = chainModel.Symbol
			stockExchange = chainModel.Exchange
		}
		//FIX THIS
		def start = "2011-06-1"
		def end = "2011-07-10"
		if(chainModel != null && chainModel.start!=null)
		{
			start = chainModel.start
		}
		if(chainModel != null && chainModel.finish!=null)
		{
			end = chainModel.finish
		}
		def loginStatement = "select tdate, adjclose, volume from History where ename='${stockExchange}' and symbol='${stockSymbol}' and tdate>='${start}' ;"
		SqlLogic.SetStatement(loginStatement)
		ResultSet tableResult = SqlLogic.ExecuteQuery();
		def tablep = []
		def tablev = []
		boolean x = true;
		double p1
		double p2
		while(tableResult.next())
		{
			if (x == true)
			{
				p1 = tableResult.getDouble(2);
				x = false;
			}
			p2 = tableResult.getDouble(2);
			tablep.add(["'"+tableResult.getString(1)+"'",tableResult.getDouble(2)])
			tablev.add(["'"+tableResult.getString(1)+"'",tableResult.getDouble(3)])
		}
		tableResult.close()
		double pchange = (((p2-p1)/p1)*100)*100;
		pchange = Math.ceil(pchange)/100;
		boolean negative = false
		if(pchange<0)
		{
			negative = true
		}
		String pcs;
		if (negative)
		{
			pcs = "("+pchange+"%)"
		}
		pcs = "("+pchange+"%)"
		//model to return
		[Symbol:stockSymbol, Exchange:stockExchange, Tablep:tablep, Tablev:tablev, PChange:pcs, Neg:negative]
	}
}