<html>
	<head>
		<meta name="layout" content="main" />
		<script type='text/javascript' src='https://www.google.com/jsapi'></script>
	</head>
	<body>
		<div class="loginPadding">
			<g:if test="${flash.message}">
				<div class="loginerrors">	
					${flash.message}
				</div>
			</g:if>
			
			<g:form>
			<div align="center">
			<h2>Your Portfolios:</h2>
		<br />
			<% 
			   def Columns = [['string', 'Select'], ['string', 'Portfolio Name'],['string', 'Descripton']]
			%><gvisualization:table elementId="table_div" width="${768}" allowHtml="${true}" showRowNumber="${false}" 
			 columns="${Columns}" data="${portfolios}" />
			<div id="table_div"></div>
			
			<g:actionSubmit value="Delete Selected" action="deletePortfolio"/>
				<g:actionSubmit value="New Portfolio" action="create"/>
				
			</g:form>
			</div>
		</div>
	</body>
</html>
