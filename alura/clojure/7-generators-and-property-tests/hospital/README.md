# Testes Generativos e Property-Based Testing

Curso sobre testes generativos em Clojure usando test.check, demonstrando property-based testing com geradores automáticos de dados para validar invariantes sistêmicas em sistema hospitalar.

## Objetivo

Complementar testes tradicionais baseados em exemplos com property-based testing, usando geradores automáticos para descobrir casos extremos e validar propriedades que sempre devem ser verdadeiras.

## Estrutura do Projeto

### model.clj
Modelo de dados integrado com schema-generators.
- Reutilização do modelo do curso anterior de testes
- Schemas com Prismatic Schema para validação
- Integração com `schema-generators` para geração automática

### logic.clj
Lógica de negócio otimizada para testes generativos.
- Funções evoluídas do curso anterior
- Nova função `total-de-pacientes` para invariantes
- Tratamento robusto de erros em cenários aleatórios

### logic_test.clj  
Combinação de testes example-based e property-based.
- Geradores customizados para domínio hospitalar
- Propriedades que validam invariantes sistêmicas
- Simulação de cenários complexos e realistas

## Conceitos Abordados

### Test.check e Geradores
- **Geradores nativos**: `gen/string-alphanumeric`, `gen/vector`, `gen/elements`
- **Geradores customizados**: `nome-aleatorio-gen`, `fila-nao-cheia-gen`
- **Geradores compostos**: `hospital-gen`, `acao-gen` para cenários complexos
- **Schema generators**: Integração automática com Prismatic Schema

### Property-Based Testing
- Propriedades que sempre devem ser verdadeiras
- Validação de invariantes sistêmicas (ex: conservação de pacientes)
- Geração automática de milhares de casos de teste
- Shrinking automático para casos mínimos de falha

### Simulações Avançadas
- Sequências aleatórias de ações do usuário
- Mistura de operações válidas e inválidas
- Validação resiliente mesmo com falhas pontuais
- Modelagem de uso real do sistema

### Vantagens sobre Testes Tradicionais
- Descoberta automática de edge cases não pensados
- Testes mais robustos contra mudanças de implementação
- Foco no "o que deve ser verdade" vs "casos específicos"
- Cobertura muito maior com menos código de teste

## Cenários Práticos

- Invariantes de conservação: pacientes nunca são perdidos
- Propriedades de capacidade: filas respeitam limites
- Simulações de dias de hospital com ações aleatórias
- Validação de comportamento sob stress com dados extremos
