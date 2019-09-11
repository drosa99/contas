package br.com.contas;

import br.com.contas.api.request.ContaRequest;
import br.com.contas.api.response.ContaResponse;
import br.com.contas.dto.PageOut;
import br.com.contas.entity.ContaEntity;
import br.com.contas.exception.ExpectedException;
import br.com.contas.service.ContaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ServiceTest extends ApplicationTest{
    @Autowired
    ApplicationContext context;

    @Autowired
    ContaService contaService;

    /*
    * Anotacao @Transactional garante que o banco seja limpo apos cada teste rodar,
    * o que evita problemas de um teste inferir em outro ao rodar a classe inteira
    * */

    @Transactional
    @Test
    public void testaSalvarConta(){
        ContaRequest contaRequest = ContaRequest.builder()
                .agencia("0101")
                .numero("1111")
                .cpf("02712867092")
                .build();
        ContaEntity contaSalva = contaService.cadastroConta(contaRequest);
        assertThat(contaSalva.getAgencia()).isEqualTo("0101");
        assertThat(contaSalva.getDataCriacao()).isEqualTo(LocalDate.now());
        assertThat(contaSalva.getStatus()).isEqualTo(true);
    }

    @Transactional
    @Test
    public void testaListarContaPaginado(){
        ContaRequest contaRequest = ContaRequest.builder()
                .agencia("0101")
                .numero("1111")
                .cpf("02712867092")
                .build();
        contaService.cadastroConta(contaRequest);
        PageOut<ContaResponse> contaResponsePageOut = contaService.listagemPaginadaContas(PageRequest.of(0, 10));
        assertThat(contaResponsePageOut.getTotalElements()).isEqualTo(1);
    }

    //Este teste esta buscando uma conta que nao existe, entao espera que seja lancada uma excecao
    @Transactional
    @Test(expected = ExpectedException.class)
    public void testaExcecaoAoBuscarContaNaoExistente(){
        contaService.buscarContaPorId("aaaa");
    }

    @Transactional
    @Test
    public void testaDesativaContaMudaStatusParaFalse(){
        ContaRequest contaRequest = ContaRequest.builder()
                .agencia("0101")
                .numero("1111")
                .cpf("02712867092")
                .build();
        ContaEntity contaSalva = contaService.cadastroConta(contaRequest);
        contaService.desativarConta(contaSalva.getId());
        assertThat(contaSalva.getStatus()).isEqualTo(false);
    }
}
