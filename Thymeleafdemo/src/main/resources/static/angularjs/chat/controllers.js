
angular.module("chatApp.controllers",[]).controller("ChatCtrl", function($scope, ChatService,NotifyService,DetailUser) {
  $scope.messages = [];
  $scope.message = "";
  $scope.max = 140;
  $scope.users=DetailUser.query();
  $scope.isCollapsed = true;
  ChatService.setToken(document.getElementById('token').value);
  $scope.addMessage = function() {
    ChatService.send($scope.message);
    $scope.message = "";
  };
  NotifyService.setToken(document.getElementById('token').value);
 
  
  NotifyService.notify().then(null,null,function(username){
	  console.log(username);
	  $scope.users=[];
	  $scope.users=DetailUser.query();
  });
  ChatService.receive().then(null, null, function(message) {
    $scope.messages.push(message);
  });
  
 
  
});