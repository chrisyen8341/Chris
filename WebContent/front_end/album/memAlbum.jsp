<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">

<head>

<%@ include file="memHead.file"%>
<style>
</style>


</head>

<body>
	<%@ include file="memNavBar.file"%>
	<div class="container-fluid">
		<div class="row">

			<div class="col-xs-12 col-sm-2 postion-left-group ">
				<%@ include file="memZoneLSide.file"%>
			</div>

			<div class="col-xs-12 col-sm-8 ">
				<div class="row">


					<h5 class="page-header text-right">目前位置:會員專區</h5>

					<div class="row">

						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">${member.memId}</h3>
							</div>
							<div class="panel-body">

   

   
 											<!-- 下方會 呼叫modal-->
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#addAlbum">新增相簿</button>
  
  								
								
							</div>

						</div>
					</div>
				</div>






		<!-- Disable MODAL -->
		<div class="modal fade" id="addAlbum" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document" >
				<div class="modal-content">
        <form enctype="multipart/form-data">  
            <input id="file-1" class="file" type="file" multiple  
                data-min-file-count="1"> <br>  
        </form>  
				</div>
			</div>
		</div>









				<%@ include file="memButtom.file"%>


			</div>
		</div>


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



		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>