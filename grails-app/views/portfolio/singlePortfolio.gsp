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


		
		<h2>Portfolio: ${portfolioName }</h2>
		
		<% 
   def Columns = [['string','Select'],['string', 'Exchange'], ['string', 'Stock'],['string', 'Date'],['string', 'Fee'],['string', 'Price'],['string', 'Type'],['string', 'Quantity']]
%><gvisualization:table elementId="table_div" allowHtml="${true}" showRowNumber="${false}" 
 columns="${Columns}" data="${portfolioTrans}" />
		<form action="/cis560/portfolio/index" method="post" >
			<div id="table_div"></div>
		<input type="submit" name="_action_createTransaction" value="Create" />
		<input type="submit" name="_action_deleteTransactions" value="Delete"/></form>
		
	</div>
</body>
</html>
