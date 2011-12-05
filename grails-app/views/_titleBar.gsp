<div class="titlebar">
	<div id="topHeader">
		<span id="titleText"> Stocks  </span> <span id="loginboxes">
			<g:loginHeader />
		</span>
	</div>
	<br /> <br /> <br /> <br /> <br /> <br />
	<div id="bottomHeader">
		<g:form controller="stock" method="post">
			Stock Symbol
			<g:textField class="symbolExchange" name="stockSymbol" width=5 />
			<g:actionSubmit value="Search" action="viewStock" />
		</g:form>
	</div>
	<div id="linksToControllersDIV" style="color: #FFF">
		<g:if test="${session.userName}">
			<g:link class="linksToControllers" controller="portfolio" action="index">Portfolios</g:link>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<g:link class="linksToControllers" controller="portfolio" action="viewOwns">Profile</g:link>
		</g:if>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<g:link class="linksToControllers" controller="stock" view="index">Stocks</g:link>
	</div>
</div>