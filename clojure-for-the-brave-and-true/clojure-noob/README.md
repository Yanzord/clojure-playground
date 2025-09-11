# Primeiro Projeto Clojure

Projeto inicial baseado no livro "Clojure for the Brave and True", demonstrando setup básico de aplicação Clojure com estrutura padrão Leiningen.

## Objetivo

Introdução ao ciclo de desenvolvimento Clojure através de projeto "Hello World" estruturado, cobrindo configuração de projeto, função main e execução de aplicações.

## Estrutura

### core.clj
Ponto de entrada da aplicação com função `-main`.
- Definição de namespace  
- Função main com parâmetros de linha de comando
- Output básico e estrutura de aplicação console
- Exemplo de documentação de função

## Conceitos Demonstrados

- Estrutura padrão de projeto Leiningen
- Namespace e organização de código
- Função `-main` como ponto de entrada
- Parâmetros de linha de comando
- Compilação e execução de aplicações Clojure

## Execução

```bash
lein run
# ou para gerar uberjar:
lein uberjar
java -jar target/uberjar/clojure-noob-0.1.0-standalone.jar
```
