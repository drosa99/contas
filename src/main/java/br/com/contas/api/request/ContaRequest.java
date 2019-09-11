package br.com.contas.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
public class ContaRequest {
    @ApiModelProperty(value = "Número da conta.")
    @Size(max = 6, message = "numero.size")
    @NotNull(message = "numero.empty")
    private String numero;
    @ApiModelProperty(value = "Agência da conta.")
    @Size(max = 4, message = "agencia.size")
    @NotNull(message = "agencia.empty")
    private String agencia;
    @ApiModelProperty(value = "CPF do usuário da conta")
    @Size(max = 11, message = "cpf.size")
    @NotNull(message = "cpf.empty")
    private String cpf;
}
