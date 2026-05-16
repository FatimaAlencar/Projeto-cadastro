package com.gestao.endereco.Service;

import com.gestao.endereco.dto.CreateEnderecoDto;
import com.gestao.endereco.model.Cidade;
import com.gestao.endereco.model.Cliente;
import com.gestao.endereco.model.Endereco;
import com.gestao.endereco.repository.CidadeRepository;
import com.gestao.endereco.repository.ClienteRepository;
import com.gestao.endereco.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;
    private final CidadeRepository cidadeRepository;

    public EnderecoService(EnderecoRepository enderecoRepository,
                           ClienteRepository clienteRepository,
                           CidadeRepository cidadeRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
    }

    //  LISTAR
    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }
    public List<Endereco> buscarPorCliente(Long clienteId) {
        return enderecoRepository.findByClienteId(clienteId);
    }

    //  CRIAR
    public Endereco salvar(CreateEnderecoDto dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Cidade cidade = new Cidade();
        cidade.setNome(dto.getCidade());
        cidade.setEstado(dto.getEstado());
        cidadeRepository.save(cidade);

        Endereco endereco = new Endereco();
        endereco.setCep(dto.getCep());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setPrincipal(dto.getPrincipal());

        endereco.setCreationTimestamp(LocalDateTime.now());
        endereco.setUpdateTimestamp(LocalDateTime.now());

        endereco.setCliente(cliente);
        endereco.setCidade(cidade);

        // REGRA: só 1 principal por cliente
        if (Boolean.TRUE.equals(dto.getPrincipal())) {
            List<Endereco> lista = enderecoRepository.findAll();

            for (Endereco e : lista) {
                if (e.getCliente().getId().equals(cliente.getId())) {
                    e.setPrincipal(false);
                    enderecoRepository.save(e);
                }
            }
        }

        return enderecoRepository.save(endereco);
    }

    // EDITAR
    public Endereco atualizar(Long id, CreateEnderecoDto dto) {

        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Cidade cidade = new Cidade();
        cidade.setNome(dto.getCidade());
        cidade.setEstado(dto.getEstado());
        cidadeRepository.save(cidade);

        endereco.setCep(dto.getCep());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setPrincipal(dto.getPrincipal());

        endereco.setUpdateTimestamp(LocalDateTime.now());

        endereco.setCliente(cliente);
        endereco.setCidade(cidade);

        //  REGRA do principal
        if (Boolean.TRUE.equals(dto.getPrincipal())) {
            List<Endereco> lista = enderecoRepository.findAll();

            for (Endereco e : lista) {
                if (!e.getId().equals(endereco.getId()) &&
                        e.getCliente().getId().equals(cliente.getId())) {
                    e.setPrincipal(false);
                    enderecoRepository.save(e);
                }
            }
        }

        return enderecoRepository.save(endereco);
    }

    //  EXCLUIR
    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
}