package net.ddns.cloudtecnologia.pessoas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    @NotEmpty(message = "{campo.logradouro.obrigatorio}")
    private String logradouro;

    @NotEmpty(message = "{campo.cep.obrigatorio}")
    private String cep;

    @NotNull(message = "{campo.numero.obrigatorio}")
    private Integer numero;


    @NotEmpty(message = "{campo.cidade.obrigatorio}")
    private String cidade;

    @NotNull(message = "{campo.idPessoa.obrigatorio}")
    private Integer idPessoa;

    //
    public EnderecoDTO(Endereco end) {
        this.logradouro = end.getLogradouro();
        this.cep = end.getCep();
        this.numero = end.getNumero();
        this.cidade = end.getCidade();
        this.idPessoa = end.getPessoa().getId();
    }
}
