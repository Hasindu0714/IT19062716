$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
	{ 
		$("#alertSuccess").hide(); 
	} 
	$("#alertError").hide(); 
}); 

//SAVE
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide();
 
	// Form validation-------------------
	var status = validateSponsorForm(); 
	if (status != true) 
	{ 
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
 	} 

	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
	 url : "SponsorsAPI", 
	 type : type, 
	 data : $("#formItem").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 	onSponsorSaveComplete(response.responseText, status); 
	 } 
 }); 
});

function onSponsorSaveComplete(response, status)
{ 
	if (status == "success") 
	{ 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
		 $("#alertSuccess").text("Successfully saved."); 
		 $("#alertSuccess").show(); 
		 $("#divItemsGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
		 } 
		 } else if (status == "error") 
		 { 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
		 } else
		 { 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
		 } 
		 $("#hidItemIDSave").val(""); 
		 $("#formItem")[0].reset(); 
	}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{ 
	 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val()); 
	 $("#sponsor_name").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#sponsor_type").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#email").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#phone").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#funding_amount").val($(this).closest("tr").find('td:eq(4)').text()); 
});

// CLIENT-MODEL================================================================
function validateSponsorForm() 
{ 
	// SPONSOR NAME
	if ($("#sponsor_name").val().trim() == "") 
	{ 
	 	return "Insert sponsor name."; 
	} 
	// SPONSOR TYPE
	if ($("#sponsor_type").val().trim() == "") 
	{ 
	 	return "Insert sponsor type."; 
	}
	// EMAIL-------------------------------
	if ($("#email").val().trim() == "") 
	{ 
	 	return "Insert email."; 
	} 
	// PHONE------------------------
	if ($("#phone").val().trim() == "") 
	{ 
	 	return "Insert phone."; 
	} 
	// FUNDING AMOUNT------------------------
	if ($("#funding_amount").val().trim() == "") 
	{ 
	 	return "Insert funding_amount."; 
	} 
	return true;
}

$(document).on("click", ".btnRemove", function(event)
{ 
	 $.ajax( 
	 { 
		 url : "SponsorsAPI", 
		 type : "DELETE", 
		 data : "sponsorID=" + $(this).data("sponsorid"),
		 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 	onSponsorDeleteComplete(response.responseText, status); 
	 } 
 }); 
});

function onSponsorDeleteComplete(response, status)
{ 
if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show(); 
	 $("#divItemsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
	 } 
}