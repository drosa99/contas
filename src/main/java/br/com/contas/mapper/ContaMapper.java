package br.com.contas.mapper;

import br.com.contas.api.request.ContaRequest;
import br.com.contas.api.response.ContaResponse;
import br.com.contas.entity.ContaEntity;

public class ContaMapper {
    public static ContaEntity mapperRequestParaEntity(ContaRequest contaRequest) {
        return ContaEntity.builder()
                .numero(contaRequest.getNumero())
                .agencia(contaRequest.getAgencia())
                .cpf(contaRequest.getCpf())
                .build();
    }

    public static ContaResponse mapperEntityParaResponse(ContaEntity contaEntity) {
        return ContaResponse.builder()
                .id(contaEntity.getId())
                .agencia(contaEntity.getAgencia())
                .cpf(contaEntity.getCpf())
                .numero(contaEntity.getNumero())
                .dataAtualizao(contaEntity.getDataAtualizao())
                .dataCriacao(contaEntity.getDataCriacao())
                .build();
    }
}
