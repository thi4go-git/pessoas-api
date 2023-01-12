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


    @GetMapping("/{idPessoa}")
    public PessoaDTO consultarPessoaId(@PathVariable Integer idPessoa) {
        return new PessoaDTO(service.consultarPessoaId(idPessoa));
    }


    @DeleteMapping("/{idPessoa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPessoa(@PathVariable Integer idPessoa) {
        service.deletarPessoa(idPessoa);
    }

    @PutMapping("/{idPessoa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPessoa(@PathVariable Integer idPessoa,
                                @RequestBody @Valid PessoaDTO dto) {
        service.atualizarPessoa(idPessoa, dto);
    }

    @GetMapping("/{idPessoa}/enderecos")
    public List<EnderecoDTO> listarEnderecosDaPessoa(@PathVariable Integer idPessoa) {
        List<EnderecoDTO> lista = new ArrayList<>();
        service.listarEnderecosPessoa(idPessoa).stream().forEach(item -> lista.add(EnderecoDTO.converterParaDto(item)));
        return lista;
    }

    @GetMapping("/{idPessoa}/enderecos/principal")
    public EnderecoDTO listarEnderecoPrincipalDaPessoa(@PathVariable Integer idPessoa) {
        return new EnderecoDTO(service.listarEnderecoPrincipalPessoa(idPessoa));
    }

    @PatchMapping("/{idPessoa}/enderecos/principal/{idEndereco}")
    public void definirEnderecoPrincipal(@PathVariable Integer idPessoa,
                                         @PathVariable Integer idEndereco) {
        service.definirEnderecoPrincipal(idPessoa, idEndereco);
    }
}
