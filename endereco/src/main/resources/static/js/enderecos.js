function abrirSucesso(){

    limparErros();

    let valido = true;

    const cep = document.getElementById("cep");
    const rua = document.getElementById("rua");
    const numero = document.getElementById("numero");
    const bairro = document.getElementById("bairro");
    const cidade = document.getElementById("cidade");
    const estado = document.getElementById("estado");
    const cliente = document.getElementById("cliente");



    if(cep.value === ""){

        document.getElementById("erroCep").innerText = "Campo obrigatório";

        cep.classList.add("input-erro");

        valido = false;
    }

    if(rua.value === ""){

        document.getElementById("erroRua").innerText = "Campo obrigatório";

        rua.classList.add("input-erro");

        valido = false;
    }

    if(numero.value === ""){

        document.getElementById("erroNumero").innerText = "Campo obrigatório";

        numero.classList.add("input-erro");

        valido = false;
    }

   if(bairro.value === ""){

       document.getElementById("erroBairro").innerText = "Campo obrigatório";

       bairro.classList.add("input-erro");

       valido = false;
   }

    if(cidade.value === ""){

        document.getElementById("erroCidade").innerText = "Campo obrigatório";

        cidade.classList.add("input-erro");

        valido = false;
    }

    if(estado.value === ""){

        document.getElementById("erroEstado").innerText = "Campo obrigatório";

        estado.classList.add("input-erro");

        valido = false;
    }

    if(cliente.value === ""){

        document.getElementById("erroCliente").innerText = "Campo obrigatório";

        cliente.classList.add("input-erro");

        valido = false;
            }

    if(!valido){
        return;
    }

    document.getElementById("modalSucesso").style.display = "flex";
}
function limparErros(){

    const erros = document.querySelectorAll(".erro");

    erros.forEach(erro => {
        erro.innerText = "";
    });

    const inputs = document.querySelectorAll("input");

    inputs.forEach(input => {
        input.classList.remove("input-erro");
    });
}
async function buscarCep(){

    let cep = document.getElementById("cep").value;

    cep = cep.replace(/\D/g, "");

    if(cep.length !== 8){
        return;
    }

    const resposta = await fetch(`https://viacep.com.br/ws/${cep}/json/`);

    const dados = await resposta.json();


    document.getElementById("rua").value = dados.logradouro;
    document.getElementById("bairro").value = dados.bairro;
    document.getElementById("cidade").value = dados.localidade;
    document.getElementById("estado").value = dados.uf;
}

// Remove a mensagem de erro e a borda vermelha do campo
function limparErroCampo(inputId, erroId){


    // Pega o input pelo ID
    const input = document.getElementById(inputId);

    // Pega o span da mensagem de erro
    const erro = document.getElementById(erroId);

    // Remove a classe da borda vermelha
    input.classList.remove("input-erro");

     // Limpa a mensagem de erro
    erro.innerText = "";
}

function abrirSucessoExcluir(){

    document.getElementById("modalSucessoExcluir").style.display = "flex";
}

function fecharSucessoExcluir(){

    document.getElementById("modalSucessoExcluir").style.display = "none";
}

function fecharModalExcluir(){

    document.getElementById("modalExcluir").style.display = "none";
}