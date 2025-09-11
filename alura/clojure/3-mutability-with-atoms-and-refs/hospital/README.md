# Mutabilidade com Atoms e Refs

Curso sobre gerenciamento de estado mutável em Clojure usando Atoms, Refs e STM (Software Transactional Memory) através de simulação de sistema hospitalar com concorrência controlada.

## Objetivo

Demonstrar como gerenciar estado mutável de forma segura e thread-safe em Clojure, contrastando problemas de mutabilidade descontrolada com soluções elegantes usando tipos de referência especializados.

## Estrutura das Aulas

### aula1.clj
Problemas de estado global e concorrência.
- Demonstração de problemas com `def` e root binding
- Race conditions com variáveis globais compartilhadas
- Simulação de threads concorrentes problemáticas
- Inconsistências de estado em ambiente multi-threaded

### aula3.clj
Introdução aos Atoms para mutabilidade thread-safe.
- Atoms como referências atômicas independentes
- `swap!` para atualizações thread-safe
- Compare-and-swap automático em conflitos
- Comparação entre shadowing e mutabilidade real

### aula4.clj
Padrões de concorrência com Atoms.
- Diferentes abordagens para processamento paralelo
- `mapv`, `doseq`, `dotimes` para execução concorrente
- Refatoração de código e curry com `partial`
- Gerenciamento de múltiplas threads

### aula5.clj
Operações compostas e transferências.
- Transferências entre departamentos usando Atoms
- Composição de múltiplas operações mutáveis
- Simulação de cenários complexos de concorrência
- Validação de consistência de dados

### aula6.clj
Refs e STM (Software Transactional Memory).
- Introdução a Refs para transações coordenadas
- `dosync`, `alter`, `ref-set` para operações transacionais
- Consistência ACID em operações múltiplas
- Coordenação de múltiplas referências

## Módulos de Apoio

### colecoes.clj
Estudo de comportamento de diferentes coleções.
- Diferenças entre vetores, listas, conjuntos e filas
- Operações `conj`, `pop`, `peek` em estruturas distintas
- Performance characteristics de cada coleção

## Conceitos Abordados

### Tipos de Referência
- **Atoms**: Referências independentes thread-safe com compare-and-swap
- **Refs**: Referências transacionais para operações coordenadas
- **STM**: Software Transactional Memory para consistência ACID
- **`swap!`, `alter`, `dosync`**: Operações especializadas para cada tipo

### Problemas de Concorrência
- Race conditions com estado global compartilhado
- Inconsistências em ambiente multi-threaded
- Soluções thread-safe sem locks explícitos
- Compare-and-swap automático vs transações coordenadas

### Padrões de Threading
- `mapv`, `doseq`, `dotimes` para execução paralela
- Curry e partial application para abstração
- Simulação de cenários realistas de concorrência

## Cenários Práticos

- Sistema hospitalar com departamentos e filas
- Transferências entre departamentos com consistência
- Simulação de chegada de pacientes concorrente  
- Operações coordenadas em múltiplas referências
