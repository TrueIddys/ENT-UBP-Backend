angular.module('entUbp.classroom')
    .factory('classroomService', ['$http', function($http) {
        return {
            findOne: function(id) {
                return $http.get('/api/classroom/'+ id);
            },
            findAll: function() {;
                return $http.get('/api/classroom');
            },
            create: function(classroom) {
                return $http.post('/api/classroom', classroom);
            }
        };
    }]);
