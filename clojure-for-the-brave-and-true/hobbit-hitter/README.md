# Hobbit Hitter

Projeto de modelagem de dados baseado no livro "Clojure for the Brave and True", demonstrando representação de estruturas complexas usando maps e vectors para modelar anatomia de hobbit.

## Objetivo

Ilustração de princípios de modelagem orientada a dados em Clojure, mostrando como representar objetos complexos do mundo real usando estruturas de dados simples e imutáveis.

## Estrutura do Projeto

### core.clj
Modelagem de dados anatômicos de hobbit.
- Definição de partes do corpo com maps estruturados
- Atributos de nome e tamanho para cada parte
- Representação assimétrica (apenas lado esquerdo modelado)
- Base para algoritmos de seleção probabilística

## Conceitos Demonstrados

### Modelagem Orientada a Dados
- Representação de entidades complexas via structures simples
- Maps como registros com chaves nomeadas  
- Vectors como coleções ordenadas de registros
- Separação entre dados e comportamento

### Design de Estruturas
- Escolha de representação adequada para domínio
- Atributos significativos (nome, tamanho) 
- Preparação para processamento algorítmico
- Extensibilidade da representação de dados

## Casos de Uso

Este modelo de dados serve como base para:
- Algoritmos de seleção aleatória ponderada por tamanho
- Simulações de combate com diferentes probabilidades
- Extensão para simetria corporal completa  
- Demonstração de transformações de coleções
