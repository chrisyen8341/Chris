<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	$(function() {

		$("#autoAddPet").click(function() {
			$("#petName").val("單身狗");
		});

	});
</script>



<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
			<![endif]-->
</head>
<body>
	<div class="container">
		<div class="row main">

			<div class="col-sm-6 col-sm-offset-3">

				<form class="" action="Register" method="post"
					enctype="multipart/form-data">



					<div id="petDiv">

						<div align="center">
							<Img src="images/logo.png" height="250px" width="100%" />
						</div>


						<div class="form-group">
							<label for="petName" class="cols-sm-2 control-label">寵物姓名</label><span
								id="petNameShow"></span>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
										type="text" class="form-control" name="petName" id="petName"
										placeholder="請輸入您的寵物姓名" />
								</div>
							</div>
						</div>


						<div class="row">
							<div class="col-sm-6 form-group">
								<label for="petKind" class="control-label">寵物分類</label><br>
								<label class="radio-inline"><input type="radio"
									name="petKind" id="petOrNot_0" value="狗" checked="true">狗</label>
								<label class="radio-inline"><input type="radio"
									name="petKind" id="petOrNot_1" value="貓">貓</label> <label
									class="radio-inline"><input type="radio" name="petKind"
									id="petOrNot_2" value="其他">其他</label>
							</div>


							<div class="col-sm-6 form-group">
								<label for="petGender" class="control-label">寵物性別</label><br>
								<label class="radio-inline"><input type="radio"
									name="petGender" value="0" checked="true">男</label> <label
									class="radio-inline"><input type="radio"
									name="petGender" value="1">女</label>
							</div>

						</div>


						<div class="form-group">
							<label for="petImg" class="control-label">寵物照片</label><span
								id="petImgShow"></span><br> <input type="file"
								name="petImg" id="petImg" /> <img
								src="https://api.fnkr.net/testimg/350x200/00CED1/FFF/?text=img+placeholder"
								height="200px" width="150px" id="petPic" style="margin-top: 5px"><br>

						</div>

					</div>


					<input class="btn btn-primary btn-lg btn-block login-button"
						type="submit" value="註冊">

				</form>

				<button type="button" class="btn btn-info" id="autoAddPet"
					style="margin-top: 10px">新增寵物</button>

			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>