package serasa.experian.erik.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "atuacoes")
@Data
public class Atuacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String regiao;

    @ElementCollection
    @CollectionTable(name = "atuacoes_estados", joinColumns = @JoinColumn(name = "atuacao_id"))
    @Column(name = "estados")
    private List<String> estados;
}
