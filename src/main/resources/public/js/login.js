angular.module("login", []).controller("login", function ($scope, $http) {
    $scope.loginData = {};

    $scope.sendForm = function (loginData) {
        $http({
            method: 'POST',
            url: '/login',
            data: loginData,
            headers: {'Content-Type' : 'application/json'}
        }).then(
            (data) => {
                window.alert("Доступ разрешен");
            },
            (error) => {
                window.alert("Доступ запрещен");

            }
        );
    }
});
