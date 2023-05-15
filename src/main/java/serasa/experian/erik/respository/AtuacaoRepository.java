package serasa.experian.erik.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serasa.experian.erik.model.Atuacao;

@Repository
public interface AtuacaoRepository extends JpaRepository<Atuacao, Integer> {
}
