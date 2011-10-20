<html>
	<head>
	
	</head>
	<body>
		<g:form name="loginScreen" method="post">
			Username
			<g:textField name="userName"/>
			<br>
			Password
			<input name="password" type="password"/>
			<g:actionSubmit value="Login" action="validateUser"/>
		</g:form>
	</body>
</html>
