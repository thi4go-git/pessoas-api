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

    private final String PESSOA_INEXISTENTE_ID = "Não existe pessoa com esse ID!";

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

    @Override
    public List<Endereco> listarEnderecosPessoa(Integer idPessoa) {
        Pessoa pessoa = pessoaRepository.
                findById(idPessoa)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                PESSOA_INEXISTENTE_ID));
        return enderecoRepository.findByPessoa(pessoa);
    }

    @Override
    public List<Endereco> listarEnderecoPrincipalPessoa(Integer idPessoa) {
        Pessoa pessoa = pessoaRepository.
                findById(idPessoa)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                PESSOA_INEXISTENTE_ID));
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);
        endereco.setPrincipal(true);
        Example<Endereco> examplePric = Example.of(endereco, matcher);
        return enderecoRepository.findAll(examplePric);
    }

    @Override
    @Transactional
    public void definirEnderecoPrincipal(Integer idPessoa, Integer idEndereco) {
        Pessoa pessoa = pessoaRepository.
                findById(idPessoa)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Não existe pessoa com esse ID!"));
        List<Endereco> todos = enderecoRepository.findByPessoa(pessoa);
        for (Endereco end : enderecoRepository.findByPessoa(pessoa)) {
            end.setPrincipal(false);
            todos.add(end);
        }
        enderecoRepository.saveAll(todos);
        //-----
        Endereco endereco = enderecoRepository.
                findById(idEndereco)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Não existe Endereço com esse ID!"));
        endereco.setPrincipal(true);

        enderecoRepository.save(endereco);
    }
}
