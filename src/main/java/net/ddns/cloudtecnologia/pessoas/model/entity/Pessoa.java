package net.ddns.cloudtecnologia.pessoas.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ddns.cloudtecnologia.pessoas.rest.dto.PessoaDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa")
    List<Endereco> enderecos = new ArrayList<>();


    public Pessoa(PessoaDTO dto) {
        this.nome = dto.getNome();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataNascimento = LocalDate.parse(dto.getDataNascimento().trim(), formatter);
        this.enderecos = dto.getEnderecos();
    }
}
