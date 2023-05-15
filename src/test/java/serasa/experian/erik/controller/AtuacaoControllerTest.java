package serasa.experian.erik.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import serasa.experian.erik.dto.AtuacaoDTO;
import serasa.experian.erik.model.Atuacao;
import serasa.experian.erik.service.AtuacaoService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AtuacaoControllerTest {

    @Mock
    private AtuacaoService atuacaoService;

    @InjectMocks
    private AtuacaoController atuacaoController;

    @Test
    public void testIncluirAtuacao() {
        Atuacao atuacao = new Atuacao();
        atuacao.setRegiao("Sudeste");
        atuacao.setEstados(Arrays.asList("SP", "RJ", "MG", "ES"));

        Atuacao atuacaoIncluida = new Atuacao();
        atuacaoIncluida.setRegiao(atuacao.getRegiao());
        atuacaoIncluida.setEstados(atuacao.getEstados());

        when(atuacaoService.incluirAtuacao(atuacao)).thenReturn(atuacaoIncluida);

        ResponseEntity<?> response = atuacaoController.incluirAtuacao(atuacao);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(atuacaoIncluida, response.getBody());
        verify(atuacaoService).incluirAtuacao(atuacao);
    }
}
