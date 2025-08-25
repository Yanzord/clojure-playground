# Mutability with Atoms and Refs - Hospital

## Objetivo de Estudo
Este projeto é baseado no curso da Alura sobre gerenciamento de estado mutável em Clojure, explorando Atoms, Refs e STM (Software Transactional Memory) através da evolução do sistema de hospital.

## O que este projeto representa
Uma evolução do sistema de hospital que demonstra como gerenciar estado mutável de forma controlada e thread-safe em Clojure, contrastando problemas de mutabilidade descontrolada com soluções elegantes usando os tipos de referência do Clojure.

### Estrutura do Projeto

**aula1.clj** - Problemas de Estado Global
- Demonstração de problemas com `def` e root binding
- Race conditions com variáveis globais compartilhadas
- Simulação de concorrência problemática

**aula3.clj** - Atoms: Mutabilidade Coordenada
- Introdução aos Atoms como referências thread-safe
- `swap!` para atualizações atômicas
- Comparação shadowing vs mutabilidade

**aula4.clj** - Concorrência com Atoms
- Diferentes padrões para trabalhar com coleções
- `mapv`, `doseq`, `dotimes` para execução paralela
- Refatoração e curry com `partial`

**aula5.clj** - Operações Compostas
- Transferências entre departamentos com Atoms
- Composição de operações mutáveis

**aula6.clj** - Refs e STM (Software Transactional Memory)
- Introdução a Refs para transações coordenadas
- `dosync`, `alter`, `ref-set`
- Consistência transacional em operações múltiplas

**colecoes.clj** - Comportamento de Coleções
- Diferenças entre vetores, listas, conjuntos e filas
- `conj`, `pop`, `peek` em diferentes estruturas

## Conceitos de Mutabilidade Controlada

### Problemas da Mutabilidade Descontrolada
```clojure
; PROBLEMÁTICO: estado global compartilhado
(def hospital (h.model/novo-hospital))
(def hospital (h.logic/chega-em hospital :espera "111"))
```

### Atoms - Mutabilidade Independente
```clojure
; SOLUÇÃO: estado local com thread-safety
(let [hospital (atom (h.model/novo-hospital))]
  (swap! hospital h.logic/chega-em :espera "111"))
```

### Refs - Transações Coordenadas
```clojure
; SOLUÇÃO: múltiplas referências coordenadas
(dosync 
  (alter fila-origem pop)
  (alter fila-destino conj pessoa))
```

## Tipos de Referência em Clojure

### Atoms
- **Thread-safe**: Operações atômicas automáticas
- **Compare-and-swap**: Retries automáticos em conflitos
- **Independentes**: Uma referência por vez
- **`swap!`**: Aplicação de função ao valor atual

### Refs e STM
- **Transacionais**: Múltiplas refs em uma transação
- **`dosync`**: Bloco transacional
- **`alter`**: Modificação dentro de transação
- **`ref-set`**: Atribuição direta dentro de transação

### Vantagens do STM
- **ACI properties**: Atomicidade, Consistência, Isolamento
- **Retry automático**: Em caso de conflitos
- **Deadlock-free**: Sem locks tradicionais
- **Composabilidade**: Transações podem ser compostas

## Patterns de Concorrência

### Threading com Collections
```clojure
; mapv: força execução eager
(mapv #(.start (Thread. (fn [] (chega-em! hospital %)))) pessoas)

; doseq: lado de efeito sobre coleção
(doseq [pessoa pessoas] (starta-thread hospital pessoa))

; dotimes: repetição numérica
(dotimes [i 6] (starta-thread hospital i))
```

### Higher-Order Functions
```clojure
; Curry manual
(defn starta-thread-de-chegada 
  ([hospital] (fn [pessoa] (starta-thread-de-chegada hospital pessoa))))

; Partial application
(partial starta-thread-de-chegada hospital)
```

## Progressão Pedagógica

### Fase 1: Identificação de Problemas
- Root binding com `def`
- Race conditions
- Estado compartilhado problemático

### Fase 2: Atoms
- Referências independentes thread-safe
- `swap!` para atualizações atômicas
- Simulações paralelas seguras

### Fase 3: Refs e STM  
- Coordenação de múltiplas referências
- Transações ACID
- Operações compostas consistentes

### Fase 4: Patterns Avançados
- Futures para processamento assíncrono
- Combinação de diferentes tipos de referência
- Arquiteturas escaláveis

## Comparação com Mutabilidade Tradicional

### Problemas Tradicionais
- Locks explícitos
- Deadlocks
- Race conditions
- Complexidade de sincronização

### Soluções Clojure
- **Immutability by default**
- **Referências explícitas e controladas**
- **STM para coordenação**
- **Compare-and-swap sem locks**

## Casos de Uso

### Atoms: Ideal para
- Contadores
- Cache local
- Estado independente
- Métricas

### Refs: Ideal para
- Transferências bancárias
- Operações multi-entidade
- Consistência transacional
- Estado coordenado

O projeto demonstra a elegância de gerenciar mutabilidade de forma controlada, mantendo os benefícios da programação funcional enquanto permite alterações de estado quando necessárias.
