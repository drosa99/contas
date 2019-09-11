package br.com.contas.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ContaResponse {
    @ApiModelProperty(value = "Id da conta.")
    private String id;
    @ApiModelProperty(value = "Número da conta.")
    private String numero;
    @ApiModelProperty(value = "Agência da conta.")
    private String agencia;
    @ApiModelProperty(value = "CPF do usuário da conta")
    private String cpf;
    @ApiModelProperty(value = "Status da conta: 0 - desativada; 1 - ativa")
    private Boolean status;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtualizao;
}
