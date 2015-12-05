angular.module('entUbp', [
  'ngRoute',
  'entUbp.classroom'
]).config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
        .when('/', {
          templateUrl: 'views/home.html',
          controller: ''
        })
        .when('/classroom', {
          templateUrl: 'views/classroom/classroom.html',
          controller: 'ClassroomController'
        })
        .when('/equipment-type', {
          templateUrl: 'views/equipment-type/equipment-type.html',
          controller: ''
        })
        .otherwise({
          redirectTo: '/'
        });
  }]);
