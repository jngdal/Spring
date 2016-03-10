angular.module('mongoApp.controllers', []).controller('MongoController', function($scope,$http,$uibModal) {
	$scope.toggle=true;
	 $scope.completedList=[];
	$scope.priorities=['HIGH','MEDIUM','LOW'];
	$scope.statuses=['ACTIVE','COMPLETED'];
	function findAllTasks() {
		$http.get("/tasks/search/findByTaskArchived?archivedfalse=0").success(function(data) {
			if (data._embedded != undefined) 
			{
				 $scope.tasks = data._embedded.tasks;
			} else {
				                    $scope.tasks = [];
				                }
			$scope.completedList=[];
			 for (var i = 0; i < $scope.tasks.length; i++) {
				   if ($scope.tasks[i].taskStatus == 'COMPLETED') {					
				         $scope.completedList.push($scope.tasks[i]._links.self.href);
				         }
				        }
			
			
				     $scope.taskName="";
				       $scope.taskDescription="";
				         $scope.taskPriority="";
				           $scope.taskStatus="";
				           
		});
			
	};
	
	findAllTasks();

	$scope.addCompletedList= function(taskUri){
		
		var idx= $scope.completedList.indexOf(taskUri);
		console.log(idx);
		if (idx>-1) {
			$http.patch(taskUri,{taskStatus: "ACTIVE"}).success(function(data) {
				findAllTasks();
			});
		} else {
			console.log(taskUri);
			$http.patch(taskUri,{taskStatus: "COMPLETED"}).success(function(data) {
				findAllTasks();
			});
		}
	}
	
	
	$scope.addNewTask = function() {
		var task={taskName: $scope.taskName,taskDescription:$scope.taskDescription,taskPriority:$scope.taskPriority,taskStatus:$scope.taskStatus};
		console.log(task);
		$http.post("/tasks", task).success(function(data,status,headers) {
			console.log(headers()["location"]);
			findAllTasks();
		});
	}
	
	
	
	
	//modal with angular
	
	 

	

	  $scope.open = function (size) {

	    var modalInstance = $uibModal.open({
	      animation: false,
	      backdrop:'static', 
	      keyboard :false,
	      templateUrl: 'myModalContent.html',
	      controller: 'ModalInstanceCtrl',
	      size: size,
	      resolve: {
	    	  completedList: function () {
	          return $scope.completedList;
	        }
	      }
	    });
	    modalInstance.result.then(function(resultList) {	    	
	    	 resultList.forEach(function(taskUri) {
	    		 $http.patch(taskUri,{taskArchived:1}).success(function(data) {
	    			 findAllTasks();					
					});
			});
	    
	    	
		});
	
	  }
	 
	  
	  
}).controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, completedList,$http) {

	$scope.items=[];
	for (var i = 0; i < completedList.length; i++) {
		
		 $http.get(completedList[i]).success(function(data, status, headers, config) {
			$scope.items.push(data);
		});			 	        
		 }
	  
	  
	  	  

	  $scope.ok = function () {
		  console.log(completedList);
	    $uibModalInstance.close(completedList);
	  };

	  $scope.cancel = function () {
	    $uibModalInstance.dismiss('cancel');
	  };
	});



