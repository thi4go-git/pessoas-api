package net.ddns.cloudtecnologia.pessoas.rest.controller;

import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
import net.ddns.cloudtecnologia.pessoas.rest.dto.PessoaDTO;
import net.ddns.cloudtecnologia.pessoas.service.impl.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PessoaController {

    @Autowired
    private PessoaServiceImpl service;


    @PostMapping("/pessoas")
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvarPessoa(@RequestBody @Valid PessoaDTO dto) {
        return service.salvarPessoa(dto);
    }

    @GetMapping("/pessoas")
    public List<Pessoa> listarTodasAsPessoas() {
        return service.listarPessoas();
    }


    @GetMapping("/pessoas/{id}")
    public Pessoa consultarPessoaId(@PathVariable Integer id) {
        return service.consultarPessoaId(id);
    }


    @DeleteMapping("/pessoas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPessoa(@PathVariable Integer id) {
        service.deletarPessoa(id);
    }

    @PutMapping("/pessoas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPessoa(@PathVariable Integer id,
                                @RequestBody @Valid PessoaDTO dto) {
        service.atualizarPessoa(id, dto);
    }
}
