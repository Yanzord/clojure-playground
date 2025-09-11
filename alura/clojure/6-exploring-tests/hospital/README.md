# Explorando Testes

Curso sobre Test Driven Development (TDD) em Clojure aplicado a sistema hospitalar, cobrindo estratégias de testes, validação de esquemas, tratamento de erros e design orientado por testes.

## Objetivo

Ensinar TDD através de desenvolvimento de sistema hospitalar, demonstrando estratégias de testes, desde básicos até avançados, com foco em validação de comportamento e tratamento robusto de erros.

## Estrutura do Projeto

### model.clj
Modelo de dados com schemas validados.
- Schemas com Prismatic Schema para tipos customizados
- Estruturas `PacienteID`, `Departamento`, `Hospital`
- Uso de `PersistentQueue` para filas FIFO
- Funções factory para criação de objetos válidos

### logic.clj  
Lógica de negócio do sistema hospitalar.
- `cabe-na-fila?`: Validação de capacidade (máximo 5 pacientes)
- `chega-em`: Adição de pacientes com validação
- `transfere`: Movimentação entre departamentos
- `atende` e `proxima`: Gerenciamento de atendimento

### logic_test.clj
Suíte abrangente de testes seguindo TDD.
- Testes de borda e casos extremos
- Validação de exceções estruturadas
- Dados não-sequenciais para cenários realistas
- Pré e pós-condições como contratos

## Conceitos Abordados

### Test Driven Development
- Ciclo Red-Green-Refactor aplicado consistentemente
- Testes guiando design e implementação
- Refatoração confiante com cobertura de testes

### Estratégias de Teste
- **Boundary tests**: Validação de limites (0, 5, 6 pacientes)
- **One-off tests**: Casos +/-1 dos valores críticos  
- **Exception testing**: Validação de erros estruturados com `ex-info`
- **Non-sequential data**: Dados realistas vs sequenciais artificiais

### Tratamento de Erros
- Evolução de estratégias: nil → exceptions genéricas → `ex-info` estruturado
- Threading macros (`some->`) para nil-safety
- Metadados em exceções para debugging eficaz

### Técnicas Avançadas
- Schema validation integrada com testes
- Contratos de função com pré/pós-condições
- Separação entre testes de comportamento vs implementação

## Cenário Prático

Sistema hospitalar com departamentos, filas limitadas, transferências entre áreas e validação rigorosa de regras de negócio para demonstrar testing em contexto realista.
