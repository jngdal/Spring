angular.module('userapp.services', []).factory('DetailUser', function($resource) {
  return $resource('/user/');
}).service('popupService',['$window',function($window){
    this.showPopup=function(message){
        return $window.confirm(message); //Ask the users if they 
    }
}]);