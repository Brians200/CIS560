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
			<h2>${exchange }</h2>
		
			<% 
			   def Columns = [['string', 'Symbol'], ['string', 'Company Name'],['string', 'IPO Year'],['number', 'Market Capacity'],['string', 'Industry'],['string', 'Sector']]
			%><gvisualization:table elementId="table_div" allowHtml="${true}" showRowNumber="${false}" 
			 columns="${Columns}" data="${symbols}" />
			<div id="table_div"></div>
		</div>
	</body>
</html>
