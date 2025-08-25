# Clojure Playground 🎯

Este repositório contém minha jornada de estudos em Clojure, documentando o aprendizado desde conceitos básicos até técnicas avançadas de desenvolvimento funcional. Cada projeto representa uma etapa específica da evolução do conhecimento na linguagem.

## 🎯 Propósito do Repositório

Um laboratório prático para explorar os conceitos fundamentais e avançados do Clojure, servindo como:
- **Referência pessoal** para consultas futuras
- **Documentação do progresso** na linguagem
- **Base de conhecimento** para projetos reais
- **Portfólio técnico** das competências adquiridas

## 📚 Estudos Realizados

### Fundamentos

#### [`basic/`](basic/) - Conceitos Básicos
Primeiros passos com Clojure: funções, variáveis locais, vetores e operações fundamentais.
- **Conceitos**: Sintaxe básica, `let`, `defn`
- **Estruturas**: Vetores, funções de alta ordem (`map`, `filter`, `reduce`)

---

### Curso Alura - Progressão Completa

#### [`alura/functional-programming/`](alura/functional-programming/) - Programação Funcional
Fundamentos do paradigma funcional através de exemplos práticos de e-commerce.
- **Conceitos**: Higher-order functions, predicados, composição
- **Threading**: `->` e `->>`
- **Estruturas**: Maps, keywords, destrutruração

#### [`alura/day-to-day-collections/`](alura/day-to-day-collections/) - Coleções no Dia a Dia  
Manipulação avançada de coleções através de um sistema de loja.
- **Conceitos**: Recursão, `group-by`, pipelines de transformação
- **Práticas**: Análise de dados, agregações, relatórios

#### [`alura/exploring-tests/`](alura/exploring-tests/) - Explorando Testes
Test Driven Development e práticas modernas de testing.
- **Conceitos**: TDD, testes de borda, property validation
- **Ferramentas**: `clojure.test`, exception handling
- **Arquitetura**: Separação de responsabilidades, error handling

#### [`alura/generators-and-property-tests/`](alura/generators-and-property-tests/) - Testes Generativos
Property-based testing com geradores automáticos de dados.
- **Conceitos**: Property-based testing, `test.check`
- **Técnicas**: Geradores customizados, simulação de cenários
- **Validação**: Invariantes sistêmicas, casos extremos

#### [`alura/mutability-with-atoms-and-refs/`](alura/mutability-with-atoms-and-refs/) - Mutabilidade Controlada
Gerenciamento de estado mutável com Atoms e Refs.
- **Conceitos**: Atoms, Refs, STM (Software Transactional Memory)
- **Concorrência**: Thread-safety, `swap!`, `dosync`
- **Patterns**: Compare-and-swap, transações coordenadas

#### [`alura/record-protocol-multi-method/`](alura/record-protocol-multi-method/) - Types e Abstrações
Polimorfismo funcional com Records e Protocols.
- **Conceitos**: Records, Protocols, polimorfismo sem herança
- **Design**: Separação dados/comportamento, extensibilidade
- **Patterns**: Expression problem solution

#### [`alura/schemas/`](alura/schemas/) - Validação de Dados
Schemas para validação e documentação de contratos.
- **Conceitos**: Prismatic Schema, validação runtime
- **Práticas**: Documentação viva, contratos explícitos
- **Trade-offs**: Performance vs segurança

#### [`alura/OWASP/`](alura/OWASP/) - Segurança em Aplicações
Vulnerabilidades comuns e suas mitigações em Clojure.
- **Vulnerabilidades**: Injection, authentication failures
- **Soluções**: Validação, criptografia, rate limiting
- **Bibliotecas**: `crypto-password`, sanitização

---

### Materiais de Referência

#### [`clojure-for-the-brave-and-true/`](clojure-for-the-brave-and-true/) - Livro de Referência
Exercícios e exemplos do livro "Clojure for the Brave and True".
- **Fundamentos**: Estruturas de dados core, sintaxe
- **Projetos**: Hobbit-hitter, modelagem de dados
- **Metodologia**: Aprendizado incremental e divertido

---

### Projetos Futuros

#### [`nubank-challenges/`](nubank-challenges/) - Desafios Técnicos
Espaço reservado para desafios algorítmicos e problemas práticos.
- **Status**: 🚧 Em preparação
- **Foco**: Aplicação prática dos conceitos estudados

## 🗺️ Mapa de Conhecimento

### Progressão de Aprendizado

1. **Básico** → **Funcional** → **Coleções**
   - Sintaxe básica → Paradigma funcional → Manipulação de dados

2. **Testes** → **Geradores** → **Propriedades**
   - TDD tradicional → Property-based testing → Validação sistêmica

3. **Imutabilidade** → **Estado** → **Concorrência**
   - Dados imutáveis → Referências mutáveis → Threading coordenado

4. **Tipos** → **Validação** → **Segurança**
   - Polimorfismo funcional → Contratos de dados → Aplicações seguras

### Competências Desenvolvidas

#### 🎯 **Core Clojure**
- Sintaxe e idiomas da linguagem
- Estruturas de dados persistentes
- Programação funcional pura

#### 🔧 **Ferramentas e Práticas**  
- Testing (TDD, property-based)
- Validação de dados (Schemas)
- Gerenciamento de estado (Atoms, Refs)

#### 🏗️ **Arquitetura e Design**
- Polimorfismo sem orientação a objetos
- Separação de dados e comportamento
- Composição sobre herança

#### 🔐 **Segurança e Qualidade**
- Mitigação de vulnerabilidades OWASP
- Validação robusta de entrada
- Patterns de código seguro

## 🚀 Próximos Passos

- [ ] Implementar desafios do `nubank-challenges/`
- [ ] Explorar web development (Ring, Compojure)
- [ ] Estudar ClojureScript para frontend
- [ ] Aplicar conceitos em projetos reais

---

## 📖 Como Navegar

Cada diretório possui seu próprio `README.md` detalhado com:
- **Objetivo específico** do estudo
- **Conceitos principais** abordados
- **Exemplos práticos** e casos de uso
- **Progressão pedagógica** do conteúdo

**Sugestão de leitura**: Siga a ordem cronológica dos estudos para uma progressão natural do conhecimento, ou navegue diretamente para tópicos específicos de interesse.

---

*Este repositório reflete uma jornada contínua de aprendizado em Clojure, documentando tanto sucessos quanto desafios encontrados no caminho da maestria da programação funcional.*
