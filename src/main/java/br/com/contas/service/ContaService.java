package br.com.contas.service;

import br.com.contas.api.request.ContaRequest;
import br.com.contas.api.response.ContaResponse;
import br.com.contas.dto.PageOut;
import br.com.contas.entity.ContaEntity;
import br.com.contas.exception.ExpectedException;
import br.com.contas.mapper.ContaMapper;
import br.com.contas.repository.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {
    private ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    /* Metodo que cadastra uma conta, recebe na requisicao apenas
      numero, agencia e cpf, os demais atributos sao setados neste metodo
    */
    @Transactional(rollbackFor = Exception.class)
    public ContaEntity cadastroConta(ContaRequest contaRequest) {
        ContaEntity contaEntity = ContaMapper.mapperRequestParaEntity(contaRequest);
        contaEntity.setDataCriacao(LocalDate.now());
        contaEntity.setDataAtualizao(LocalDate.now());
        contaEntity.setStatus(true);
        return contaRepository.save(contaEntity);
    }

    /* Metodo que lista as contas cadastradas de forma paginada, para isto foi criada uma DTO propria, PageOut
    */
    public PageOut<ContaResponse> listagemPaginadaContas(Pageable pageable) {
        Page<ContaEntity> contaEntityPage = contaRepository.findAll(pageable);
        PageOut<ContaResponse> contaResponsePageOut = new PageOut<>();
        contaResponsePageOut.setTotalElements(contaEntityPage.getTotalElements());
        List<ContaResponse> contaResponses = contaEntityPage.stream()
                .map(ContaMapper::mapperEntityParaResponse).collect(Collectors.toList());
        contaResponsePageOut.setElements(contaResponses);
        contaResponsePageOut.setTotalPages((long) contaEntityPage.getTotalPages());
        return contaResponsePageOut;
    }

    /* Metodo que busca uma conta passando o seu id, caso esta conta nao esteja cadastrada, e lancada uma excecao 417 com mensagem especifica
    * */
    public ContaResponse buscarContaPorId(String id) {
        ContaEntity contaEntity = contaRepository.findById(id).orElseThrow(() -> new ExpectedException("error.contaNotFound"));
        return ContaMapper.mapperEntityParaResponse(contaEntity);
    }

    /* Metodo que edita a conta, recebe o mesmo request body do cadastro
    * */
    @Transactional(rollbackFor = Exception.class)
    public void editarConta(String id, ContaRequest contaRequest) {
        ContaEntity contaBanco = contaRepository.findById(id).orElseThrow(() -> new ExpectedException("error.contaNotFound"));
        ContaEntity contaEntity = ContaMapper.mapperRequestParaEntity(contaRequest);
        preparaEntityParaEditar(contaBanco, contaEntity);
        contaRepository.save(contaEntity);
    }

    /* Metodo que desativa a conta logicamente, ele apenas muda o status para false, nao e realizado delete no banco de dados
    * */
    public void desativarConta(String id) {
        ContaEntity contaBanco = contaRepository.findById(id).orElseThrow(() -> new ExpectedException("error.contaNotFound"));
        if (!contaBanco.getStatus()) {
            throw new ExpectedException("error.contaDisabled");
        }
        contaBanco.setStatus(false);
        contaRepository.save(contaBanco);
    }

    /* Metodo auxiliar chamado na edicao para preparar o objeto para ser salvo
    * */
    private void preparaEntityParaEditar(ContaEntity contaBanco, ContaEntity contaEntity) {
        contaEntity.setDataAtualizao(LocalDate.now());
        contaEntity.setDataCriacao(contaBanco.getDataCriacao());
        contaEntity.setId(contaBanco.getId());
        contaEntity.setStatus(contaBanco.getStatus());
    }
}
