# Record, Protocol e Multi-Method - Hospital

## Objetivo de Estudo
Este projeto é baseado no curso da Alura sobre estruturas avançadas de dados e polimorfismo em Clojure, explorando Records, Protocols e Multi-methods através da evolução do sistema de hospital.

## O que este projeto representa
Uma exploração das alternativas ao polimorfismo orientado a objetos em Clojure, demonstrando como criar abstrações flexíveis e extensíveis usando os mecanismos nativos da linguagem para tipos e comportamentos.

### Estrutura do Projeto

**aula1.clj** - Records: Estruturas Tipadas
- Introdução aos Records como alternativa aos maps simples
- Sintaxes de criação: `->Record`, `Record.`, `map->Record`
- Diferenças entre Records e maps tradicionais
- Validação e comportamento de tipos estruturados

**aula2.clj** - Protocols: Polimorfismo Funcional  
- Definição de Protocols como contratos de comportamento
- `extend-type` para implementação de protocols
- Polimorfismo sem hierarquia de classes
- Extensão de tipos Java existentes

## Conceitos de Types e Abstractions

### Records: Estruturas de Dados Tipadas

**Definição e Criação:**
```clojure
(defrecord Paciente [id nome nascimento])

; Sintaxes de criação
(->Paciente 15 "Yan" "02/04/1998")           ; Posicional
(Paciente. 15 "Yan" "02/04/1998")            ; Java interop
(map->Paciente {:id 15 :nome "Yan"})         ; A partir de map
```

**Características dos Records:**
- Comportam-se como maps mas com tipo específico
- Acesso via keywords: `(:id paciente)`  
- Acesso via métodos: `(.nome paciente)`
- Equality baseada em valor, não identidade
- Performance otimizada para campos definidos

### Protocols: Contratos de Comportamento

**Definição de Protocol:**
```clojure
(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))
```

**Implementação via extend-type:**
```clojure
(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (>= valor 50)))
```

## Vantagens sobre OOP Tradicional

### Separação de Dados e Comportamento
- **Dados**: Records definem estrutura
- **Comportamento**: Protocols definem operações
- **Flexibilidade**: Comportamentos podem ser adicionados sem modificar tipos

### Expression Problem Solution
- Fácil adição de novos tipos (Records)
- Fácil adição de novos comportamentos (Protocols)
- Sem recompilação de código existente

### Extensibilidade
- Protocols podem ser implementados para tipos existentes (Java)
- Tipos podem implementar múltiplos protocols
- Implementações podem ser adicionadas a posteriori

## Padrões de Design

### Multiple Dispatch via Protocols
```clojure
; Comportamento diferente baseado no tipo
(deve-assinar-pre-autorizacao? particular :raio-x 40)      ; false
(deve-assinar-pre-autorizacao? plano-saude :raio-x 40)     ; true
```

### Extensão de Tipos Java
```clojure
(extend-type java.util.Date
  Dateable
  (to-ms [this] (.getTime this)))
  
(extend-type java.lang.Number  
  Dateable
  (to-ms [this] this))
```

### Composition over Inheritance
- Records compõem dados sem hierarquia
- Protocols compõem comportamentos
- Flexibilidade sem acoplamento de herança

## Comparação com Alternativas

### vs Maps Simples
- **Records**: Tipo específico, validação, performance
- **Maps**: Flexibilidade total, sem tipo

### vs Classes OOP
- **Records+Protocols**: Composição flexível
- **Classes**: Herança rígida, acoplamento

### vs Multi-methods
- **Protocols**: Performance otimizada, dispatch simples
- **Multi-methods**: Dispatch complexo, mais flexível

## Patterns Avançados

### Protocol como Interface
```clojure
; Múltiplos tipos implementando mesmo protocolo
(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [...])
  (calcula-valor-final [...]))
```

### Records com Validação
```clojure
(defn adiciona-paciente [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente sem id" {:paciente paciente}))))
```

### Extensão Retroativa
```clojure
; Adicionar comportamento a tipos já existentes
(extend-type java.util.Calendar
  Dateable
  (to-ms [this] (to-ms (.getTime this))))
```

## Arquitetura Emergente

### Data-Driven Programming
- Records como especificação de dados
- Protocols como especificação de comportamento
- Implementações independentes e composáveis

### Open/Closed Principle
- Aberto para extensão (novos tipos, novos protocols)
- Fechado para modificação (código existente intocado)
- Evolução sem quebra de compatibilidade

### Performance Benefits
- Dispatch otimizado via protocols
- Acesso otimizado a campos de records
- JIT compilation friendly

Este projeto demonstra como Clojure oferece mecanismos poderosos para criar abstrações flexíveis e extensíveis, combinando a simplicidade de dados imutáveis com o polimorfismo necessário para sistemas complexos, sem os custos da orientação a objetos tradicional.
