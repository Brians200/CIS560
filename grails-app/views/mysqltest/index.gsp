<html>
	<head>

	</head>
	<body>
	<div style="width='300'">
		<div style="float:left">
		<g:each var="c" in="${names }">
			<li class="controller">${c}</li>
        </g:each>
        </div>
        <div style="float:right">
        <g:each var="d" in="${prices}">
        	<li class="controller">${d}</li>
        </g:each>
        </div>
        </div>
	</body>
</html>