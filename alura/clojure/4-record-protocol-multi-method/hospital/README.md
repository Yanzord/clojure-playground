# Records, Protocols e Multi-Methods

Curso sobre estruturas avançadas de dados e polimorfismo funcional em Clojure, explorando alternativas à orientação a objetos através de Records, Protocols e Multi-methods aplicados a sistema hospitalar.

## Objetivo

Demonstrar como criar abstrações flexíveis e extensíveis usando mecanismos nativos do Clojure para tipos e comportamentos, oferecendo alternativas ao polimorfismo orientado a objetos tradicional.

## Estrutura das Aulas

### aula1.clj
Records: estruturas de dados tipadas.
- Introdução aos Records como alternativa aos maps simples
- Sintaxes de criação: `->Record`, `Record.`, `map->Record`
- Diferenças entre Records e maps em performance e semântica
- Acesso via keywords vs métodos de campo

### aula2.clj
Protocols: contratos de comportamento.
- Definição de Protocols como interfaces funcionais
- `extend-type` para implementação de protocols
- Polimorfismo baseado em tipo sem hierarquia de classes
- Extensão de tipos Java existentes

### aula3.clj
Multi-methods: dispatch avançado.
- Dispatch customizado baseado em valores arbitrários
- Implementação de comportamento polimórfico complexo
- Hierarquias de dispatch e relações entre tipos

### aula4.clj
Integração e patterns avançados.
- Combinação de Records, Protocols e Multi-methods
- Patterns de design funcional
- Extensibilidade e composição de comportamentos

### aula5.clj  
Casos de uso práticos e comparações.
- Aplicação prática em domínio hospitalar
- Comparação com abordagens orientadas a objetos
- Trade-offs e decisões de design

## Conceitos Abordados

### Records vs Maps
- Records como estruturas tipadas com performance otimizada
- Múltiplas sintaxes de criação e acesso a dados
- Comportamento híbrido: tipagem + flexibilidade de maps
- Equality baseada em valor e acesso via keywords/métodos

### Protocols como Contratos
- Definição de interfaces funcionais sem hierarquia de classes
- `extend-type` para implementação de comportamentos
- Polimorfismo baseado em tipo com dispatch otimizado
- Extensão retroativa de tipos existentes (inclusive Java)

### Multi-methods
- Dispatch customizado baseado em valores arbitrários
- Polimorfismo mais flexível que protocols
- Hierarquias de dispatch e relações complexas entre tipos
- Trade-off entre flexibilidade e performance

### Design Patterns Funcionais
- Separação clara entre dados (Records) e comportamento (Protocols)
- Composition over inheritance
- Expression problem solution: fácil adição de tipos e comportamentos
- Open/closed principle sem herança tradicional

## Cenários Práticos

- Sistema hospitalar com diferentes tipos de pacientes
- Polimorfismo para regras de cobrança e autorização
- Extensão de comportamentos sem modificar código existente
- Integração com tipos Java existentes
