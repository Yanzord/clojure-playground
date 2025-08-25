# Functional Programming - Conceitos Fundamentais

## Objetivo de Estudo
Este projeto é baseado no curso da Alura sobre programação funcional em Clojure, cobrindo os princípios fundamentais do paradigma funcional através de exemplos práticos.

## O que este projeto representa
Uma progressão didática através dos conceitos centrais da programação funcional, desde funções simples até composição complexa, usando cenários práticos de e-commerce.

### Estrutura das Aulas

**aula2.clj** - Funções e Condicionais
- Definição de funções com documentação
- Estruturas condicionais (`if`, `let`)
- Cálculo de descontos com regras de negócio
- Binding de variáveis locais

**aula3.clj** - Predicados e Higher-Order Functions
- Funções predicado (terminadas em `?`)
- Separação de responsabilidades (lógica vs regra)
- Funções como parâmetros (higher-order functions)
- Funções anônimas: `fn` e sintaxe `#()`
- Definição de funções com `def`

**aula4.clj** - Coleções e Transformações
- Vetores: acesso, adição e atualização (`get`, `conj`, `update`)
- Funções de alta ordem: `map`, `filter`, `reduce`
- Pipeline de transformações de dados
- Valores padrão e tratamento de índices inexistentes

**aula5.clj** - Maps e Estruturas Associativas
- Maps com strings vs keywords
- Manipulação: `assoc`, `dissoc`, `update`
- Acesso a dados: sintaxe de função vs keyword
- Estruturas aninhadas e `update-in`
- Threading first (`->`) para navegação em estruturas

**aula6.clj** - Composição e Threading Avançado
- Decomposição de maps com destrutruração
- Threading last (`->>`) para pipelines de transformação
- Função `comp` para composição de funções
- Processamento de coleções complexas
- Cálculos agregados em estruturas aninhadas

## Conceitos Fundamentais Praticados

### Paradigma Funcional
- **Imutabilidade**: Dados não mudam, criam-se novas versões
- **Funções puras**: Sem efeitos colaterais
- **Higher-order functions**: Funções que recebem/retornam funções
- **Composição**: Combinação de funções simples em operações complexas

### Estruturas de Dados
- **Vetores**: Coleções ordenadas e indexadas
- **Maps**: Associações chave-valor (dictionaries)
- **Keywords**: Identificadores eficientes para chaves
- **Destrutruração**: Extração de valores de estruturas

### Threading Macros
- **`->`**: Thread-first para navegação em estruturas
- **`->>`**: Thread-last para transformação de coleções
- Pipeline de operações legível e funcional

### Transformações de Coleções
- **`map`**: Transformação elemento a elemento
- **`filter`**: Seleção baseada em predicados
- **`reduce`**: Agregação de coleções em valores únicos
- **Composição**: Combinação de operações para análises complexas

## Cenários Práticos
- Sistema de descontos dinâmicos
- Gerenciamento de estoque com maps
- Processamento de pedidos e-commerce
- Cálculos de totais e agregações
- Análise de dados de clientes

## Progressão Pedagógica
O curso evolui de conceitos simples (if/else) para composições sofisticadas, sempre mantendo exemplos práticos e próximos ao mundo real do desenvolvimento de software.
