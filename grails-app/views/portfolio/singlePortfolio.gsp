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
			
			
			${session.userName } 's ${portfolioName } portfolio 
			
			           
			<g:each in="${portfolioTrans}" var="Trans">
				<p>
					
					${Trans[0]} -  ${Trans[1]} - ${Trans[2]} - ${Trans[4]} - ${Trans[5]} - ${Trans[6]} - ${Trans[7]} 
						
				</p>
			</g:each>
			
		</div>
	</body>
</html>
