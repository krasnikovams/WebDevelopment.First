<!doctype html>
<html ng-app="myApp">
<head>
<script src="../js/angular.min.js"></script>
<script src="../js/main.js"></script>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
    <div ng-controller="myCtrl">
    <p>Words to search: </p>
        <form ng-submit="sendPost()">
            <input ng-model="words" />
            <button type="submit">Search</button>
        </form>
        <p>Search results: {{message}}
    </div>
</body>
</html>