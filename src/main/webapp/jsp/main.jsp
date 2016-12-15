<!doctype html>
<html ng-app="myApp">
<head>
<script src="../js/angular.min.js"></script>
<script src="../js/main.js"></script>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
    <div ng-controller="myCtrl">
        <form ng-submit="sendPost()">
            <input ng-model="name" />
            <button type="submit">Send</button>
        </form>
        <p>Message from servlet: {{message}}
    </div>
</body>
</html>