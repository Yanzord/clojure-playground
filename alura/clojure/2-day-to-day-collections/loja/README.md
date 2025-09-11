# Coleções no Dia a Dia

Curso prático sobre manipulação de coleções em Clojure através de sistema de loja virtual, demonstrando técnicas de processamento de dados, recursão, agrupamento e análise estatística.

## Objetivo

Demonstrar o uso efetivo das estruturas de dados do Clojure em cenários reais de aplicação, cobrindo desde implementação manual de funções básicas até pipelines complexos de transformação de dados.

## Estrutura das Aulas

### aula1.clj
Iteração e recursão fundamental.
- Implementação manual da função `map` do zero
- Conceitos de recursão e stack overflow
- Uso de `recur` para tail recursion otimizada
- Comparação entre implementação manual e função nativa

### aula2.clj
Implementação de funções de contagem.
- Função `conta` (equivalente a `count`) implementada manualmente
- Sobrecarga de funções com múltiplas aridades
- Uso de `loop` e `recur` para iterações locais
- Otimizações de performance para coleções grandes

### aula3.clj
Agrupamento e análise estatística de dados.
- Uso de `group-by` para agrupamento de pedidos por usuário
- Threading macros (`->>`) para composição de operações
- Transformação de dados estruturados
- Geração de relatórios e análises

### aula4.clj
Processamento avançado de coleções.
- Combinação de múltiplas operações de transformação
- Análise de padrões de compra e comportamento
- Cálculos agregados e estatísticas derivadas

### aula5.clj
Pipelines complexos de dados.
- Composição de operações para análises sofisticadas
- Tratamento de dados aninhados e estruturas complexas
- Otimização de performance em processamento de lotes

## Módulos de Apoio

### db.clj
Base de dados simulada para testes.
- Estrutura de pedidos com maps aninhados
- Modelo de dados realista para e-commerce
- Dados de teste para validação de funções

### logic.clj  
Lógica de negócio e cálculos.
- Funções para cálculo de totais de pedidos
- Análise de gastos por usuário
- Separação clara de responsabilidades

## Conceitos Abordados

### Recursão e Iteração
- Tail recursion com `recur` para otimização
- `loop` para iterações locais controladas
- Implementação manual de funções core
- Prevenção de stack overflow

### Transformação de Coleções
- Funções de alta ordem: `map`, `filter`, `reduce`
- `group-by` para agrupamento e categorização
- Composição de operações via threading macros
- Pipelines de transformação de dados

### Estruturas de Dados
- Manipulação de maps aninhados
- Acesso e navegação de estruturas complexas
- Imutabilidade e persistência estrutural
- Keywords como chaves eficientes

## Cenários Práticos

- Processamento de pedidos de loja virtual
- Análise de comportamento de usuários  
- Geração de relatórios de vendas
- Cálculos de métricas de negócio
- Agrupamento e categorização de dados
