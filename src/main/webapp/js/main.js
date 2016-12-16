var helloAjaxApp = angular.module("myApp", []);

helloAjaxApp.controller("myCtrl", [ '$scope', '$http', function($scope, $http) {

    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";

    $scope.$watch("words",function(newValue, oldValue){
        $http({
                url : 'SearchJsServlet',
                method : "POST",
                data : {
                    'words' : newValue
                }
        }).then(function(response) {
            console.log(response.data);
            $scope.message = response.data;
        }, function(response) {
            //fail case
            console.log(response);
            $scope.message = "NOTHING FOUND";
        });
        $http({
                url : 'ShowJsServlet',
                method : "POST",
                data : {
                    'words' : newValue
                }
        }).then(function(response) {
            console.log(response.data);
            $scope.content = response.data;
        }, function(response) {
            //fail case
            console.log(response);
            $scope.content = "NOTHING FOUND";
        });
    });
} ]);