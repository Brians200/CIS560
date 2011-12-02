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
			
			<h2>${exchange }</h2>
			<table>
				<g:each in="${symbols}" var="symbol">
					<tr>
						<td>
							<g:link controller="stock" action="viewStock" params="[symbol:symbol[0],exchange:exchange]">
								${symbol[0]}
							</g:link>
						</td>
						<td>
							${symbol[1] }
						</td>
						<td>
							${symbol[2] }
						</td>
						<td>
							${symbol[3] }
						</td>
						<td>
							${symbol[4] }
						</td>
						<td>
							${symbol[5] }
						</td>
						
					</tr>
				</g:each>	
			</table>		
		</div>
	</body>
</html>
