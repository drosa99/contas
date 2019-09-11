package br.com.contas.api;

import br.com.contas.api.request.ContaRequest;
import br.com.contas.api.response.ContaResponse;
import br.com.contas.dto.PageOut;
import br.com.contas.service.ContaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/conta")
public class ContaApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContaApi.class);
    private ContaService contaService;

    public ContaApi(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("")
    @ApiOperation(value = "Cadastra uma conta.")
    public ResponseEntity cadastrarConta(@Valid @RequestBody() ContaRequest contaRequest) {
        LOGGER.info("Iniciado cadastro de nova conta.");
        contaService.cadastroConta(contaRequest);
        LOGGER.info("Finalizada cadastro de nova conta.");
        return ResponseEntity.ok(Void.class);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Edita uma conta")
    public ResponseEntity editarConta(@PathVariable String id, @Valid @RequestBody() ContaRequest contaRequest) {
        LOGGER.info("Iniciada edicao de conta.");
        contaService.editarConta(id, contaRequest);
        LOGGER.info("Finalizada edicao de conta.");
        return ResponseEntity.ok(Void.class);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar detalhe de uma conta")
    public ResponseEntity<ContaResponse> buscarContaPorId(@PathVariable String id) {
        LOGGER.info("Iniciada busca de conta.");
        ContaResponse contaResponse = contaService.buscarContaPorId(id);
        LOGGER.info("Finalizada busca de conta.");
        return ResponseEntity.ok(contaResponse);
    }

    @GetMapping("")
    @ApiOperation(value = "Buscar lista de contas paginada")
    public ResponseEntity<PageOut<ContaResponse>> buscarContasPaginado(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        LOGGER.info("Iniciada listagem de contas.");
        PageRequest paginado = PageRequest.of(page - 1, size);
        PageOut<ContaResponse> contasResponses = contaService.listagemPaginadaContas(paginado);
        LOGGER.info("Finalizada listagem de contas.");
        return ResponseEntity.ok(contasResponses);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Desativar uma conta")
    public ResponseEntity desativarConta(@PathVariable String id) {
        LOGGER.info("Iniciada desativação de conta.");
        contaService.desativarConta(id);
        LOGGER.info("Finalizada desativação de conta.");
        return ResponseEntity.ok(Void.class);
    }
}
