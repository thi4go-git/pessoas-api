package net.ddns.cloudtecnologia.pessoas.service;

import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;
import net.ddns.cloudtecnologia.pessoas.rest.dto.EnderecoDTO;

import java.util.List;

public interface EnderecoService {
    Endereco salvarEndereco(EnderecoDTO dto);

    List<Endereco> listarEnderecos();

    List<Endereco> listarEnderecosPessoa(Integer idPessoa);

    List<Endereco> listarEnderecoPrincipalPessoa(Integer idPessoa);

    void definirEnderecoPrincipal(Integer idPessoa, Integer idEndereco);

}
