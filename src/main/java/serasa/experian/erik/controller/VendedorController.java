package serasa.experian.erik.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serasa.experian.erik.dto.VendedorDTO;
import serasa.experian.erik.model.Vendedor;
import serasa.experian.erik.respository.VendedorRepository;
import serasa.experian.erik.service.VendedorService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api( value = "VendedorController")
@RestController
@RequestMapping("/vendedor")
@RequiredArgsConstructor
public class VendedorController {
    private final VendedorService vendedorService;
    private final VendedorRepository vendedorRepository;

    @ApiOperation(value = "Adicionar um vendedor.")
    @PostMapping
    public ResponseEntity<?> incluirVendedor(@Valid @RequestBody Vendedor vendedor) {
        try {
            Vendedor vendedorIncluido = vendedorService.incluirVendedor(vendedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(vendedorIncluido);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Buscar Vendedor pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVendedorPorId(@RequestParam int id) {
        try {
            Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);
            if (vendedorOptional.isPresent()) {
                Vendedor vendedor = vendedorOptional.get();
                VendedorDTO vendedorDTO = VendedorDTO.toDTO(vendedor);
                return ResponseEntity.ok(vendedorDTO);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao buscar o vendedor: " + e.getMessage());
        }
    }

    @ApiOperation(value = "Listar os Vendedores.")
    @GetMapping
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
