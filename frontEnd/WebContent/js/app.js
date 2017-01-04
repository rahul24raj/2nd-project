/*Mapping controller and html pages*/

var app=angular.module("myApp",['ngRoute'])
app.config(function($routeProvider){
	console.log('entering configuration')
	$routeProvider
	.when('/login',{
		controller:'usercontroller',
		templateUrl:'user/login.html'
	})
	.when('/register',{
		controller:'usercontroller',
		templateUrl:'user/register.html'
	})
	.when('/home',{
		templateUrl:'home/home.html'
	})
	.when('/listOfUsers',{
		controller:'usercontroller',
		templateUrl:'user/listOfUsers.html'
	})
	.when('/postJob',{
		controller:'jobcontroller',
		templateUrl:'_job/createJob.html'
	})
	.when('/getAllJobs',{
		controller:'jobcontroller',
		templateUrl:'_job/jobTitles.html'
	})
	.when('/uploadPicture',{
		templateUrl:'_user/uploadPicture.html'
    })
})