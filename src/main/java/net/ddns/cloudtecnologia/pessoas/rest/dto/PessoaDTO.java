package net.ddns.cloudtecnologia.pessoas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;
import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private Integer id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.dataNascimento.obrigatorio}")
    private String dataNascimento;

    List<Endereco> enderecos = new ArrayList<>();


    public PessoaDTO(String nome, String dataNascimento, List<Endereco> enderecos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }

    //
    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento().toString();
        this.enderecos = pessoa.getEnderecos();
    }

    //
    public static PessoaDTO converterParaDto(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setDataNascimento(pessoa.getDataNascimento().toString());
        dto.setEnderecos(pessoa.getEnderecos());
        return dto;
    }


}
