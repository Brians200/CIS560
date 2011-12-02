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
			
			<p>Select an exchange</p>
			
			<g:each in="${exchanges}" var="exchange">
				<p>
					<g:link controller="stock" action="listStocks" params="[exchange:exchange]">
						${exchange}
					</g:link>
				</p>
			</g:each>			
		</div>
	</body>
</html>
