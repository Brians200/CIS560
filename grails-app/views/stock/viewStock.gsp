<html>  
<head>
	<meta name="layout" content="main" />
    <!--Load the AJAX API-->    
<script type="text/javascript" src="https://www.google.com/jsapi"></script>    
<script type="text/javascript">          
// Load the Visualization API and the piechart package.      
google.load('visualization', '1.0', {'packages':['corechart']});            
// Set a callback to run when the Google Visualization API is loaded.      
google.setOnLoadCallback(drawPriceChart);   
google.setOnLoadCallback(drawVolumeChart);  
// Callback that creates and populates a data table,       
// instantiates the pie chart, passes in the data and      
// draws it.      
function drawPriceChart() {      
// Create the data table.
var data = new google.visualization.DataTable();      
data.addColumn('string', 'Date');      
data.addColumn('number', 'Price');    
data.addRows(${Tablep});      
// Set chart options     

 var options = {'title':'Price ${PChange}','width':768,'height':300,scaleType:'allmaximized' ,series: {0:{color: 'lime',type:"area", visibleInLegend: false}}};      
// Instantiate and draw our chart, passing in some options.      
var chart = new google.visualization.AreaChart(document.getElementById('price_chart_div'));      
chart.draw(data, options);    
}  

function drawVolumeChart() {      
	// Create the data table.
	var data = new google.visualization.DataTable();      
	data.addColumn('string', 'Date');      
	data.addColumn('number', 'Volume');    
	data.addRows(${Tablev});
	// Set chart options     

    var options = {'title':'Volume Avg:${AverageVolume}','width':768,'height':300,scaleType:'allmaximized' ,series: {0:{color: '#512888',type:"bars", visibleInLegend: false}}};      
	// Instantiate and draw our chart, passing in some options.      
	var chart = new google.visualization.AreaChart(document.getElementById('volume_chart_div'));      
	chart.draw(data, options);    
	}  
</script>  
</head>  
<body>    
<div align="center">
<br/>
	<h2>
	${Exchange}:${Symbol}<br />${company}</h2>
	<p>Industry: ${industry } - Sector: ${sector }</p>
	<p>Current Price: $${currentPrice }</p>
	<p>Percent Change: ${percentChange}</p>
	<g:form controller="stock">
		<div>
			<br/>Show Dates Between<br/>
			<span><g:datePicker name="startDate" value="${datePickerStart}" precision="day" years="${2011}"/></span>
			<span><g:datePicker name="finishDate" value="${datePickerFinish}" precision="day" years="${2011}"/></span>
			<span><g:actionSubmit value="Update" action="updateChartDates"/></span>
		</div>
	</g:form>
	<div class="graphs" id="price_chart_div"></div> 
	<div class="graphs" id="volume_chart_div"></div>
			<h2>Historical Data:</h2>
			<br />
		<% 
  			 def Columns = [['string', 'Date'], ['number', 'Adjusted Close'],['number', 'Volume'],['number', 'High'],['number', 'Low'],['number', 'End of Day']]
		%>
		<gvisualization:table elementId="table_div" width="${768}" allowHtml="${true}" 
			showRowNumber="${false}" columns="${Columns}"
			data="${Tablet}" />
			<div id="table_div"></div>
</div>   


</body>
</html>