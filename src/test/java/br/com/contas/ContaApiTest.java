package br.com.contas;

import br.com.contas.api.ContaApi;
import br.com.contas.api.request.ContaRequest;
import br.com.contas.exception.config.GlobalExceptionHandler;
import br.com.contas.service.ContaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ContaApi.class, secure = false)
public class ContaApiTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContaService contaService;
    @MockBean
    private GlobalExceptionHandler globalExceptionHandler;
    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void cadastraContaTestApi() throws Exception {
        ContaRequest contaRequest = ContaRequest.builder().agencia("0101").cpf("02712867094").numero("02022").build();
        String jsonInString = objectMapper.writerWithView(ContaRequest.class).writeValueAsString(contaRequest);
        doNothing().when(contaService).cadastroConta(any());
        mockMvc.perform(
                post("/v1/conta")
                        .content(jsonInString)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
