package net.ddns.cloudtecnologia.pessoas.service;

import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
import net.ddns.cloudtecnologia.pessoas.rest.dto.PessoaDTO;

import java.util.List;

public interface PessoaService {
    Pessoa salvarPessoa(PessoaDTO dto);

    List<Pessoa> listarPessoas();

    void deletarPessoa(Integer id);

    void atualizarPessoa(Integer id, PessoaDTO dto);

    Pessoa consultarPessoaId(Integer id);
}