package net.ddns.cloudtecnologia.pessoas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;
import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private Integer id;

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

    private boolean principal;

    //
    public EnderecoDTO(Endereco end) {
        this.id = end.getId();
        this.logradouro = end.getLogradouro();
        this.cep = end.getCep();
        this.numero = end.getNumero();
        this.cidade = end.getCidade();
        this.idPessoa = end.getPessoa().getId();
    }

    //
    public static EnderecoDTO converterParaDto(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId());
        dto.setCep(endereco.getCep());
        dto.setCidade(endereco.getCidade());
        dto.setIdPessoa(endereco.getPessoa().getId());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setPrincipal(endereco.isPrincipal());
        return dto;
    }

}
