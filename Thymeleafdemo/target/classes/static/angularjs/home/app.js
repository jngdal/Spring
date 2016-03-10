angular.module('homeApp', ['homeApp.controllers','homeApp.services','ui.bootstrap','ui.bootstrap.tpls','ngResource']);

angular.module("homeApp").config(function($httpProvider) {
	 var csrfToken =document.getElementById("csrf").value;	
	  $httpProvider.defaults.headers.post['X-CSRF-TOKEN'] = csrfToken; 
		 
})