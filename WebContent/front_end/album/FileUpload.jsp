<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>


<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="<%=request.getContextPath() %>/front_end/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/front_end/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="<%=request.getContextPath() %>/front_end/js/fileinput.min.js"></script>


</head>
<body>

   <div class="container kv-main" style=" width: 830px;height: 400px;margin-top: 200px;">  
  
  
        <form enctype="multipart/form-data">  
            <input id="file-1" class="file" type="file" multiple  
                data-min-file-count="1"> <br>  
        </form>  
  
  
        <hr>  
  
  
        <hr>  
        <br>  
    </div>




</body>



<script>  
      
    $("#file-1").fileinput({   
        language: 'zh',  
        uploadUrl: '<%=request.getContextPath()%>/FileUpload', 
        allowedFileExtensions : ['xls','jpg', 'png','gif'],  
        maxFileCount: 3, 
       
    slugCallback: function(filename) {  
            return filename.replace('(', '_').replace(']', '_');  
        }  
    });   
         
     $("#file-1").on("fileuploaded", function (event, data, previewId, index) {  
         top.location.href="<%=request.getContextPath()%>/front_end/album/FileUpload.jsp";  
     });  
</script>  



</html>