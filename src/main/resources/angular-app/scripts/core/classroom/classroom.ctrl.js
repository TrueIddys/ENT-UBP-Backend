angular.module('entUbp.classroom')
    .controller('ClassroomController', ['$scope', 'classroomService',
        function($scope, classroomService) {
            $scope.classrooms = [];

            classroomService.create({'name': 'SL78565', 'roomCapacity': { 'maxCapacity': 20}, 'equipments': []});

            classroomService.findAll().then(
                function(response) {
                    $scope.classrooms = response.data;
                },
                function(response) {
                    console.error(response);
                }
            );
        }
    ]);
