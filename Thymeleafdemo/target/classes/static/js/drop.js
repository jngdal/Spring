/**
 * 
 */
$(function() {
	var obj=$('.drop');
	obj.on('dragover',function(e){
		e.stopPropagation();
		e.preventDefault();
		$(this).css("border","2px solid #16a085")
	})
	
	obj.on("drop",function(e){
		e.stopPropagation();
		e.preventDefault();
		$(this).css("border","2px solid #bdc3c7")
		var files=e.originalEvent.dataTransfer.files;
		var dvPreview = $("#dvPreview");
        var dvMultiprogressbar=$("div#multiprogressbar");
		if(files.length>7){
			
		}else{
		$.each(files,function(key,value){
			var reader = new FileReader();
			
	        reader.onload = function (e) {
	            var img = $("<img />");
	            img.attr("style", "height:100px;width: 100px");
	            img.attr("src", e.target.result);
	            dvPreview.append(img);
	            var html='<div class="progress progress-striped active"><div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" id="'+key+'"></div> </div>'
	           	
	            dvMultiprogressbar.append(html); 
	            var oMyForm = new FormData();
	     	 	 oMyForm.append("fileupload", files[key]); 
	     	 	
	     	 	CallAjax(oMyForm,key); 

	     	 	         
	            
	        }
	        reader.readAsDataURL(files[key]);
		});
		}
		
		
	})
});
	
	
