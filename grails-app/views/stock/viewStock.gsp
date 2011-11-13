<html>  
<head>
	<meta name="layout" content="main" />
    <!--Load the AJAX API-->    
<script type="text/javascript" src="https://www.google.com/jsapi"></script>    
<script type="text/javascript">          
// Load the Visualization API and the piechart package.      
google.load('visualization', '1.0', {'packages':['corechart']});            
// Set a callback to run when the Google Visualization API is loaded.      
google.setOnLoadCallback(drawChart);            
// Callback that creates and populates a data table,       
// instantiates the pie chart, passes in the data and      
// draws it.      
function drawChart() {      
// Create the data table.
var data = new google.visualization.DataTable();      
data.addColumn('string', 'Date');      
data.addColumn('number', 'Price');    
data.addRows(${Tablep});      
// Set chart options     

 var options = {'title':'${Exchange}:${Symbol}','width':1024,'height':300,scaleType:'allmaximized' ,series: {0:{color: 'lime',type:"area", visibleInLegend: false}}};      
// Instantiate and draw our chart, passing in some options.      
var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));      
chart.draw(data, options);    
}  
</script>  
</head>  
<body>    
<!--Div that will hold the Price chart-->  
<div align="center">
<div id="chart_div"></div> 
</div>   


</body>
</html>