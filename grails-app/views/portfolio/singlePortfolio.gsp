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
			
			<table>
			<tr><td>Exchange</td>
			<td>Stock</td></tr>          
			<g:each in="${portfolioTrans}" var="Trans">
				<tr>
				
				<td>${Trans[0]}</td>
				<td>${Trans[1]}</td>
				<td>${Trans[2]}</td>
				<td>${Trans[4]}</td>
				<td> ${Trans[5]}</td>
				<td>${Trans[6]}</td>
				<td> ${Trans[7]}</td> 
						
				</tr>
			</g:each>
			</table>
		</div>
	</body>
</html>
