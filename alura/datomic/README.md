# Datomic

Curso completo sobre Datomic, banco de dados imutável e funcional, cobrindo desde fundamentos até recursos avançados através de projetos práticos de e-commerce.

## Objetivo

Ensinar conceitos fundamentais e avançados do Datomic através de progressão estruturada: configuração, queries, schemas, funções transacionais, filtros, histórico e geradores de dados.

## Estrutura dos Cursos

### 1-chronological-database/
Fundamentos do banco cronológico.
- Configuração básica do Datomic
- Transações e queries fundamentais  
- Características cronológicas e temporais dos dados
- Snapshots e evolução do banco

### 2-datomic-queries/
Sistema de queries avançado.
- Sintaxe de query Datalog
- Joins e relacionamentos entre entidades
- Queries complexas e otimização
- Padrões de consulta eficientes

### 3-schemas-and-rules/
Schemas e regras de negócio.
- Definição de schemas de entidades
- Regras Datalog customizadas
- Validação e constraints de dados
- Evolução de schemas

### 4-bindings-transaction-functions-filters/
Bindings, funções transacionais e filtros.
- Bindings avançados em queries
- Transaction functions para lógica no banco
- Filtros para views específicas de dados
- Segurança e controle de acesso

### 5-filtered-database-and-history/
Banco filtrado e funcionalidades de histórico.
- Database filters para diferentes views
- Navegação no histórico temporal
- Auditoria e versionamento de dados
- Queries em diferentes pontos do tempo

### 6-generators-schemas-indexes/  
Geradores, schemas e índices avançados.
- Geradores de dados para testes
- Schemas avançados e composição
- Otimização de índices e performance
- Patterns para aplicações em produção

## Dependências

### Datomic Pro
Banco de dados necessário para executar os projetos.
- Download e instalação via documentação oficial
- Configuração de transactor para desenvolvimento
- Storage H2 embarcado para estudos

### Ambiente de Desenvolvimento
- Clojure e Leiningen para gerenciamento de dependências
- REPL para experimentação interativa
- Configuração de portas (4334, 4335) para transactor

## Como Usar

1. Instalar Datomic Pro e iniciar transactor
2. Navegar para projeto específico
3. Executar `lein deps` e `lein repl`  
4. Carregar namespaces e experimentar no REPL
