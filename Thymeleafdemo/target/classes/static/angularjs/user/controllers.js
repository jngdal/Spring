
angular.module("userapp.controllers",[]).controller("UserCtrl", function($scope,popupService,$window,DetailUser,$translate) {
	
		$scope.status = {
		    opened: false
		  };
		 /* $scope.today = function() {
		    $scope.birthday = new Date();
		  };
		  $scope.today();*/	
		$scope.user=new DetailUser();			
		  $scope.updateuser = function(){	
			  $scope.user.status=true;
			  console.log($scope.user) ;
			  $scope.user.$save(function(){
				  $window.location.href = '/'
			  });
		};

		  $scope.setDate = function(year, month, day) {
		    $scope.birthday = new Date(year, month, day);
		  };

		  $scope.dateOptions = {
		    formatYear: 'yyyy',
		    startingDay: 1
		  };
		
		  $scope.format = 'dd-MM-yyyy';

		  $scope.changeLanguage = function (langKey) {
			    $translate.use(langKey);
			  };

	  
		  
		
});