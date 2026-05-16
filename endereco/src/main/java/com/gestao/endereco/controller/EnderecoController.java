package com.gestao.endereco.controller;

import com.gestao.endereco.dto.CreateEnderecoDto;
import com.gestao.endereco.model.Endereco;
import com.gestao.endereco.Service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }


    //  LISTAR
    @GetMapping
    public List<Endereco> listar() {
        return service.listar();
    }
    @GetMapping("/cliente/{clienteId}")
    public List<Endereco> buscarPorCliente(@PathVariable Long clienteId) {
        return service.buscarPorCliente(clienteId);
    }

    //  CRIAR
    @PostMapping
    public Endereco salvar(@RequestBody CreateEnderecoDto dto) {
        return service.salvar(dto);
    }

    //  EDITAR
    @PutMapping("/{id}")
    public Endereco atualizar(@PathVariable Long id,
                              @RequestBody CreateEnderecoDto dto) {
        return service.atualizar(id, dto);
    }

    //  EXCLUIR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}