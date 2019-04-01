var urlSearchFlight='http://localhost:8080/blablaplane/flights/search';
var urlPostFlight='http://localhost:8080/blablaplane/flights/add';

function afterSearch(listF) {
	$("#resultr").text("");
	$("#notfound").hide();
	console.log(listF.length);
	if(listF.length == 0) {
		$("#notfound").show();
		return;
	}
	for(i=0;i<listF.length;i++){
		var templateExample = _.template($('#ajoutr').html());
		var html = templateExample({
			"depart":listF[i].departureAirport,
			"arrive":listF[i].arrivalAirport,
			"date":listF[i].date,
			"type":listF[i].typeflight
		});
		$("#resultr").append(html);
		//console.log(listF[i].departureAirport+" "+listF[i].arrivalAirport+" "+listF[i].price+" "+listF[i].travelTime);
	}
}

//fonction generique pour recuperer des donnes
function getServerData(url, callBack, type, data){
    $.ajax({
		url: url,
		type: type,
		dataType: 'json',
		contentType: 'application/json',
		success: function (data) {
			if(callBack)
				callBack(data);
		},
		data: JSON.stringify(data),
		error : function(jqXHR, textStatus, errorThrown) {
			alert("pas ok");
		}
	});
}

//click de bouton recherche vol
$(function(){
	$("#rFlight").click(function(){
		//construction des donne de recherche
		var data={typeLocal:$("#localr").val(),
			typeTravel:$("#travelr").val(),
			date:$("#dater").val(),
			departure:$("#departurer").val(),
			arrival:$("#arrivalr").val()
		};
		getServerData(urlSearchFlight, afterSearch, 'post', data);
	});
});

$(function(){
	$("#rFlight").click(function(){
		//construction des donne de recherche
		var data={typeLocal:$("#localr").val(),
			typeTravel:$("#travelr").val(),
			date:$("#dater").val(),
			departure:$("#departurer").val(),
			arrival:$("#arrivalr").val()
		};
		getServerData(urlSearchFlight, afterSearch, 'post', data);
	});
});



//click de bouton planifié vol
$(function(){
	$("#pFlight").click(function(){
		//construction des donne du vol
		var data={
			date:$("#datep").val(),
			timep:$("#timep").val(),
			departureAirport:$("#departurep").val(),
			arrivalAirport:$("#arrivalp").val(),
			travelTime:$("#travelTimep").val(),
			price:$("#pricep").val(),
			typeflight:"local"
		};
		
		getServerData(urlPostFlight,null, 'post', data);
	});
});
