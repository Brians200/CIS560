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
			<% 
			   def Columns = [['string', 'Select'], ['string', 'Portfolio Name'],['string', 'Descripton']]
			%><gvisualization:table elementId="table_div" allowHtml="${true}" showRowNumber="${false}" 
			 columns="${Columns}" data="${portfolio}" />
			<div id="table_div"></div>
			
			<g:form>
				<g:actionSubmit value="New Portfolio" action="create"/>
			</g:form>
		</div>
	</body>
</html>
