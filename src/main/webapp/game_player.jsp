<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact List</title>
<!-- Bootstrap Core CSS -->
<link href="resources/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/lib/bootstrap/css/sb-admin.css" rel="stylesheet">
<link href="resources/lib/bootstrap/css/game.css" rel="stylesheet">
<link href="resources/lib/bootstrap/css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/lib/bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="page-wrapper">
        <p class="distance">Total Distance: <% request.getAttribute("distance"); %></p>
        <p class="distance">Current Distance: <span id="currentDistance">0</span></p>
    </div>

    <div class="diceStyle">Dice: <span id="dice">6</span></div>

    <div>
        <button class="btn btn-default rollBtn" type="button" id="normalRollButton">Normal Roll</button></div>
        <button class="btn btn-default rollBtn" type="button" id="superRollButton">Super Roll</button></div>
    </div>
</body>
<!-- jQuery -->
<script type="text/javascript"
	src="resources/lib/bootstrap/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resources/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/addContact.js"></script>
<script type="text/javascript" src="resources/js/gamePlayer.js"></script>
</html>