package serasa.experian.erik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serasa.experian.erik.model.Vendedor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorDTO {

    private String nome;
    private String telefone;
    private int idade;
    private String cidade;
    private String estado;
    private String regiao;
    private LocalDateTime dataInclusao;
    private List<String> estados;

    public static VendedorDTO toDTO(Vendedor vendedor) {
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome(vendedor.getNome());
        vendedorDTO.setTelefone(vendedor.getTelefone());
        vendedorDTO.setIdade(vendedor.getIdade());
        vendedorDTO.setCidade(vendedor.getCidade());
        vendedorDTO.setEstado(vendedor.getEstado());
        vendedorDTO.setDataInclusao(vendedor.getDataInclusao());

        return vendedorDTO;
    }
}

