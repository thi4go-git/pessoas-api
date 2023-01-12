package net.ddns.cloudtecnologia.pessoas.rest.controller;

import net.ddns.cloudtecnologia.pessoas.rest.dto.EnderecoDTO;
import net.ddns.cloudtecnologia.pessoas.service.impl.EnderecoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDTO salvarEndereco(@RequestBody @Valid EnderecoDTO dto) {
        return new EnderecoDTO(service.salvarEndereco(dto));
    }

    @GetMapping
    public List<EnderecoDTO> listarTodosEnderecos() {
        List<EnderecoDTO> lista = new ArrayList<>();
        service.listarEnderecos().stream().forEach(item -> lista.add(EnderecoDTO.converterParaDto(item)));
        return lista;
    }

}
