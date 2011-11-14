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
			<g:form>
				Username
				<g:textField name="Username"></g:textField>
				<br/>
				Password
				&nbsp;<g:passwordField name="Password"></g:passwordField>
				<br/>
				<g:actionSubmit value="Create User" action="createUserMySQL"/>
			</g:form>
		</div>
	</body>
</html>