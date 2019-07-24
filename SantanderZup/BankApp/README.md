## Challenge Santander

## Features

- Login: O usuário realiza o login onde é validada a formatação de "user" e "password", em caso de sucesso é realizada requisição para um endpoint de login, caso usuário seja valido é retornado os dados de sua conta e em seguida é direcionado para tela de Statements. 
- Statements: Exibe os dados da conta do usuário que foram retornados do login e em uma nova requisição traz os últimos lançamentos, que são exibidos em uma lista.

## Instalação
Para compilar o APK(debug) e instalá-lo imediatamente em um emulador em execução ou dispositivo conectado, abra uma linha de comando e navegue para a raiz do diretório do projeto — no Android Studio, selecione View > Tool Windows > Terminal. 
Execute a seguinte comando:
```sh
$ ./gradlew installDebug
```
## Execução dos testes
```sh
$ ./gradlew test
```
## Execução do Detekt (Static code analysis for Kotlin)
```sh
$ ./gradlew detekt
```
Obs: os relatórios em detekt.html e detekt.xml são gerados no diretório root.

## Arquitetura
Foi utilizado MVVM com Android Architecture Components (LiveData e ViewModel)

## Bibliotecas

| Plugin | Objetivo |
| ------ | ------ |
| Retrofit | Requisições da Api |
| Gson | Deserializar retorno da API de formato Json |
| Mockito-Kotlin | Mock para realização de testes unitários locais  |
| Mockito-Inline | Resolver problema com final classes em testes no Kotlin  |
| Detekt | análise estática de código  |
| kotlin Coroutines Adapter | Uso retrofit com coroutines  |
| Koin | Injeção de dependência |
| Kotter Knife | View binding for Kotlin |

# Author
 Wagner Messias - wmessiascavalcanti@gmail.com 
 
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Wagner%20Messias-blue.svg)](https://www.linkedin.com/in/wagnermessias/)
