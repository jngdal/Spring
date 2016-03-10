angular.module("userapp", [
  'userapp.controllers','ngSanitize','pascalprecht.translate','ngResource','userapp.services','ui.bootstrap','ui.bootstrap.tpls','ngMessages'
]);

angular.module("userapp").config(function($httpProvider,$translateProvider) {
	 var csrfToken =document.getElementById("csrf").value;	
	  $httpProvider.defaults.headers.post['X-CSRF-TOKEN'] = csrfToken;	 
	  $translateProvider.useUrlLoader("/user/language"); 
	  $translateProvider.useSanitizeValueStrategy('escaped');
	  $translateProvider.preferredLanguage('vi');
	 
})