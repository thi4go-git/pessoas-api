package net.ddns.cloudtecnologia.pessoas.service.impl;

import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
import net.ddns.cloudtecnologia.pessoas.model.repository.PessoaRepository;
import net.ddns.cloudtecnologia.pessoas.rest.dto.PessoaDTO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaServiceImplTest {
    //
    private static final int ID = 1;
    private static final int INDEX_0 = 0;
    private static final String NOME = "Usuário de testes";
    private static final String NASCIMENTO = "1998-08-08";
    private static final String MSG_NOT_FOUND = "Pessoa não encontrada";

    @InjectMocks
    private PessoaServiceImpl service;

    @Mock
    private PessoaRepository repository;

    private Pessoa pessoa;
    private PessoaDTO pessoaDTO;
    private Optional<Pessoa> optionalPessoa;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPessoa();
    }

    @Test
    @Order(1)
    void salvarPessoaSucesso() {
        when(repository.save(any())).thenReturn(pessoa);
        //
        Pessoa response = service.salvarPessoa(pessoaDTO);
        //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Pessoa.class, response.getClass());
        Assertions.assertEquals(NOME, response.getNome());
    }

    @Test
    @Order(2)
    void listarPessoasSucesso() {
        when(repository.findAll()).thenReturn(List.of(pessoa));
        //
        List<Pessoa> lista = service.listarPessoas();
        //
        int tamanhoEsperado = 1;
        Assertions.assertNotNull(lista);
        Assertions.assertEquals(tamanhoEsperado, lista.size());
        Assertions.assertEquals(Pessoa.class, lista.get(INDEX_0).getClass());
        Assertions.assertEquals(NOME, lista.get(INDEX_0).getNome());
    }

    @Test
    void retornarPessoaNaoEncontradaAoPesquisarPorIdSucesso() {
        when(repository.findById(anyInt())).thenThrow(
                new ResponseStatusException(HttpStatus.NOT_FOUND, MSG_NOT_FOUND));
        try {
            service.consultarPessoaId(ID);
        } catch (Exception ex) {
            Assertions.assertEquals(ResponseStatusException.class, ex.getClass());
            Assertions.assertTrue(ex.getMessage().contains(MSG_NOT_FOUND));
        }
    }

    @Test
    @Order(3)
    void consultarPessoaIdSucesso() {
        when(repository.findById(anyInt())).thenReturn(optionalPessoa);
        //
        Pessoa response = service.consultarPessoaId(ID);
        //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Pessoa.class, response.getClass());
        Assertions.assertEquals(NOME, response.getNome());
    }

    @Test
    @Order(5)
    void deletarPessoaErro() {
        when(repository.save(any())).thenReturn(pessoa);
        //
        Pessoa response = service.salvarPessoa(pessoaDTO);
        //
        try {
            service.deletarPessoa(response.getId());
        } catch (Exception ex) {
            Assertions.assertEquals(ResponseStatusException.class, ex.getClass());
            Assertions.assertTrue(ex.getMessage().contains(MSG_NOT_FOUND));
        }
    }

    @Test
    @Order(4)
    void atualizarPessoaErro() {
        when(repository.save(any())).thenReturn(pessoa);
        //
        Pessoa response = service.salvarPessoa(pessoaDTO);
        //
        try {
            service.atualizarPessoa(response.getId(), pessoaDTO);
        } catch (Exception ex) {
            Assertions.assertEquals(ResponseStatusException.class, ex.getClass());
            Assertions.assertTrue(ex.getMessage().contains(MSG_NOT_FOUND));
        }

    }

    private void startPessoa() {
        pessoaDTO = new PessoaDTO(NOME, NASCIMENTO);
        pessoa = new Pessoa(pessoaDTO);
        optionalPessoa = Optional.of(new Pessoa(pessoaDTO));
    }
}