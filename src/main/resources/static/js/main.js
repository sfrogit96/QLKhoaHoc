/**
 * 
 */

$(document).ready(function(){
	$('.table .eBtn').on('click', function(event){
		
		event.preventDefault();
		var href = $(this).attr('href');
		
		$.get(href, function(dkt, status){
			$('.myForm #iddiemkt').val(dkt.iddiemkt);
			$('.myForm #diemso').val('');
			$('.myForm #danhgia').val('');
		});
		
		$('.myForm #exampleModal').modal();
	});
});