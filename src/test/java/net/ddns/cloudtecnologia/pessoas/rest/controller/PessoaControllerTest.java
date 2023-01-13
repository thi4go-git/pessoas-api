package net.ddns.cloudtecnologia.pessoas.rest.controller;

import net.ddns.cloudtecnologia.pessoas.model.entity.Endereco;
import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
import net.ddns.cloudtecnologia.pessoas.rest.dto.PessoaDTO;
import net.ddns.cloudtecnologia.pessoas.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PessoaControllerTest {

    private static final int ID = 1;
    private static final int INDEX_0 = 0;
    private static final String NOME = "Usuário de testes";
    private static final String NASCIMENTO = "1998-08-08";
    //
    private static final int CREATED = 201;
    private static final int OK = 200;
    private static final int NO_CONTENT = 204;


    //
    @InjectMocks
    private PessoaController controller;

    @Mock
    private PessoaServiceImpl service;
    //
    private Pessoa pessoa;
    private PessoaDTO pessoaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startPessoa();
    }

    @Test
    @Order(1)
    void salvarPessoaSucesso() {
        when(service.salvarPessoa(any())).thenReturn(pessoa);
        //
        ResponseEntity<PessoaDTO> response = controller.salvarPessoa(pessoaDTO);
        //
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(PessoaDTO.class, response.getBody().getClass());
        Assertions.assertEquals(CREATED, response.getStatusCode().value());
        Assertions.assertEquals(NOME, response.getBody().getNome());
        Assertions.assertEquals(NASCIMENTO, response.getBody().getDataNascimento());
    }

    @Test
    @Order(2)
    void listarPessoas() {
        when(service.listarPessoas()).thenReturn(List.of(pessoa));
        //
        ResponseEntity<List<PessoaDTO>>
                response = controller.listarTodasAsPessoas();
        //
        int tamanhoEsperado = 1;
        List<PessoaDTO> lista = response.getBody();
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(OK, response.getStatusCode().value());
        Assertions.assertEquals(tamanhoEsperado, lista.size());
        Assertions.assertEquals(NOME, lista.get(INDEX_0).getNome());
    }


    @Test
    @Order(3)
    void atualizarPessoaSucesso() {
        when(service.atualizarPessoa(ID, pessoaDTO)).thenReturn(pessoa);
        //
        String nomeAtualizado = NOME + "-Atualizado";
        PessoaDTO atualizado =
                new PessoaDTO(nomeAtualizado, NASCIMENTO, new ArrayList<Endereco>());
        ResponseEntity<PessoaDTO> response = controller.atualizarPessoa(ID, atualizado);
        //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(NO_CONTENT, response.getStatusCode().value());
    }

    @Test
    @Order(4)
    void consultarPessoaId() {
        when(service.consultarPessoaId(ID)).thenReturn(pessoa);
        //
        ResponseEntity<PessoaDTO>
                response = controller.consultarPessoaId(ID);
        //
        System.out.println(response.getBody().toString());
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(PessoaDTO.class, response.getBody().getClass());
        Assertions.assertEquals(OK, response.getStatusCode().value());
        Assertions.assertEquals(NASCIMENTO, response.getBody().getDataNascimento());
    }


    private void startPessoa() {
        pessoaDTO = new PessoaDTO(ID, NOME, NASCIMENTO, new ArrayList<Endereco>());
        pessoa = new Pessoa(pessoaDTO);
    }

}