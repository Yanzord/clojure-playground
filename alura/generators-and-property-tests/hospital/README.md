# Generators and Property Tests - Hospital

## Objetivo de Estudo
Este projeto é baseado no curso da Alura sobre testes generativos e property-based testing em Clojure, explorando técnicas avançadas de testing através do test.check e geradores automáticos de dados.

## O que este projeto representa
Uma evolução do sistema de hospital que demonstra como usar property-based testing para validar comportamentos sistêmicos com geradores automáticos de dados de teste, complementando os testes tradicionais baseados em exemplos.

### Estrutura do Projeto

**model.clj** - Modelo de Dados
- Mesmo modelo do curso anterior de testes
- Schemas com Prismatic Schema
- Integração com schema-generators

**logic.clj** - Lógica de Negócio Evoluída
- Funções do curso anterior otimizadas
- Nova função `total-de-pacientes` para property testing
- Tratamento robusto de erros para testes generativos

**logic_test.clj** - Testes Generativos Avançados
- Combinação de testes example-based e property-based
- Geradores customizados para domínios específicos
- Simulação de cenários complexos e realistas

## Conceitos de Property-Based Testing

### Test.check e Geradores
- **test.check**: Biblioteca para property-based testing
- **Geradores nativos**: `gen/string-alphanumeric`, `gen/vector`, `gen/elements`
- **Geradores customizados**: `nome-aleatorio-gen`, `fila-nao-cheia-gen`
- **Schema generators**: Integração com Prismatic Schema

### Estrutura dos Testes Generativos

**Geradores Básicos**
```clojure
(def nome-aleatorio-gen
  (gen/fmap clojure.string/join 
            (gen/vector gen/char-alphanumeric 5 10)))
```

**Propriedades Testadas**
- `coloca-uma-pessoa-em-filas-menores-que-5`: Validação básica
- `transfere-tem-que-manter-a-quantidade-de-pessoas`: Invariante sistêmica
- `simula-um-dia-do-hospital-nao-perde-pessoas`: Simulação complexa

### Técnicas Avançadas

**Geradores Compostos**
- `hospital-gen`: Geração de hospitais válidos
- `acao-gen`: Simulação de ações do usuário
- Combinação de geradores para cenários realistas

**Tratamento de Erros em Testes Generativos**
- `transfere-ignorando-erro`: Wrapper para cenários de erro
- `executa-uma-acao`: Execução resiliente de ações
- Validação de propriedades mesmo com falhas pontuais

**Simulação de Cenários Reais**
- Geração de sequências de ações aleatórias
- Mistura de operações válidas e inválidas
- Validação de invariantes sistêmicas

## Property-Based vs Example-Based Testing

### Example-Based (Tradicional)
```clojure
(is (= {:espera [1 2 3 4 5]} 
       (chega-em {:espera [1 2 3 4]} :espera 5)))
```

### Property-Based (Generativo)
```clojure
(defspec coloca-uma-pessoa-em-filas-menores-que-5 100
  (prop/for-all [fila (gen/vector gen/string-alphanumeric 0 4)
                 pessoa gen/string-alphanumeric]
    (is (= {:espera (conj fila pessoa)} 
           (chega-em {:espera fila} :espera pessoa)))))
```

## Vantagens do Property-Based Testing

### Descoberta de Casos Extremos
- Geração automática de milhares de casos de teste
- Descoberta de edge cases não pensados manualmente
- Shrinking automático para casos mínimos de falha

### Invariantes de Sistema
- Validação de propriedades que sempre devem ser verdadeiras
- Testes mais robustos contra mudanças de implementação
- Foco no "o que" ao invés de "como"

### Simulações Realistas
- Cenários complexos com múltiplas interações
- Validação de comportamento sob stress
- Modelagem de uso real do sistema

## Desafios e Limitações

### Complexidade de Geradores
- Necessidade de criar geradores representativos
- Balanceamento entre simplicidade e realismo
- Manutenção de geradores junto com evolução do código

### Interpretação de Falhas
- Casos de falha podem ser complexos de analisar
- Necessidade de shrinking eficiente
- Debug mais desafiador que testes tradicionais

## Integração com Esquemas
- Uso de `schema-generators` para geração automática
- Validação em runtime com schemas
- Integração seamless entre validação e testing
