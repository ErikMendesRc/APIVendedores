package serasa.experian.erik.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import serasa.experian.erik.dto.VendedorDTO;
import serasa.experian.erik.model.Vendedor;
import serasa.experian.erik.respository.VendedorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VendedorServiceTest {

    private VendedorService vendedorService;

    @Mock
    private VendedorRepository vendedorRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        vendedorService = new VendedorService(vendedorRepository);
    }

    @Test
    public void testIncluirVendedor() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("João");

        when(vendedorRepository.save(vendedor)).thenReturn(vendedor);

        Vendedor resultado = vendedorService.incluirVendedor(vendedor);

        assertNotNull(resultado);
        assertEquals(vendedor, resultado);
        assertNotNull(resultado.getDataInclusao());
    }

    @Test
    public void testBuscarVendedorPorId_Existente() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(1);
        vendedor.setNome("João");

        when(vendedorRepository.findById(1)).thenReturn(Optional.of(vendedor));

        VendedorDTO resultado = vendedorService.buscarVendedorPorId(1);

        assertNotNull(resultado);
        assertEquals(vendedor.getNome(), resultado.getNome());
        assertEquals(vendedor.getDataInclusao(), resultado.getDataInclusao());
    }

    @Test
    public void testBuscarVendedorPorId_Inexistente() {
        when(vendedorRepository.findById(1)).thenReturn(Optional.empty());
        VendedorDTO resultado = vendedorService.buscarVendedorPorId(1);

        assertNull(resultado);
    }

    @Test
    public void testListarVendedores() {
        Vendedor vendedor1 = new Vendedor();
        vendedor1.setNome("João");

        Vendedor vendedor2 = new Vendedor();
        vendedor2.setNome("Maria");

        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        when(vendedorRepository.findAll()).thenReturn(vendedores);

        List<VendedorDTO> resultados = vendedorService.listarVendedores();

        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        assertEquals(vendedor1.getNome(), resultados.get(0).getNome());
        assertEquals(vendedor2.getNome(), resultados.get(1).getNome());
    }
}
