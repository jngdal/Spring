angular.module('homeApp.services', []).service('popupService',['$window',function($window){
    this.showPopup=function(message){
        return $window.confirm(message); //Ask the users if they 
    }
}]);