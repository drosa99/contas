package br.com.contas.service;

import br.com.contas.api.request.ContaRequest;
import br.com.contas.api.response.ContaResponse;
import br.com.contas.config.dto.PageOut;
import br.com.contas.entity.ContaEntity;
import br.com.contas.exception.ExpectedException;
import br.com.contas.mapper.ContaMapper;
import br.com.contas.repository.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {
    private ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void cadastroConta(ContaRequest contaRequest) {
        ContaEntity contaEntity = ContaMapper.mapperRequestParaEntity(contaRequest);
        contaEntity.setDataCriacao(LocalDate.now());
        contaEntity.setStatus(true); //melhorar isso aqui se der
        contaRepository.save(contaEntity);
    }

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

    public ContaResponse buscarContaPorId(String id) {
        ContaEntity contaEntity = contaRepository.findById(id).orElseThrow(() -> new ExpectedException("error.contaNotFound"));
        return ContaMapper.mapperEntityParaResponse(contaEntity);
    }

    public void editarConta(String id, ContaRequest contaRequest) {
        ContaEntity contaBanco = contaRepository.findById(id).orElseThrow(() -> new ExpectedException("error.contaNotFound"));
        ContaEntity contaEntity = ContaMapper.mapperRequestParaEntity(contaRequest);
        preparaEntityParaEditar(contaBanco, contaEntity);
        contaRepository.save(contaEntity);
    }

    public void desativarConta(String id) {
        ContaEntity contaBanco = contaRepository.findById(id).orElseThrow(() -> new ExpectedException("error.contaNotFound"));
        if (!contaBanco.getStatus()) {
            throw new ExpectedException("error.contaDisabled");
        }
        contaBanco.setStatus(false);
        contaRepository.save(contaBanco);
    }

    private void preparaEntityParaEditar(ContaEntity contaBanco, ContaEntity contaEntity) {
        contaEntity.setDataAtualizao(LocalDate.now());
        contaEntity.setDataCriacao(contaBanco.getDataCriacao());
        contaEntity.setId(contaBanco.getId());
        contaEntity.setStatus(contaBanco.getStatus());
    }

    //TODO: ver porque as mensagens no request nao estao linkand
    //TODO: agencia, numero tem que ser digitos
    //TODO: colocar validacao de cpf do stella
    //todo: tirar codigo de exceptions nao usado
}
