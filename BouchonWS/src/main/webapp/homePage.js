
	function myFunction(listF) {
		$("#resultr").text("");
		//alert("je parse");
		/*var obj=JSON.parse(listF);
		console.log("je parse "+listF.length);*/
		console.log(listF.length);
		for(i=0;i<listF.length;i++){
			var templateExample = _.template($('#ajoutr').html());
			var html = templateExample({
				"depart":listF[i].departureAirport,
				"arrive":listF[i].arrivalAirport,
				"date":listF[i].date,
				"type":listF[i].typeflight
			});
			$("#resultr").append(html);
			console.log(listF[i].departureAirport+" "+listF[i].arrivalAirport+" "+listF[i].price+" "+listF[i].travelTime);
			//console.log(html);
		}
	}
	
$(function(){
	$("#rvol").click(function(){
		var donne={typeLocal:$("#local").val(),
			typeTravel:$("#travel").val(),
			date:$("#date").val(),
			departure:$("#departure").val(),
			arrival:$("#arrival").val()
		};
		$.ajax({
			url: 'http://localhost:8080/blablaplane/flights/search',
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			success: function (data) {
				myFunction(data);
				//alert("ok");
			},
			data: JSON.stringify(donne),
			error : function(jqXHR, textStatus, errorThrown) {
				alert("pas ok");
			}
		});
	});
});