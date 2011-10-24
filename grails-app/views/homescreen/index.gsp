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
			<div class="hiddenDiv">
				<g:textField name="userName" value="${userName }"/>
			</div>
			<g:form controller="user" method="post">
				Welcome ${userName }
				<br>
				<g:if test="${userName=='admin' }">
					<g:actionSubmit value="Edit Users" action="index"/>
				</g:if>
			</g:form>
		</div>
	</body>
</html>
