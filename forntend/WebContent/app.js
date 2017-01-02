var app=angular.module('simpleApp',['ngRoute']);
app.config(function($routeProvider){
	alert('inside config')
	$routeProvider
	.when('/home',{
		controller:'EmployeeController',
	    templateUrl:'home.html'
	})
	.when('/user',{
		controller:'EmployeeController',
	    templateUrl:'user.html'
	})
	.when('/blog',{
		controller:'EmployeeController',
		templateUrl:"blog.html"
	})
	.when('/forum',{
		controller:'EmployeeControlletr',
		templateUrl:"forum.html"
	})
	.otherwise({redirectTo:'/home'})
})
