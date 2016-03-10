/**
 * 
 */


function getTimeFromSeconds(totalSeconds) {
    if (totalSeconds < 86400) {
        var dt = new Date("01/01/2000 0:00");
        dt.setSeconds(totalSeconds);
        return formatTime(dt);
    } else {
        return null;
    }
}

function formatTime(dt) {
    var h = dt.getHours(),
        m = dt.getMinutes(),
        s = dt.getSeconds(),
        r = "";
    if (h > 0) {
        r += (h > 9 ? h.toString() : "0" + h.toString()) + ":";
    }
    r += (m > 9 ? m.toString() : "0" + m.toString()) + ":"
    r += (s > 9 ? s.toString() : "0" + s.toString());
    return r;
}







function checkUploadSpeed( iterations, update ) {
    var average = 0,
        index = 0,
        timer = window.setInterval(check, 5000 ); //check every 5 seconds
    	check();
    
    function check() {
    	
    	 $.ajax({
             xhr: function () {
                 var xhr = new window.XMLHttpRequest(),
                 startTime= new Date(),
                 speed = 0;
      
                 xhr.onreadystatechange = function ( event ) {
                     if( xhr.readyState == 4 ) {
                         speed = Math.round( 1024 / ( ( new Date() - startTime ) / 1000 ) );
                          update( speed, average );
                         
                     };
                 };
                 return xhr;
             },
             headers:  { 
             	"X-CSRF-TOKEN":
             		$("meta[name='_csrf']").attr("value")
             },
             enctype:'multipart/form-data',
             url: 'speed?cache=' + Math.floor( Math.random() * 10000 ),
             type: "POST",
             data:getRandomString( 1 ) ,
             contentType: false,
             processData: false,
             success: function (result) {
             	console.log(result);
             },
             error: function (result) {
             	console.log(result);
             }
         });   	
    	      
        
    };
    
    function getRandomString( sizeInMb ) {
        var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+`-=[]\{}|;':,./<>?", //random data prevents gzip effect
            iterations = sizeInMb * 1024 * 1024, //get byte count
            result = '';
        for( var index = 0; index < iterations; index++ ) {
            result += chars.charAt( Math.floor( Math.random() * chars.length ) );
        };     
        return result;
    };
};

