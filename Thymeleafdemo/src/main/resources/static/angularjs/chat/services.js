
angular.module("chatApp.services",[]).service("ChatService",function($q, $timeout) {
   
    var service = {},  listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [],crsftoken;
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/chat";
    service.CHAT_TOPIC = "/topic/message"; 
    service.CHAT_BROKER = "/app/chat";
    
    service.setToken=function(token){
    	crsftoken=token;
    	initialize();
    };  
       	
       
    service.receive = function() {
      return listener.promise;
    };
    
    service.send = function(message) {
      var id = Math.floor(Math.random() * 1000000);
      socket.stomp.send(service.CHAT_BROKER, {
        priority: 9
      }, JSON.stringify({
        content: message,
        id: id
      }));
      messageIds.push(id);
    };
    
    var reconnect = function() {
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var getMessage = function(data) {
      var message = JSON.parse(data), out = {};
      out.message = message.content;
      out.time = new Date(message.date);
      out.user=message.user;
      if (messageIds.indexOf(message.id)!=-1) {
        out.self = true;
        messageIds = messageIds.splice(message.id);
      }
      return out;
    };
    
   
    
    
    var startListener = function() {
      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
    	  console.log(data);
        listener.notify(getMessage(data.body));
      });
     
    };
    
    var initialize = function() {
      var headers = {};
      headers["X-CSRF-TOKEN"] = crsftoken;
      socket.client = new SockJS(service.SOCKET_URL);
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect(headers, startListener);
      socket.stomp.onclose = reconnect;
    };
    
    
    return service;
  }).service("NotifyService", function($q, $timeout) {
	  var service = {},  notifylistener = $q.defer(), socket = {
	      client: null,
	      stomp: null
	    };
	  
	  service.RECONNECT_TIMEOUT = 30000;
	    service.SOCKET_URL = "/notify";	    
	    service.NOTIFY_TOPIC = "/topic/notify"; 
	   
	    service.setToken=function(token){
	    	crsftoken=token;
	    	initialize();
	    };   
	    
	  service.notify = function(){
	    	return notifylistener.promise;
	    };
	    /*var getUser = function(data) {
	        var user = JSON.parse(data);
	         return user.username;      
	        
	      };*/
	    var startListener = function() {	        
	        socket.stomp.subscribe(service.NOTIFY_TOPIC, function(data) {
	      	  console.log(data);
	      	  notifylistener.notify(data.body);
	      	 
	          });
	      };
	      
	      var reconnect = function() {
	          $timeout(function() {
	            initialize();
	          }, this.RECONNECT_TIMEOUT);
	        };  
	        var initialize = function() {
	        	var headers = {};
	            headers["X-CSRF-TOKEN"] = crsftoken;
	            socket.client = new SockJS(service.SOCKET_URL);
	            socket.stomp = Stomp.over(socket.client);
	            socket.stomp.connect(headers, startListener);
	            socket.stomp.onclose = reconnect;
	          };
	         
	    return service;
  }).factory("DetailUser", function($resource) {
  	return $resource("/api/users");
  });