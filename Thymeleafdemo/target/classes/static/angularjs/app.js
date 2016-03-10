angular.module('movieApp', ['ui.router', 'ngResource', 'movieApp.controllers', 'movieApp.services']);

angular.module('movieApp').config(function($stateProvider,$httpProvider) {
	 csrfToken = $("meta[name='_csrf']").attr("value"); 
	  $httpProvider.defaults.headers.post['X-CSRF-TOKEN'] = csrfToken;
	  $httpProvider.defaults.headers.put['X-CSRF-TOKEN'] = csrfToken;
	  $httpProvider.defaults.headers.patch['X-CSRF-TOKEN'] = csrfToken;
	  $httpProvider.defaults.headers.common['X-CSRF-TOKEN'] = csrfToken;
	
  $stateProvider.state('movies', { // state for showing all movies
    url: '/movies',
    templateUrl: '/movies',
    controller: 'MovieListController'
  }).state('viewMovie', { //state for showing single movie
    url: '/movies/:id/view',
    templateUrl: '/movieview',
    controller: 'MovieViewController'
  }).state('newMovie', { //state for adding a new movie
    url: '/movies/new',
    templateUrl: '/movieadd',
    controller: 'MovieCreateController'
  }).state('editMovie', { //state for updating a movie
    url: '/movies/:id/edit',
    templateUrl: '/movieedit',
    controller: 'MovieEditController'
  });
}).run(function($state) {
  $state.go('movies'); //make a transition to movies state when app starts
});