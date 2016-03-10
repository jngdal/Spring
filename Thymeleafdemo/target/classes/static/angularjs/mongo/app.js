angular.module('mongoApp', ['mongoApp.controllers', 'mongoApp.services','ui.bootstrap','ui.bootstrap.tpls']);

angular.module('mongoApp').config(function($httpProvider) {
	 var csrfToken =document.getElementById("csrf").value;	
	  $httpProvider.defaults.headers.post['X-CSRF-TOKEN'] = csrfToken;
	  $httpProvider.defaults.headers.put['X-CSRF-TOKEN'] = csrfToken;
	  $httpProvider.defaults.headers.patch['X-CSRF-TOKEN'] = csrfToken;
	  $httpProvider.defaults.headers.common['X-CSRF-TOKEN'] = csrfToken;	

});