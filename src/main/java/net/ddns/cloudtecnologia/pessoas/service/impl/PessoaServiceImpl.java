package net.ddns.cloudtecnologia.pessoas.service.impl;


import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;
import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
import net.ddns.cloudtecnologia.pessoas.model.repository.EnderecoRepository;
import net.ddns.cloudtecnologia.pessoas.model.repository.PessoaRepository;
import net.ddns.cloudtecnologia.pessoas.rest.dto.PessoaDTO;
import net.ddns.cloudtecnologia.pessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    private static final String PESSOA_INEXISTENTE_ID = "Não existe pessoa com esse ID!";
    private static final String ENDERECO_INEXISTENTE_ID = "Não existe Endereço com esse ID!";
    private static final String ENDERECO_PRINCIPAL_INEXISTENTE = "Essa Pessoa não tem Endereço Principal";


    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public Pessoa salvarPessoa(PessoaDTO dto) {
        return pessoaRepository.save(new Pessoa(dto));
    }

    @Override
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    @Override
    @Transactional
    public void deletarPessoa(Integer id) {
        pessoaRepository.
                findById(id)
                .map(pessoa -> {
                    pessoaRepository.delete(pessoa);
                    return Void.TYPE;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada para deletar!"));
    }

    @Override
    @Transactional
    public void atualizarPessoa(Integer id, PessoaDTO dto) {
        pessoaRepository.
                findById(id)
                .map(pessoaAchada -> {
                    pessoaAchada.setNome(dto.getNome());
                    pessoaAchada.setDataNascimento(LocalDate.parse(dto.getDataNascimento()));
                    return pessoaRepository.save(pessoaAchada);
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada para atualizar!"));
    }

    @Override
    public Pessoa consultarPessoaId(Integer id) {
        return pessoaRepository.
                findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada Pelo ID !"));
    }

    @Override
    public List<Endereco> listarEnderecosPessoa(Integer idPessoa) {
        Pessoa pessoa = pessoaRepository.
                findById(idPessoa)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                PESSOA_INEXISTENTE_ID));
        return pessoa.getEnderecos();
    }

    @Override
    public Endereco listarEnderecoPrincipalPessoa(Integer idPessoa) {
        Pessoa pessoa = pessoaRepository.
                findById(idPessoa)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                PESSOA_INEXISTENTE_ID));
        for (Endereco enderecoAtual : pessoa.getEnderecos()) {
            if (enderecoAtual.isPrincipal()) {
                return enderecoAtual;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                ENDERECO_PRINCIPAL_INEXISTENTE);
    }

    @Override
    @Transactional
    public void definirEnderecoPrincipal(Integer idPessoa, Integer idEndereco) {
        Pessoa pessoa = pessoaRepository.
                findById(idPessoa)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                PESSOA_INEXISTENTE_ID));
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
                                ENDERECO_INEXISTENTE_ID));
        endereco.setPrincipal(true);

        enderecoRepository.save(endereco);
    }
}
