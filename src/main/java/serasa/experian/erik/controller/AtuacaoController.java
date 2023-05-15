package serasa.experian.erik.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serasa.experian.erik.model.Atuacao;
import serasa.experian.erik.service.AtuacaoService;

@Api( value = "RegiaoController")
@RestController
@RequestMapping("/atuacao")
@RequiredArgsConstructor
public class AtuacaoController {
    private final AtuacaoService atuacaoService;

    @ApiOperation(value = "Adicionar região de atuação.")
    @PostMapping
    public ResponseEntity<?> incluirAtuacao(@RequestBody Atuacao atuacao) {
        try {
            Atuacao atuacaoIncluida = atuacaoService.incluirAtuacao(atuacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(atuacaoIncluida);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
