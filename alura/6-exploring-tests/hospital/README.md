# Exploring Tests - Hospital

## Objetivo de Estudo
Este projeto é baseado no curso da Alura sobre testes em Clojure, explorando Test Driven Development (TDD) e Test Driven Design através de um sistema simulado de hospital.

## O que este projeto representa
Um sistema de gerenciamento de filas de hospital que demonstra como aplicar práticas modernas de testes em Clojure, incluindo TDD, testes de borda, validação de esquemas e tratamento de erros.

### Estrutura do Projeto

**model.clj** - Modelo de Dados
- Definição de schemas com Prismatic Schema
- Tipos customizados: `PacienteID`, `Departamento`, `Hospital`
- Uso de `PersistentQueue` para filas FIFO
- Função factory para criar hospitais

**logic.clj** - Lógica de Negócio
- `cabe-na-fila?`: Verifica se há espaço na fila (máximo 5 pacientes)
- `chega-em`: Adiciona pacientes às filas com validação
- `transfere`: Move pacientes entre departamentos
- `atende` e `proxima`: Gerenciam o atendimento de pacientes

**logic_test.clj** - Suíte de Testes Abrangente
- Testes de borda (boundary tests)
- Testes com valores "one-off" (+1, -1 dos limites)
- Testes não-sequenciais para casos mais realistas
- Validação de exceções estruturadas
- Pré e pós-condições

## Conceitos de Testes Praticados

### Test Driven Development (TDD)
- Ciclo Red-Green-Refactor
- Testes primeiro, implementação depois
- Design emergente através dos testes

### Tipos de Testes
- **Boundary Tests**: Testam os limites (0, 5, 6 pacientes)
- **One-off Tests**: Testam +/-1 dos valores limite
- **Non-sequential Tests**: Dados não-sequenciais mais realistas
- **Exception Testing**: Validação de erros estruturados

### Técnicas Avançadas
- **Threading Macros**: `some->` para pipeline com nil-safety  
- **Exception Info**: `ex-info` com dados estruturados vs exceptions genéricas
- **Schema Validation**: Validação de tipos em runtime
- **Pre/Post Conditions**: Contratos de função declarativos

### Tratamento de Erros
- Evolução de diferentes estratégias de error handling:
  - Retorno `nil`
  - Exceptions genéricas vs estruturadas  
  - Maps de resultado com `:sucesso`/`:erro`
  - `ex-info` com metadados para debugging

## Cenário Prático
Sistema de hospital com:
- Filas por departamento (espera, laboratórios, raio-x)
- Limite de 5 pacientes por fila
- Transferências entre departamentos
- Validação de regras de negócio
- Error handling robusto

## Aprendizados sobre Qualidade
- Como escrever testes que realmente validam comportamento
- Diferença entre testar implementação vs comportamento
- Importância de testes de borda e casos extremos
- Estruturação de dados de erro para debugging eficaz
