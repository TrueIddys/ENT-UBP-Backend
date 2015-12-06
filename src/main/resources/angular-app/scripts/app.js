angular.module('entUbp', [
    'ngRoute',
    'entUbp.sidebar',
    'entUbp.classroom'
]).config(['$routeProvider',
    function($routeProvider) {
        $routeProvider
            .when('/', {
                title: 'Acceuil',
                templateUrl: 'views/home.html',
                controller: ''
            })
            .when('/classroom', {
                title: 'Salles de cours',
                templateUrl: 'views/classroom/classroom.html',
                controller: 'ClassroomController'
            })
            .when('/equipment-type', {
                title: 'Types d\'équipements',
                templateUrl: 'views/equipment-type/equipment-type.html',
                controller: ''
            })
            .otherwise({
                redirectTo: '/'
            });
    }])
    .run(['$rootScope', function($rootScope) {
        $rootScope.$on('$routeChangeSuccess', function(event, current, previous) {
            $rootScope.title = current.$$route.title;
        });
    }]);

