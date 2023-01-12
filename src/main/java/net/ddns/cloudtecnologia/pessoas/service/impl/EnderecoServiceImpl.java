package net.ddns.cloudtecnologia.pessoas.service.impl;

import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;
import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
import net.ddns.cloudtecnologia.pessoas.model.repository.EnderecoRepository;
import net.ddns.cloudtecnologia.pessoas.model.repository.PessoaRepository;
import net.ddns.cloudtecnologia.pessoas.rest.dto.EnderecoDTO;
import net.ddns.cloudtecnologia.pessoas.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;



    @Override
    @Transactional
    public Endereco salvarEndereco(EnderecoDTO dto) {
        Pessoa pessoa = pessoaRepository.
                findById(dto.getIdPessoa())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Pessoa não encontrado para Salvar endereço!"));
        Endereco endereco = new Endereco(dto, pessoa);
        return enderecoRepository.save(endereco);

    }

    @Override
    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }


}
