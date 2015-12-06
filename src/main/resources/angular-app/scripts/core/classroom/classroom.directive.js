angular.module('entUbp.classroom')
    .directive('list', [
      function() {
        return {
          restrict: 'AE',
          replace: 'true',
          templateUrl: '/views/scripts/core/classroom/classroom-list.html',
          controller: function($scope) {

          }
        };
      }]);
