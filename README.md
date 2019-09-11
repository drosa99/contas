**Autora Daniela Amaral da Rosa 10/09/2019**

Enunciado:
Efetuar o desenvolvimento de uma api REST em Java com o seguinte endpoint:
    Conta
        {
            id : String gerado automaticamente ao criar
            numero: String -> Somente digitos e no máximo 6 caracteres 
            agencia: String -> Somente digitos e no máximo 4 caracteres
            cpf: String -> Somente digitos e no máximo 11 caracteres
            status: Boolean -> Default ativo
            dataCriacao -> LocalDate
            dataAtualizacao -> LocalDate
        }
        GET /conta
            Lista todas contas cadastradas -> Possivel paginar
        GET /conta/{id}
            Retorna uma conta especifica pelo ID
        POST /conta
            Salva uma conta na base de dados
        PUT /conta/{id}
            Atualiza um conta
        DELETE /conta/{id}
            Faz o delete lógico da conta (status = false)


Para o desenvolvimento desta foi utilizado: 
- Springboot;
- A biblioteca Lombok para gerar Getters, Setters e Builders;
- Banco de dados H2, o qual é um banco de dados Open Source que funciona em memória com um console acessível pelo browser dentro do contexto da aplicação. Como ele funciona em memória todo seu armazenamento é volátil, ou seja, a cada sobe e desce da aplicação ele será reconstruído. Seu intuíto é ser um banco de configuração rápida e fácil, visando favorecer a produtividade;
- Foi criada uma classe de Exceção _ExpectedException_ para lançar os erros da aplicação;
- Um arquivo de mensagens dentro da pasta resources, contendo todas as mensagens de erro e de validações utilizadas na aplicação;
- Foi configurado um Swagger, no qual todas as requisições estão funcionando ao subir a aplicação em: localhost:8080 ;
- Foram feitos testes unitários utilizando jUnit;
- Maven;
- O desenvolvimento foi feito utilizando a IDE Intellij;

