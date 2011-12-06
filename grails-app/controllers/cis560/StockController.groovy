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
			symbols.add([createLink(symbolsResult.getString(1),params.exchange),symbolsResult.getString(2),symbolsResult.getString(3),symbolsResult.getString(4),symbolsResult.getString(5),symbolsResult.getString(6)])
		}
		
		symbolsResult.close()
		[symbols:symbols,exchange:params.exchange]
	}
	
	String createLink(String symbol,String exchange)
	{
		"""<a href="/cis560/stock/viewStock?symbol=${symbol}&amp;exchange=${exchange}">${symbol}</a>"""
	}
	
	def transaction = { }
	
	def updateChartDates = {
		//TODO: ERROR on date checking
		Date start = params.startDate
		Date finish = params.finishDate
		def graphStart = start.calendarDate.year.toString()+"-"+start.calendarDate.month.toString()+"-"+ start.calendarDate.dayOfMonth.toString();
		def graphFinish = finish.calendarDate.year.toString()+"-"+finish.calendarDate.month.toString()+"-"+ finish.calendarDate.dayOfMonth.toString();
		
		if (finish<start)
		{
			flash.message = "Invalid Date Range"
			
		}
		
		
		chain (action:viewStock, model:[start:graphStart,finish:graphFinish,dateStart:start,dateFinish:finish])
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
			
		Date today = new Date();
		Date lastmo = today.minus(30);
		def start = lastmo.calendarDate.year.toString()+"-"+lastmo.calendarDate.month.toString()+"-"+ lastmo.calendarDate.dayOfMonth.toString();
		def finish = today.calendarDate.year.toString()+"-"+today.calendarDate.month.toString()+"-"+ today.calendarDate.dayOfMonth.toString();
			
		if(chainModel != null && chainModel.start!=null)
		{
			start = chainModel.start
			lastmo = chainModel.dateStart
			
		}
		if(chainModel != null && chainModel.finish!=null)
		{
			finish = chainModel.finish
			today = chainModel.dateFinish
		}
		def loginStatement = """select tdate, adjclose,volume, high, low, eod from History where ename='${stockExchange}' and symbol='${stockSymbol}' and tdate >='${start}' AND tdate <='${finish}' ;"""
		SqlLogic.SetStatement(loginStatement)
		ResultSet tableResult = SqlLogic.ExecuteQuery();
		def tablep = []
		def tablev = []
		def tablet = []
		boolean x = true;
		double p1 = 0;
		double p2 = 0;
		double ct = 0;
		def avg = 0.0;
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
			tablet.add([tableResult.getString(1),tableResult.getDouble(2),tableResult.getDouble(3),tableResult.getDouble(4),tableResult.getDouble(5),tableResult.getDouble(6)])
			avg = avg + tableResult.getDouble(3);
			ct++;
		}
		tableResult.close()
		avg = avg/ct;
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
		
		avg = avg*100
		avg = Math.ceil(avg)
		avg = avg/100
		avg = (int)avg
		
		String companyNameQuery = """select cname, industry, sector from Stock where symbol='${stockSymbol}'"""
		SqlLogic.SetStatement(companyNameQuery)
		ResultSet companyNameResult = SqlLogic.ExecuteQuery()
		
		def company = ""
		def industry = ""
		def sector = ""
		if(companyNameResult.next())
		{
			company = companyNameResult.getString(1)
			industry = companyNameResult.getString(2)
			sector = companyNameResult.getString(3)
		}
		
		//model to return
		[company:company, industry:industry, sector:sector, Symbol:stockSymbol, Exchange:stockExchange, Tablep:tablep, Tablev:tablev, PChange:pcs, Neg:negative, datePickerStart:lastmo, datePickerFinish:today,AverageVolume:avg,Tablet:tablet]
	}
}