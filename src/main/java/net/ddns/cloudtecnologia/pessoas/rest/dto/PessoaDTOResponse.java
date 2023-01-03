package net.ddns.cloudtecnologia.pessoas.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTOResponse {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;

    //
    public PessoaDTOResponse(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
    }
}
