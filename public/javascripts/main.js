$ (function() {
	
	$ ("#uf").change(function() {
		
		uf = $ ("#uf").val();
		
		$.ajax({
			url:"Cidades/listar",
			data:"uf=" + uf,
			dataType:"json",
			success:function(c) {
				
				$("#cidades").html("");
				for (var i = 0; i < c.length; i++) {
					opt = "<option value ="+c[i].id+">"+c[i].nome+"</option>";
					$("#cidades").append(opt);
				}
			}
			
		});
	});
	
});