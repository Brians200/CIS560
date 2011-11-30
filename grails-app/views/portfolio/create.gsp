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
			<g:form>
				Portfolio Name: 
				<g:textField name="PortfolioName"/>
				<br/>
				Description
				<g:textArea name="PortfolioDescription"/>
				<br/>
				<g:actionSubmit value="Create Portfolio" action="createPortfolioMySQL"/>
			</g:form>
		</div>
	</body>
</html>