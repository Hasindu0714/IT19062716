<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="sponsorModel.Sponsor" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
	<script src="Components/sponsor.js"></script>
</head>
<body>

	<div class="container"><div class="row"><div class="col-6"> 
	
	<h1>Sponsor Management</h1>
	<form id="formItem" name="formItem">
 		 Sponsor name: 
 		 <input id="sponsor_name" name="sponsor_name" type="text" 
 				class="form-control form-control-sm ">
 				
 		 <br> Sponsor type: 
		 <input id="sponsor_type" name="sponsor_type" type="text" 
		 		class="form-control form-control-sm">
		 		
		 <br> Email: 
		 <input id="email" name="email" type="text" 
		 		class="form-control form-control-sm">
		 		
		 <br> Phone: 
		 <input id="phone" name="phone" type="text" 
		 		class="form-control form-control-sm">
		 		
		 <br> Funding amount: 
		 <input id="funding_amount" name="funding_amount" type="text" 
		 		class="form-control form-control-sm">
		 		
		 <br>
		 <input id="btnSave" name="btnSave" type="button" value="Save" 
		 		class="btn btn-primary">
		 <input type="hidden" id="hidItemIDSave" 
		 		name="hidItemIDSave" value="">
	</form>
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemsGrid">
		 <%
			 Sponsor sponObj = new Sponsor(); 
			 out.print(sponObj.getSponsors()); 
		 %>
	</div>
</div> </div> </div>

</body>
</html>