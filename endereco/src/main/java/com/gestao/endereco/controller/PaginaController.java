package com.gestao.endereco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/editar-endereco")
    public String editarEndereco(){
        return "editar-endereco";
    }

    @GetMapping("/excluir-endereco")
    public String excluirEndereco(){
        return "excluir-endereco";
    }
}