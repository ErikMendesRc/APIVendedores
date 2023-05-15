package serasa.experian.erik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import serasa.experian.erik.dto.VendedorDTO;
import serasa.experian.erik.model.Vendedor;
import serasa.experian.erik.respository.VendedorRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendedorService {
    private final VendedorRepository vendedorRepository;

    public Vendedor incluirVendedor(@NotNull Vendedor vendedor) {
        vendedor.setDataInclusao(LocalDateTime.now());
        return vendedorRepository.save(vendedor);
    }

    public VendedorDTO buscarVendedorPorId(int id) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);

        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            VendedorDTO vendedorDTO = new VendedorDTO();
            vendedorDTO.setNome(vendedor.getNome());
            vendedorDTO.setDataInclusao(vendedor.getDataInclusao());

            return vendedorDTO;
        }

        return null;
    }

    public List<VendedorDTO> listarVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        List<VendedorDTO> vendedoresDTO = new ArrayList<>();

        for (Vendedor vendedor : vendedores) {
            VendedorDTO vendedorDTO = VendedorDTO.toDTO(vendedor);
            vendedoresDTO.add(vendedorDTO);
        }

        return vendedoresDTO;
    }
}