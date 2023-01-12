package net.ddns.cloudtecnologia.pessoas.rest.controller;

import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;
import net.ddns.cloudtecnologia.pessoas.rest.dto.EnderecoDTO;
import net.ddns.cloudtecnologia.pessoas.rest.dto.PessoaDTO;
import net.ddns.cloudtecnologia.pessoas.service.impl.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaServiceImpl service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaDTO salvarPessoa(@RequestBody @Valid PessoaDTO dto) {
        return new PessoaDTO(service.salvarPessoa(dto));
    }

    @GetMapping
    public List<PessoaDTO> listarTodasAsPessoas() {
        List<PessoaDTO> lista = new ArrayList<>();
        service.listarPessoas().stream().forEach(item -> lista.add(PessoaDTO.converterParaDto(item)));
        return lista;
    }


    @GetMapping("/{id}")
    public PessoaDTO consultarPessoaId(@PathVariable Integer id) {
        return new PessoaDTO(service.consultarPessoaId(id));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPessoa(@PathVariable Integer id) {
        service.deletarPessoa(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPessoa(@PathVariable Integer id,
                                @RequestBody @Valid PessoaDTO dto) {
        service.atualizarPessoa(id, dto);
    }

    @GetMapping("/{id}/enderecos")
    public List<EnderecoDTO> listarEnderecosDaPessoa(@PathVariable Integer id) {
        List<EnderecoDTO> lista = new ArrayList<>();
        service.listarEnderecosPessoa(id).stream().forEach(item -> lista.add(EnderecoDTO.converterParaDto(item)));
        return lista;
    }

    @GetMapping("/{id}/enderecos/principal")
    public EnderecoDTO listarEnderecoPrincipalDaPessoa(@PathVariable Integer id) {
        return new EnderecoDTO(service.listarEnderecoPrincipalPessoa(id));
    }

    @PatchMapping("/{idPessoa}/enderecos/principal/{idEndereco}")
    public void definirEnderecoPrincipal(@PathVariable Integer idPessoa,
                                         @PathVariable Integer idEndereco) {
        service.definirEnderecoPrincipal(idPessoa, idEndereco);
    }
}
