<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Testing</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	var app = angular.module('myApp', []);
	app.controller('MyController', [ '$scope', '$http',
			function($scope, $http) {
				$scope.getDataFromServer = function() {
					var a = $scope.searchText;
					$http({
						method : 'GET',
						url : 'javaAngularJS?a='+a
					}).success(function(data, status, headers, config) {
						$scope.display = data;
						drawChart();
					}).error(function(data, status, headers, config) {
					});

				};
			} ]);
</script>

<script type="text/javascript">
	function drawChart() {
		alert("here");
		var data = google.visualization.arrayToDataTable([
				[ 'Task', 'Hours per Day' ], [ 'Positive', 2 ],
				[ 'Negative', 2 ], [ 'Neutral', 2 ]

		]);

		var options = {
			title : 'REPUTATION GRAPH'
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options);
	}
</script>

</head>
<body>
	<div ng-app="myApp">
		<div ng-controller="MyController">
			<input type="text" ng-model="searchText">
			<button ng-click="getDataFromServer()">Fetch data from
				server</button>
			<br> <br> Total Tweets : {{display.positiveTweets.length +
			display.negativeTweets.length + display.neutralTweets.length}}<br>
			<br>
			Positive Tweets
			<ul>
				<li ng-repeat="positiveTweets in display.positiveTweets"><a href="#">{{positiveTweets}}</a></li>
			</ul>
			<br><br>
			
			Negative Tweets 
			<ul>
				<li ng-repeat="negativeTweets in display.negativeTweets"><a href="#">{{negativeTweets}}</a></li>
			</ul>
			<br><br>
			
			Neutral Tweets 
			<ul>
				<li ng-repeat="neutralTweets in display.neutralTweets"><a href="#">{{neutralTweets}}</a></li>
			</ul>
			
			<br> <br>Plot
			Graph: <br> <br>Positive {{display.positiveTweets.length}}
			, Negative {{display.negativeTweets.length}}, Neutral
			{{display.neutralTweets.length}}
		</div>
		<table border="1" width="100%">
			<tr>
				<td width="50%"><div class="col-md-6" id="piechart"
						style="width: 600px; height: 300px;"></div></td>
			</tr>
		</table>
	</div>
</body>
</html>