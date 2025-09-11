# Schemas para Validação de Dados

Curso sobre validação de dados em Clojure usando Prismatic Schema, demonstrando como adicionar segurança de tipos e validação runtime mantendo flexibilidade da linguagem dinâmica.

## Objetivo

Equilibrar flexibilidade de linguagem dinâmica com necessidade de validação em sistemas de produção, usando schemas como documentação executável e contratos explícitos.

## Estrutura das Aulas

### aula1.clj
Introdução aos schemas e validação básica.
- Problemas de tipos inconsistentes em sistemas dinâmicos
- `s/validate` para validação manual de dados
- `s/defn` para validação automática de funções
- Detecção precoce de erros de tipo

### aula2.clj
Schemas complexos e customizações.
- Definição de schemas para estruturas de dados
- Validação customizada com predicados nomeados
- Trade-offs entre rigidez e forward compatibility
- Constraints e validações de regras de negócio

### aula3.clj
Integração com desenvolvimento e produção.
- Controle condicional de validação via `s/set-fn-validation!`
- Schemas como documentação viva de APIs
- Performance implications e estratégias de deployment

### aula4.clj
Patterns avançados de validação.
- Schemas compostos e modulares
- Validação de pipelines de dados
- Integração com testing e property-based testing

### aula5.clj
Casos práticos e evolution de contratos.
- Versionamento de schemas para APIs
- Backward/forward compatibility strategies
- Schema evolution em sistemas em produção

## Conceitos Abordados

### Validação de Dados
- `s/validate` para validação manual e explícita
- `s/defn` para validação automática de funções
- Detecção precoce de erros de tipo em sistemas dinâmicos
- Controle condicional via `s/set-fn-validation!`

### Schemas de Estruturas
- Definição de schemas para maps e estruturas complexas
- Predicados customizados para validação de regras de negócio
- Schemas compostos e modulares para reutilização
- Trade-offs entre rigidez e forward compatibility

### Documentação Executável
- Schemas como contratos explícitos de APIs
- Documentação viva que evolui com o código
- `s/explain` para geração automática de documentação
- Mensagens de erro descritivas e contextualizadas

### Performance e Deployment
- Ativação condicional baseada em ambiente
- Balance entre segurança de tipos e performance runtime
- Estratégias para desenvolvimento vs produção

## Cenários Práticos

- Validação de dados de entrada em APIs
- Contratos de funções em sistema hospitalar
- Evolução de schemas sem quebrar compatibilidade
- Detecção precoce de inconsistências de dados
