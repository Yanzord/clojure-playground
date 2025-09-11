# Programação Funcional

Curso introdutório sobre programação funcional em Clojure cobrindo conceitos fundamentais do paradigma através de exemplos práticos de e-commerce com sistema de descontos e processamento de dados.

## Objetivo

Ensinar os princípios centrais da programação funcional em Clojure através de progressão didática: desde funções básicas até composição complexa, usando cenários práticos de negócio.

## Estrutura das Aulas

### aula2.clj
Fundamentos de funções e controle de fluxo.
- Definição de funções com `defn` e documentação
- Estruturas condicionais com `if` e `let`
- Binding de variáveis locais e escopo
- Implementação de lógica de desconto simples

### aula3.clj  
Predicados e funções de alta ordem.
- Funções predicado com convenção de nomenclatura (terminadas em `?`)
- Higher-order functions: funções como parâmetros
- Separação de responsabilidades entre lógica e regras de negócio
- Funções anônimas: sintaxe `fn` e `#()`
- Definição de funções com `def`

### aula4.clj
Operações com vetores e transformações de coleções.
- Acesso a elementos: `get`, indexação, valores padrão
- Modificação imutável: `conj`, `update`
- Funções transformadoras: `map`, `filter`, `reduce`
- Pipeline de transformações de dados
- Processamento de listas de preços

### aula5.clj
Estruturas associativas e navegação de dados.
- Maps: criação, acesso e manipulação (`assoc`, `dissoc`, `update`)
- Keywords vs strings como chaves
- Acesso via função vs keyword
- Estruturas aninhadas e `update-in`
- Threading first (`->`) para navegação

### aula6.clj
Composição avançada e threading.
- Destrutruração de maps para extração de valores
- Threading last (`->>`) para pipelines de transformação
- Função `comp` para composição de funções
- Processamento de estruturas complexas
- Cálculos agregados e análise de dados

## Conceitos Abordados

### Paradigma Funcional
- Imutabilidade de estruturas de dados
- Funções puras sem efeitos colaterais  
- Higher-order functions como abstração
- Composição de funções simples em operações complexas

### Estruturas de Dados
- Vetores como coleções ordenadas e indexadas
- Maps como associações chave-valor eficientes
- Keywords como identificadores otimizados
- Acesso uniforme e navegação de estruturas

### Threading e Composição
- Threading first (`->`) para navegação de estruturas
- Threading last (`->>`) para transformação de coleções
- Pipelines legíveis e funcionais
- Composição declarativa de operações

## Cenários Práticos

- Sistema de cálculo de descontos com regras de negócio
- Processamento de listas de preços e produtos
- Transformação de dados de pedidos e-commerce
- Análise e agregação de informações de clientes
