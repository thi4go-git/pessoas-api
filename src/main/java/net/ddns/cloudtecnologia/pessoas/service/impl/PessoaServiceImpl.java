package net.ddns.cloudtecnologia.pessoas.service.impl;


import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
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

    @Autowired
    private PessoaRepository pessoaRepository;

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
}