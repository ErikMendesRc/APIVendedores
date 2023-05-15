package serasa.experian.erik.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import serasa.experian.erik.model.Atuacao;
import serasa.experian.erik.respository.AtuacaoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AtuacaoServiceTest {

    private AtuacaoService atuacaoService;
    @Mock
    private AtuacaoRepository atuacaoRepository;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        atuacaoService = new AtuacaoService(atuacaoRepository);
    }

    @Test
    public void testIncluirAtuacao() {
        Atuacao atuacao = new Atuacao();
        List<String> estados = new ArrayList<>();
        estados.add("SP");
        estados.add("RJ");
        estados.add("MG");
        atuacao.setRegiao("Sudeste");
        atuacao.setEstados(estados);

        when(atuacaoRepository.save(atuacao)).thenReturn(atuacao);

        Atuacao resultado = atuacaoService.incluirAtuacao(atuacao);

        assertNotNull(resultado);
        assertEquals(atuacao, resultado);
    }
}