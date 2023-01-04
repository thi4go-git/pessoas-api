package net.ddns.cloudtecnologia.pessoas.rest.controller;

import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
import net.ddns.cloudtecnologia.pessoas.rest.dto.PessoaDTO;
import net.ddns.cloudtecnologia.pessoas.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


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


    @InjectMocks
    private PessoaController controller;

    @Mock
    private PessoaServiceImpl service;
    //
    private Pessoa pessoa;
    private PessoaDTO pessoaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPessoa();
    }

    @Test
    void salvarPessoaSucesso() {
        when(service.salvarPessoa(any())).thenReturn(pessoa);
        //
        Pessoa response = controller.salvarPessoa(pessoaDTO);
        //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Pessoa.class, response.getClass());
        Assertions.assertEquals(NOME, response.getNome());
    }

    @Test
    void listarPessoas() {
        when(service.listarPessoas()).thenReturn(List.of(pessoa));
        //
        List<Pessoa> lista = controller.listarPessoas();
        //
        int tamanhoEsperado = 1;
        Assertions.assertNotNull(lista);
        Assertions.assertEquals(tamanhoEsperado, lista.size());
        Assertions.assertEquals(Pessoa.class, lista.get(INDEX_0).getClass());
        Assertions.assertEquals(NOME, lista.get(INDEX_0).getNome());
    }

    @Test
    void consultarPessoaId() {
        when(service.consultarPessoaId(anyInt())).thenReturn(pessoa);
        //
        Pessoa response = controller.consultarPessoaId(ID);
        //
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Pessoa.class, response.getClass());
        Assertions.assertEquals(NOME, response.getNome());
    }

    @Test
    void deletarPessoa() {
    }

    @Test
    void atualizarPessoa() {
    }

    private void startPessoa() {
        pessoaDTO = new PessoaDTO(NOME, NASCIMENTO);
        pessoa = new Pessoa(pessoaDTO);
    }
}