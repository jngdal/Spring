angular.module('movieApp.services', []).factory('Movie', function($resource) {
  return $resource('./api/movies/:id', { id: '@_id' }, {
    update: {
      method: 'PUT'
    }
  });
}).service('popupService',['$window',function($window){
    this.showPopup=function(message){
        return $window.confirm(message); //Ask the users if they 
    }
}]);