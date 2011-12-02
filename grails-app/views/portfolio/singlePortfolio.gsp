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


		${session.userName }'s
		${portfolioName }

		
		<table>
			<tr>
				<td>Exchange</td>
				<td>Stock</td>
				<td>Date</td>
				<td>Fee</td>
				<td>Price</td>
				<td>Type</td>
				<td>Quantity</td>
			</tr>
			<tr>
				<g:form>
					<td><g:textField class="transactionCreate" name="Exchange" /></td>
					<td><g:textField class="transactionCreate" name="Symbol" /></td>
					<td><g:textField class="transactionCreate" name="Date" /></td>
					<td><g:textField class="transactionCreate" name="Fee" /></td>
					<td><g:textField class="transactionCreate" name="Price" /></td>
					<td><g:textField class="transactionCreate" name="Type" /></td>
					<td><g:textField class="transactionCreate" name="Quantity" /></td>
					<td><g:actionSubmit value="Create" action="createTransaction"/></td>
				</g:form>
			</tr>
			<g:each in="${portfolioTrans}" var="Trans">
				<tr>

					<td>
						${Trans[0]}
					</td>
					<td>
						${Trans[1]}
					</td>
					<td>
						${Trans[2]}
					</td>
					<td>
						${Trans[3]}
					</td>
					<td>
						${Trans[4]}
					</td>
					<td>
						${Trans[5]}
					</td>
					<td>
						${Trans[6]}
					</td>
					<td>
						${Trans[7]}
					</td>

				</tr>
			</g:each>
		</table>
	</div>
</body>
</html>
