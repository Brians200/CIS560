<html>
	<head>
		<meta name="layout" content="main" />
	</head>
	<body>
		<div class="loginPadding">
			<g:if test="${flash.message}">
				<div class="loginerrors">	
					${flash.message}
				</div>
			</g:if>
			<g:each in="${portfolios}" var="portfolio">
				<p>
					<g:link controller="portfolio" action="singlePortfolio" params="[portfolioName:portfolio[0]]">
						${portfolio[0]} - ${portfolio[1]} -${portfolio[2]} - ${portfolio[3]} - ${portfolio[4]} -${portfolio[5]}-${portfolio[6]}
					</g:link>
				</p>
			</g:each>
			
			<g:form>
				<g:actionSubmit value="New Portfolio" action="create"/>
			</g:form>
			
		</div>
	</body>
</html>
