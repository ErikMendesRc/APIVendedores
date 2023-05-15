package serasa.experian.erik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serasa.experian.erik.model.Atuacao;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtuacaoDTO {
    private String regiao;
    private List<String> estados;

    public static AtuacaoDTO toDTO(Atuacao atuacao) {
        AtuacaoDTO atuacaoDTO = new AtuacaoDTO();
        atuacaoDTO.setRegiao(atuacao.getRegiao());
        atuacaoDTO.setEstados(atuacao.getEstados());

        return atuacaoDTO;
    }
}
