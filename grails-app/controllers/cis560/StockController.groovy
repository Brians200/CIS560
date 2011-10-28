package cis560

class StockController {

    def index = {
		
		chain(controller:"stock",action:"viewStock")
	}
	
	def viewStock={
		
		def stockSymbol
		def stockExchange
		if(chainModel!=null&&chainModel.Symbol!=null&&chainModel.Exchange!=null)
		{
			stockSymbol = chainModel.Symbol
			stockExchange = chainModel.Exchange
		}
		
		
		//model to return
		[Symbol:stockSymbol, Exchange:stockExchange]
	}
	
}
