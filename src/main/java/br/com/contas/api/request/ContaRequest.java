package br.com.contas.api.request;

import br.com.contas.util.Regex;
import io.swagger.annotations.ApiModelProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
public class ContaRequest {
    @ApiModelProperty(value = "Número da conta.")
    @Size(max = 6, message = "numero.size")
    @NotNull(message = "numero.empty")
    @Pattern(regexp = Regex.SOMENTE_NUMERO, message = "numero.invalid")
    private String numero;
    @ApiModelProperty(value = "Agência da conta.")
    @Size(max = 4, message = "agencia.size")
    @NotNull(message = "agencia.empty")
    @Pattern(regexp = Regex.SOMENTE_NUMERO, message = "agencia.invalid")
    private String agencia;
    @ApiModelProperty(value = "CPF do usuário da conta")
    @Size(max = 11, message = "cpf.size")
    @NotNull(message = "cpf.empty")
    @CPF(message = "cpf.invalid")
    private String cpf;
}
