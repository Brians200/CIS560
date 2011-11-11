<div class="titlebar">
	<div id="titleText">
		Stocks and Stuff
	</div>
	<div id="loginboxes">
		<g:loginHeader/>
		<g:form controller="stock" method="post">
			Search Stock Symbol
			<g:textField name="stockSymbol"/>
			<g:actionSubmit value="Search" action="viewStock"/>
		</g:form>
	</div>
</div>