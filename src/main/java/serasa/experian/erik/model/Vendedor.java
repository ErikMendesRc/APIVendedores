package serasa.experian.erik.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendedores")
@Data
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
    @NotNull
    private int idade;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
    @NotBlank
    private String regiao;

    @Column(name = "data_inclusao")
    private LocalDateTime dataInclusao;
}
