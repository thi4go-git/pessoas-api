package net.ddns.cloudtecnologia.pessoas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.pessoas.rest.dto.EnderecoDTO;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String logradouro;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, length = 200)
    private String cidade;

    @Column
    private boolean principal;
    //
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    //
    public Endereco(EnderecoDTO dto, Pessoa pessoa) {
        this.logradouro = dto.getLogradouro();
        this.cep = dto.getCep();
        this.numero = dto.getNumero();
        this.cidade = dto.getCidade();
        this.pessoa = pessoa;
    }


    public Endereco(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
