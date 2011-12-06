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
			<div align="center">
			<g:form controller="user" method="post">
				<h1> Welcome to Stocks </h1>
				<h2> Get started tracking stocks by <g:link controller="login" action = "login" >loging in</g:link> or <g:link controller="login" action ="createUserScreen">create a username</g:link>. <br /> <br /> Dont want to Login? Search a symbol to view data.<br /> <br /> Click <g:link controller="stock" view="index">Stocks</g:link> to list Symbols and Markets </h2>
				<br>
				<g:if test="${userName=='admin' }">
					<g:actionSubmit value="Edit Users" action="index"/>
				</g:if>
			</g:form>
			</div>
		</div>
	</body>
</html>
