var urlSearchFlight = 'http://localhost:8080/blablaplane/flights/search';
var urlPostFlight = 'http://localhost:8080/blablaplane/flights/add';
var urlPostPassenger = 'http://localhost:8080/blablaplane/user/passenger/signup';
var urlLogPassenger = 'http://localhost:8080/blablaplane/user/passenger/signin';
var urlPostPilot = 'http://localhost:8080/blablaplane/user/pilots/signup';
var urlLogPilot = 'http://localhost:8080/blablaplane/user/pilots/signin';
var profil = null;
var type = "";

function afterSearch(listF) {
	$("#resultsearch").text("");
	$("#notfound").hide();
	if (listF.length == 0) {
		$("#notfound").show();
		return;
	}
	for (i = 0; i < listF.length; i++) {
		var templateExample = _.template($('#ajoutsearch').html());
		var html = templateExample({
			"idf" : listF[i].idFlight,
			"depart" : listF[i].departureAirport,
			"arrive" : listF[i].arrivalAirport,
			"date" : listF[i].date,
			"time" : listF[i].timep,
			"mail" : listF[i].pilot.mail,
			"pilote" : listF[i].pilot.firstName,
			"modele" : listF[i].plane.modele,
			"price" : listF[i].price,
			"place" : listF[i].seatLeft
		});
		$("#resultsearch").append(html);
		// alert(listF[0].departureAirport+" "+listF[0].arrivalAirport+"
		// "+listF[0].price);
	}
}

// fonction generique pour recuperer des donnes
function getServerData(url, callBack, type, data) {
	$.ajax({
		url : url,
		type : type,
		dataType : 'json',
		contentType : 'application/json',
		success : function(data) {
			if (callBack)
				callBack(data);
		},
		data : JSON.stringify(data),
		error : function(jqXHR, textStatus, errorThrown) {
			alert("pas ok " + textStatus);
		}
	});
}

// click de bouton recherche vol
$(function() {
	$("#search").click(function() {
		var local = "";
		var travel = "";
		if ($('#travelsearch').is(':checked'))
			travel = "travel";
		if ($('#localsearch').is(':checked'))
			local = "local";
		var data = {
			typeLocal : local,
			typeTravel : travel,
			date : $("#datesearch").val(),
			departure : $("#departuresearch").val(),
			arrival : $("#arrivalsearch").val()
		};
		getServerData(urlSearchFlight, afterSearch, 'post', data);
		// alert(data.typeLocal+" "+data.typeTravel+" "+data.date+
		// " "+data.departure+" "+data.arrival);
	});
});

function afterPost(flight) {
	$("#signin").removeData('bs.modal');
	$('#postFlight').modal('hide');
}

// click de bouton planifié vol
$(function() {
	$("#postflight").click(function() {
		// construction des donne du vol
		var typeVol = "";
		if ($('#travelpost').is(':checked'))
			typeVol = "travel";
		else
			typeVol = "local";
		var data = {
			date : $("#datepost").val(),
			timep : $("#timepost").val(),
			departureAirport : $("#departurepost").val(),
			arrivalAirport : $("#arrivalpost").val(),
			travelTime : $("#traveltimepost").val(),
			price : $("#pricepost").val(),
			seatLeft : $("#placepost").val(),
			typeflight : typeVol,
			pilot : profil
		};
		getServerData(urlPostFlight, afterPost, 'post', data);
		// alert(data.typeflight+" "+data.departureAirport+"
		// "+data.arrivalAirport+" "+data.date+" "+data.timep+" "
		// +data.travelTime+" "+data.price+" "+data.seatLeft+" "+typeVol);
	});
});

// lien de recherche de vol
$(function() {
	$("#lienRecherche").click(function() {
		$("#resultsearch").text("");
	});
});

// verifier si le formulaire d'incription a bien ete rempli
function VerifFormSingup() {
	var regex = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
	valid = true;
	if ($("#firstName").val() == "" || $("#lastName").val() == ""
			|| $("#mail").val() == "" || $("#password").val() == "") {
		valid = false;
		$("#formsignup .error-form").fadeIn().text(
				"veuillez remplir tout les champs");
	} else if (!regex.test($('#mail').val())) {
		$("#formsignup .error-form").fadeIn().text("Format email incorrect");
		valid = false;
	} else {
		$("#formsignup .error-form").fadeIn().text("");
		valid = true;
	}
	return valid;
}

