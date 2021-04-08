# Projeto de autenticação

O projeto foi feito com base na seguinte especificação:

## Descrição

Considere uma senha sendo válida quando a mesma possuir as seguintes definições:

- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial
  - Considere como especial os seguintes caracteres: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto

Exemplo:  

```c#
IsValid("") // false  
IsValid("aa") // false  
IsValid("ab") // false  
IsValid("AAAbbbCc") // false  
IsValid("AbTp9!foo") // false  
IsValid("AbTp9!foA") // false
IsValid("AbTp9 fok") // false
IsValid("AbTp9!fok") // true
```

> **_Nota:_**  Espaços em branco não devem ser considerados como caracteres válidos.

### Notas técnicas

O projeto foi feito utilizando o java, spring-boot, jax-rs e swagger (Para documentar a api).

#### Decisões do código

Visando deixar o código mais fácil de ler e desenvolver foi adotado o uso de regex. Foi assumido como premissa que o código faz parte de um módulo de autenticação e api é versionada. Com isso se fosse preciso adicionar outras regras seria facilmente alterável sem comprometer quem esteja consumindo desta api.

#### Testes

Para os testes foi utilizado o junit, contento testes unitários e um teste de integração na api. A cobertura do código ficou acima dos 75%, não sendo testado apenas a classe de boot do spring (AuthApplication) e de configuração do swagger (SwaggerConfig).

## Rodar o projeto

Para rodar o projeto é necessário rodar o comando:

	./mvnw spring-boot:run

### Url para chamada da api

Para utilizar a api pode ser usado o swagger na url:

	http://localhost:8080/swagger-ui/index.html#/auth-controller/authUsingPOST