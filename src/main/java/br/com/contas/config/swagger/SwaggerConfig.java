package br.com.contas.config.swagger;

import br.com.contas.exception.SimpleError;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
    private TypeResolver typeResolver;

    @Autowired(required = false)
    private MessageSource messageSource;

    @Bean
    public Docket api() {
        List<ResponseMessage> defaultResponses = new ArrayList<ResponseMessage>() {{
            add(buildResponseMessage(HttpStatus.EXPECTATION_FAILED, translate("error.417")));
            add(buildResponseMessage(HttpStatus.BAD_REQUEST, translate("error.400")));
            add(buildResponseMessage(HttpStatus.UNAUTHORIZED, translate("error.401")));
            add(buildResponseMessage(HttpStatus.FORBIDDEN, translate("error.403")));
            add(buildResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, translate("error.500")));
        }};
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalTime.class, String.class)
                .additionalModels(typeResolver.resolve(SimpleError.class))
                .globalResponseMessage(RequestMethod.POST, defaultResponses)
                .globalResponseMessage(RequestMethod.DELETE, defaultResponses)
                .globalResponseMessage(RequestMethod.GET, defaultResponses)
                .globalResponseMessage(RequestMethod.PUT, defaultResponses)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    private ResponseMessage buildResponseMessage(HttpStatus status, String message) {
        return new ResponseMessageBuilder()
                .code(status.value())
                .message(message)
                .responseModel(new ModelRef(SimpleError.class.getSimpleName()))
                .build();
    }

    private String translate(String message) {
        return (messageSource == null) ? message : messageSource.getMessage(message, null, Locale.ENGLISH);
    }
}