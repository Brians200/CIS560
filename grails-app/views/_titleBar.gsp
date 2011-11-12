<div class="titlebar">
	<div>
		<div id="titleText">
			Stocks and Stuff
		</div>
		<div id="loginboxes">
			<g:loginHeader/>
		</div>
	</div>
	<div id="bottomHeader">
		<g:form controller="stock" method="post">
			Stock Symbol
			<g:textField class="symbolExchange" name="stockSymbol" width=5/>
			Exchange
			<g:textField class="symbolExchange" name="exchange" width=5/>
			<g:actionSubmit value="Search" action="viewStock"/>
		</g:form>
	</div>
</div>