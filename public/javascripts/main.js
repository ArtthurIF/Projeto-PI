$(function() {
    $("#uf").change(function() {
        let uf = $("#uf").val();
        
        $.ajax({
            url: "/Viagens/obterCidades",  // Atualize para a rota correta
            type: "GET",
            data: { uf: uf },
            dataType: "json",
            success: function(cidades) {
                $("#cidades").html('<option value="">Selecione uma cidade</option>'); // Limpa o select e adiciona um placeholder
                cidades.forEach(function(cidade) {
                    let option = `<option value="${cidade.id}">${cidade.nome}</option>`;
                    $("#cidades").append(option);
                });
            },
            error: function() {
                alert("Erro ao buscar cidades. Tente novamente.");
            }
        });
    });
});
