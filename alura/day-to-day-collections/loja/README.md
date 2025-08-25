# Day-to-Day Collections - Loja

## Objetivo de Estudo
Este projeto é baseado no curso da Alura "Clojure: Collections no dia a dia", focando na manipulação prática de coleções em Clojure através do desenvolvimento de um sistema de loja.

## O que este projeto representa
Um sistema de exemplo para gerenciar pedidos de uma loja virtual, demonstrando como trabalhar efetivamente com as estruturas de dados do Clojure no contexto de uma aplicação real.

### Estrutura do Projeto

**db.clj**
- Base de dados simulada com pedidos
- Estruturas de dados aninhadas (maps dentro de maps)
- Modelo de dados realista para um e-commerce

**aula1.clj** - Iteração e Recursão
- Implementação manual da função `map`
- Conceitos de recursão e tail recursion
- Uso de `recur` para otimização

**aula2.clj** - Implementação de Funções de Contagem
- Função `conta` implementada do zero
- Sobrecarga de funções (múltiplas aridades)
- Uso de `loop` e `recur`

**aula3.clj** - Agrupamento e Análise de Dados
- Uso de `group-by` para agrupar pedidos por usuário
- Threading macros (`->>`) para composição de operações
- Transformação de dados e análises estatísticas

**logic.clj** - Lógica de Negócio
- Cálculos de totais de pedidos
- Funções para análise de gastos por usuário
- Separação de responsabilidades

## Conceitos Praticados

### Coleções e Estruturas de Dados
- Vetores, maps e keywords
- Acesso e manipulação de estruturas aninhadas
- Imutabilidade e persistência estrutural

### Funções de Alta Ordem
- `map`, `filter`, `reduce`
- `group-by` para agrupamento
- Composição de funções

### Threading e Composição
- Threading macros (`->`, `->>`)
- Pipeline de transformações de dados
- Código mais legível e funcional

### Recursão e Performance
- Tail recursion com `recur`
- `loop` para iterações locais
- Otimização de stack overflow

## Cenário Prático
O projeto simula um sistema de loja onde é possível:
- Calcular totais de pedidos
- Agrupar pedidos por usuário
- Gerar relatórios de gastos
- Analisar padrões de compra
