# Schemas - Hospital

## Objetivo de Estudo
Este projeto é baseado no curso da Alura sobre validação de dados em Clojure usando Prismatic Schema, explorando como adicionar segurança de tipos e validação runtime a um sistema dinâmico.

## O que este projeto representa
Uma abordagem prática para adicionar validação de dados e documentação viva ao código Clojure, demonstrando como equilibrar a flexibilidade da linguagem com a necessidade de validação em sistemas em produção.

### Estrutura do Projeto

**aula1.clj** - Introdução aos Schemas
- Problemas de tipos inconsistentes em sistemas dinâmicos
- `s/validate` para validação manual
- `s/defn` para validação automática de funções
- Detecção precoce de erros de tipo

**aula2.clj** - Schemas Complexos e Customizados
- Definição de schemas para estruturas de dados
- Validação customizada com predicados
- Trade-offs de forward compatibility
- Constraints e validações de negócio

## Conceitos de Schema Validation

### Problemas sem Validação
```clojure
; Código frágil - erro detectado apenas em runtime tardio
(imprime-relatorio-de-paciente visitas daniela)  ; mapa como ID
(imprime-relatorio-de-paciente visitas 20)       ; número como ID
```

### Validação Manual
```clojure
(s/validate Long 15)          ; ✓ Sucesso
(s/validate Long "Guilherme") ; ✗ Erro imediato
```

### Validação Automática
```clojure
(s/set-fn-validation! true)

(s/defn imprime-relatorio-de-paciente 
  [visitas paciente :- Long]
  (println "Visitas do paciente" paciente))

; Erro detectado na chamada da função
```

## Schemas de Estruturas de Dados

### Schema Básico
```clojure
(def Paciente 
  "Schema de um paciente"
  {:id s/Num :nome s/Str})

(s/validate Paciente {:id 15 :nome "Yan"}) ; ✓ Válido
```

### Forward Compatibility
```clojure
; Schema rígido (não forward compatible)
(s/validate Paciente {:id 15 :nome "Yan" :plano [:raio-x]}) ; ✗ Erro

; Trade-off: segurança vs flexibilidade
```

### Schemas com Constraints
```clojure
(defn estritamente-positivo? [x] (> x 0))

(def EstritamentePositivo 
  (s/pred estritamente-positivo? 'estritamente-positivo))

(def Paciente
  {:id (s/constrained s/Int pos?) :nome s/Str})
```

## Vantagens dos Schemas

### Documentação Viva
- Schemas servem como documentação executável
- `s/explain` gera documentação automática
- Contratos explícitos entre funções

### Detecção Precoce de Erros
- Erros capturados no ponto de entrada/saída
- Mensagens de erro descritivas e contextualizadas
- Redução de debugging demorado

### Validação de Tipos de Entrada e Saída
```clojure
(s/defn novo-paciente :- Paciente
  [id :- s/Num nome :- s/Str]
  {:id id :nome nome})
```

### Integration com Desenvolvimento
- Validação ativada em desenvolvimento
- Desativada em produção para performance
- Testes automáticos com contratos validados

## Padrões e Best Practices

### Predicados Nomeados
```clojure
; ✓ Bom: função nomeada e testável
(defn estritamente-positivo? [x] (> x 0))
(def EstritamentePositivo (s/pred estritamente-positivo?))

; ✗ Evitar: lambdas anônimas no schema
(s/constrained s/Int #(> % 0))
```

### Schemas Modulares
- Schemas pequenos e componíveis
- Reutilização entre diferentes contextos
- Evolução incremental dos contratos

### Validação Condicional
- `s/set-fn-validation!` para controle global
- Activação condicionada ao ambiente
- Balance entre segurança e performance

## Trade-offs Importantes

### Rigidez vs Flexibilidade
- **Schemas rígidos**: Detectam mais erros, menos flexíveis
- **Schemas permissivos**: Mais flexíveis, podem perder erros
- **Estratégia**: Começar rígido, relaxar conforme necessário

### Performance vs Segurança
- Validação runtime tem custo computacional
- Essencial em desenvolvimento e testes
- Opcional em produção com alta performance

### Evolução de Contratos
- Schemas como contratos evoluem com o sistema
- Versionamento de schemas para APIs
- Backward/forward compatibility planejada

## Integração com Ecosystem

### Testing
- Schemas facilitam property-based testing
- Geração automática de dados de teste
- Invariantes validadas automaticamente

### API Design
- Contratos explícitos para APIs REST
- Validação de entrada/saída padronizada
- Documentação automática de endpoints

### Data Pipeline Validation
- Validação em pipelines de dados
- Detecção precoce de problemas de qualidade
- Monitoramento de contratos de dados

## Filosofia de Gradual Typing

### Optional Typing
- Adição progressiva de tipos onde necessário
- Não requer refatoração massiva
- Compatibilidade com código existente

### Runtime vs Compile Time
- Validação em runtime permite flexibilidade
- Feedback imediato durante desenvolvimento
- Contratos verificados em execução

Este projeto demonstra como usar schemas para adicionar uma camada de segurança e documentação ao código Clojure, mantendo a flexibilidade da linguagem enquanto reduz erros comuns relacionados a tipos e estruturas de dados incorretas.
