package net.ddns.cloudtecnologia.pessoas.rest.controller;

import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;
import net.ddns.cloudtecnologia.pessoas.rest.dto.EnderecoDTO;
import net.ddns.cloudtecnologia.pessoas.service.impl.EnderecoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EnderecoController {

    @Autowired
    private EnderecoServiceImpl service;

    @PostMapping("/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDTO salvarEndereco(@RequestBody @Valid EnderecoDTO dto) {
        return new EnderecoDTO(service.salvarEndereco(dto));
    }

    @GetMapping("/enderecos")
    public List<Endereco> listarTodosEnderecos() {
        return service.listarEnderecos();
    }

    @GetMapping("/pessoas/{id}/enderecos")
    public List<Endereco> listarEnderecosDaPessoa(@PathVariable Integer id) {
        return service.listarEnderecosPessoa(id);
    }

    @GetMapping("/pessoas/{id}/enderecos/principal")
    public List<Endereco> listarEnderecoPrincipalDaPessoa(@PathVariable Integer id) {
        return service.listarEnderecoPrincipalPessoa(id);
    }

    @PatchMapping("/pessoas/{idPessoa}/enderecos/principal/{idEndereco}")
    public void definirEnderecoPrincipal(@PathVariable Integer idPessoa,
                                         @PathVariable Integer idEndereco) {
        service.definirEnderecoPrincipal(idPessoa, idEndereco);
    }

}
