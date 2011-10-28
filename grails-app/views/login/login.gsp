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
			<g:form name="loginScreen" method="post">
				Username
				<g:textField name="userName"/>
				<br>
				Password&nbsp;
				<input name="password" type="password"/>
				<br>
				<g:actionSubmit value="Login" action="validateUser"/>
			</g:form>
		</div>
	</body>
</html>