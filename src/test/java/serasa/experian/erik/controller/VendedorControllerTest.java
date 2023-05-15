package serasa.experian.erik.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import serasa.experian.erik.dto.VendedorDTO;
import serasa.experian.erik.model.Vendedor;
import serasa.experian.erik.respository.VendedorRepository;
import serasa.experian.erik.service.VendedorService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class VendedorControllerTest {

    private VendedorService vendedorService;
    private VendedorRepository vendedorRepository;
    private VendedorController vendedorController;

    @BeforeEach
    public void setUp() {
        vendedorService = mock(VendedorService.class);
        vendedorRepository = mock(VendedorRepository.class);
        vendedorController = new VendedorController(vendedorService, vendedorRepository);
    }

    @Test
    public void testIncluirVendedor_Success() {
        Vendedor vendedor = new Vendedor();
        Vendedor vendedorIncluido = new Vendedor();
        when(vendedorService.incluirVendedor(vendedor)).thenReturn(vendedorIncluido);

        ResponseEntity<?> response = vendedorController.incluirVendedor(vendedor);

        verify(vendedorService, times(1)).incluirVendedor(vendedor);
        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertSame(vendedorIncluido, response.getBody());
    }

    @Test
    public void testBuscarVendedorPorId_Existente() {
        int vendedorId = 1;
        Vendedor vendedor = new Vendedor();
        vendedor.setId(vendedorId);
        vendedor.setNome("João");
        vendedor.setTelefone("123456789");
        vendedor.setIdade(30);
        vendedor.setCidade("São Paulo");
        vendedor.setEstado("SP");
        LocalDateTime dataInclusao = LocalDateTime.now();
        vendedor.setDataInclusao(dataInclusao);
        Optional<Vendedor> vendedorOptional = Optional.of(vendedor);
        when(vendedorRepository.findById(vendedorId)).thenReturn(vendedorOptional);

        ResponseEntity<?> response = vendedorController.buscarVendedorPorId(vendedorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        VendedorDTO vendedorDTO = (VendedorDTO) response.getBody();
        assertNotNull(vendedorDTO);
        assertEquals(vendedor.getNome(), vendedorDTO.getNome());
        assertEquals(vendedor.getTelefone(), vendedorDTO.getTelefone());
        assertEquals(vendedor.getIdade(), vendedorDTO.getIdade());
        assertEquals(vendedor.getCidade(), vendedorDTO.getCidade());
        assertEquals(vendedor.getEstado(), vendedorDTO.getEstado());
        assertEquals(dataInclusao, vendedorDTO.getDataInclusao());
        verify(vendedorRepository, times(1)).findById(vendedorId);
    }

    @Test
    public void testBuscarVendedorPorId_NotExists() {
        int id = 1;
        Optional<Vendedor> vendedorOptional = Optional.empty();
        when(vendedorRepository.findById(id)).thenReturn(vendedorOptional);

        ResponseEntity<?> response = vendedorController.buscarVendedorPorId(id);

        verify(vendedorRepository, times(1)).findById(id);
        assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testBuscarVendedorPorId_Excecao() {
        int vendedorId = 1;
        String errorMessage = "Erro ao buscar o vendedor";
        when(vendedorRepository.findById(vendedorId)).thenThrow(new RuntimeException(errorMessage));

        ResponseEntity<?> response = vendedorController.buscarVendedorPorId(vendedorId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro ao buscar o vendedor: " + errorMessage, response.getBody());
        verify(vendedorRepository, times(1)).findById(vendedorId);
    }

    @Test
    public void testListarVendedores() {
        Vendedor vendedor1 = new Vendedor();
        vendedor1.setId(1);
        vendedor1.setNome("João");
        vendedor1.setTelefone("123456789");
        vendedor1.setIdade(30);
        vendedor1.setCidade("São Paulo");
        vendedor1.setEstado("SP");

        Vendedor vendedor2 = new Vendedor();
        vendedor2.setId(2);
        vendedor2.setNome("Maria");
        vendedor2.setTelefone("987654321");
        vendedor2.setIdade(35);
        vendedor2.setCidade("Rio de Janeiro");
        vendedor2.setEstado("RJ");

        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        when(vendedorRepository.findAll()).thenReturn(vendedores);

        List<VendedorDTO> vendedoresDTO = vendedorController.listarVendedores();

        assertEquals(vendedores.size(), vendedoresDTO.size());

        VendedorDTO vendedorDTO1 = vendedoresDTO.get(0);
        assertEquals(vendedor1.getNome(), vendedorDTO1.getNome());
        assertEquals(vendedor1.getTelefone(), vendedorDTO1.getTelefone());
        assertEquals(vendedor1.getIdade(), vendedorDTO1.getIdade());
        assertEquals(vendedor1.getCidade(), vendedorDTO1.getCidade());
        assertEquals(vendedor1.getEstado(), vendedorDTO1.getEstado());

        VendedorDTO vendedorDTO2 = vendedoresDTO.get(1);
        assertEquals(vendedor2.getNome(), vendedorDTO2.getNome());
        assertEquals(vendedor2.getTelefone(), vendedorDTO2.getTelefone());
        assertEquals(vendedor2.getIdade(), vendedorDTO2.getIdade());
        assertEquals(vendedor2.getCidade(), vendedorDTO2.getCidade());
        assertEquals(vendedor2.getEstado(), vendedorDTO2.getEstado());
    }
}
