package serasa.experian.erik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import serasa.experian.erik.model.Atuacao;
import serasa.experian.erik.respository.AtuacaoRepository;

@Service
@RequiredArgsConstructor
public class AtuacaoService {
    private final AtuacaoRepository atuacaoRepository;

    public Atuacao incluirAtuacao(Atuacao atuacao) {
        return atuacaoRepository.save(atuacao);
    }
}