// creer un pilote ou un passager
$(function() {
	$("#createUser")
			.click(
					function() {
						if (VerifFormSingup()) {
							if ($('#formsignup input[name="type"]:checked')
									.val() == "pilot") {
								$("#formsignup .error-form").text("");
								getServerData(urlPostPilot, afterPostUser,
										'post', formsignupToJSON());
							} else if ($(
									'#formsignup input[name="type"]:checked')
									.val() == "passenger") {
								$("#formsignup .error-form").text("");
								getServerData(urlPostPassenger, afterPostUser,
										'post', formsignupToJSON());
							} else {
								$("#formsignup .error-form")
										.fadeIn()
										.text(
												"veuillez cocher la case pilote ou passager");
							}
						}
					});
});

function afterPostUser(user) {
	// jQuery.noConflict();
	$('#signup').modal('hide');
	$('#signin').modal('show');
	$(".signup-sucess").fadeIn().text(
			"Inscription reussie vous pouvez vous connecter");
}

// return les info du formulaire d'inscription
function formsignupToJSON() {
	// var formulaire=JSON.stringify({
	var formulaire = {
		firstName : $('#firstName').val(),
		lastName : $('#lastName').val(),
		mail : $('#mail').val(),
		password : $('#password').val()
	};
	return formulaire;
}

// verifie si le formulaire de connection a bien ete rempli
function VerifFormSingin() {
	var regex = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
	valid = true;
	if ($("#mailog").val() == "" || $('#logpass').val() == "") {
		valid = false;
		$("#formsignin .error-form").fadeIn().text(
				"veuillez remplir tout les champs");
	} else if (!regex.test($('#mailog').val())) {
		console.log("jjjjjj");
		$("#formsignin .error-form").fadeIn().text("Format email incorrect");
		valid = false;
	} else {
		$("#formsignin .error-form").fadeIn().text("");
		valid = true;
	}
	return valid;
}

// connection pilote ou passager
$(function() {
	$("#connectuser")
			.click(
					function() {
						if (VerifFormSingin()) {
							if ($('#formsignin input[name="type"]:checked')
									.val() == "pilot") {
								$("#formsignin .error-form").text("");
								type = "pilot";
								getServerData(urlLogPilot, afterLoginUser,
										'post', formlogToJSON());
							} else if ($(
									'#formsignin input[name="type"]:checked')
									.val() == "passenger") {
								$("#formsignin .error-form").text("");
								type = "passenger";
								getServerData(urlLogPassenger, afterLoginUser,
										'post', formlogToJSON());
							} else {
								$("#formsignin .error-form")
										.fadeIn()
										.text(
												"veuillez cocher la case pilote ou passager");
							}
						}
					});
});

function afterLoginUser(user) {
	if (!user) {
		$("#formsignin .error-form").fadeIn().text(
				"Votre email ou mot de passe sont incorrect");
	} else {
		$('#signin').modal('hide');
		$(".signup-sucess").text("");
		$("#signin").removeData('bs.modal');
		// ////////////////////////////////////////////////////////////////////////
		// achanger par session
		profil = user;
		if (type == "passenger") {
			$("#lienRecherche").show();
			$("#lienReservation").show();
		} else {
			$("#lienpost").show();
		}
		$("#logOut").show();
		$("#sup").hide();
		$("#sin").hide();
		// ///////////////////////////////////////////////////////////////////////
	}
}

// return les infos du formulaire
function formlogToJSON() {
	var form = {
		firstName : "",
		lastName : "",
		mail : $('#mailog').val(),
		password : $('#logpass').val()
	};
	return form;
}

// se deconnecter
$(function() {
	$("#logOut").click(function() {
		$(".connected").hide();
		$("#lienRecherche").hide();
		$("#lienReservation").hide();
		$("#logOut").hide();
		$("#sup").show();
		$("#sin").show();
		profil = null;
		window.location.href = "http://localhost:8080/";
	});
});

// reserver un vol par un passenger
function bookFlight(val, mail) {
	if (profil == null)
		$('#signin').modal('show');
	else {
		alert(profil.mail+" idflight "+val+ " mail pilot "+mail+" nombre de place "+ $('#nbPlace').val());
	}
}