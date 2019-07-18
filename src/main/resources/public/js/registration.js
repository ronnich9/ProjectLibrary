angular.module("registration_form", [])
    .controller("AppCtrl", function ($scope, $http) {
    $scope.regData = {};

    $scope.sendForm = function (regData) {
        $http({
            method: 'POST',
            url: '/registration',
            data: regData,
            headers: {'Content-Type' : 'application/json'}
        })

    };

});
