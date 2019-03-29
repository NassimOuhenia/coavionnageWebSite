var dataf;
var RedirictURL = "http://localhost:8080/profilPage/";

function Postuser(url, success){
	
	console.log(formToJSON());
    $.ajax({
	 type: 'POST',
contentType: 'application/json',
        dataType: "json",
data:formToJSON(),
        url: url,
       success: function(data){ console.log('findB success: ' + data.firstName);

},
       error: function(jqXHR, textStatus, errorThrown){
			alert('add pilot error' + textStatus);
}
    });
}

function callDone(result){
	
	
	
}



function loguser(url, success){
	
	console.log(formlogToJSON());
    $.ajax({
	 type: 'POST',
contentType: 'application/json',
        dataType: "json",
data:formlogToJSON(),
        url: url,
       success: function(data){ 
	
	if(!data){
		
	var templateExample = _.template($('#templateExample').html());
message="Votre mail et mot de passe sont incorrect";
	var html = templateExample({
		"attribute":message
	});

	$(".msEror").after(html);
	console.log("kkkkkk");	
		
	}else{
		
		console.log(data.firstName);	
		
	}

	
	
	

},
       error: function(jqXHR, textStatus, errorThrown){
	
			alert('loginpilot error' + textStatus);
}
    });
}

function callDone(result){
	
	
	
}


$(function(){
	$("#createUser").click(function(){
		
		if(document.querySelector('input[name="type"]:checked').value=="pilot"){
			
		
		Postuser("blablaplane/user/pilots/signup/",callDone);
		}else{
			
		Postuser("blablaplane/user/passenger/signup",callDone);
		}
	});
});

$(function(){
	$("#connectuser").click(function(){
		
		if(document.querySelector('input[name="type"]:checked').value=="pilot"){
		
		loguser("blablaplane/user/pilots/signin/",callDone);
		}else if(document.querySelector('input[name="type"]:checked').value=="passenger"){
			
		loguser("blablaplane/user/passenger/signin",callDone);
		}else{
			
		}
	});
});







function formlogToJSON() {
	
	
	var form=JSON.stringify({firstName:"", lastName:"",mail:$('#mailog').val(),
	password:$('#logpass').val()});
	
	return form;
}

function formToJSON() {
	
	var formulaire=JSON.stringify({firstName:$('#firstName').val(),lastName:$('#lastName').val(),mail:$('#mail').val(),
	password:$('#password').val()});
	
	return formulaire;
}