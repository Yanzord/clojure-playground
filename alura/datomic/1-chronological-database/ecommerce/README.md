# Banco de Dados Cronológico

Curso introdutório sobre Datomic demonstrando conceitos fundamentais de banco de dados imutável através de sistema de e-commerce com produtos, transações temporais e snapshots de dados.

## Objetivo

Introduzir características cronológicas do Datomic através de exemplos práticos: configuração, schemas, transações, snapshots e navegação temporal de dados.

## Estrutura das Aulas

### aula1.clj
Configuração inicial e operações básicas.
- Conexão e configuração do banco Datomic
- Criação de schema e estruturas iniciais
- Inserção de produtos e entidades básicas
- Queries de consulta e busca por ID

### aula2.clj
Transações e modificações de dados.
- Diferentes formas de criar entidades
- Transações com `:db/add` e `:db/retract`
- Manipulação de propriedades de entidades existentes
- Uso de temporary IDs e referências

### aula3.clj
Snapshots e evolução temporal.
- Criação de snapshots do banco em diferentes momentos
- Comparação de estados temporais
- Queries em pontos específicos do tempo
- Navegação no histórico de dados

### aula5.clj
Queries avançadas e relacionamentos.
- Consultas complexas com múltiplos critérios
- Navegação entre entidades relacionadas
- Padrões de query eficientes
- Otimização de consultas

### aula6.clj
Funcionalidades cronológicas avançadas.
- Histórico completo de entidades
- Auditoria e rastreamento de mudanças
- Queries temporais complexas
- Versionamento de dados

## Módulos de Apoio

### model.clj
Funções para criação de modelos de dados.
- Factory functions para produtos
- Estruturas de dados padronizadas
- Validação de modelos

### db.clj
Utilitários de banco e configuração.
- Funções de conexão e configuração
- Criação e gerenciamento de schemas
- Queries básicas reutilizáveis

## Conceitos Abordados

- Imutabilidade e características cronológicas
- Snapshots e navegação temporal
- Transações e modificação de estado
- Schemas e estruturação de dados
- Queries básicas e navegação de entidades
