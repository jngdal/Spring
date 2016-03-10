/**
 * 
 */
function CallAjax(obj,id) {
				
        $.ajax({
            xhr: function () {
                var xhr = new window.XMLHttpRequest();
                xhr.upload.addEventListener("load", function (evt) {
                    if (evt.lengthComputable) {
                        var percentComplete = evt.loaded / evt.total;
                        percentComplete = parseInt(percentComplete * 100);
                       // percent.css(percentComplete);
                        console.log(percentComplete);
                        $('div#'+id+'').css('width',  percentComplete+'%').attr('aria-valuenow',  percentComplete); 
                        
                    }
                }, true);
                return xhr;
            },
            headers:  { 
            	"X-CSRF-TOKEN":
            		$("meta[name='_csrf']").attr("value")
            },
            enctype:'multipart/form-data',
            url: "/upload",
            type: "POST",
            data: obj,
            contentType: false,
            processData: false,
            success: function (result) {
            	console.log(result);
            	if(!result){
            		$('.progress-bar').css('width',  0+'%').attr('aria-valuenow',  0); 
            		$('div#dvPreview img:last-child').remove();
            	}
            },
            error: function (result) {
            	console.log(result);
               alert("File not uploaded (perhaps it's too much big)");
                }
        });

    };
	

