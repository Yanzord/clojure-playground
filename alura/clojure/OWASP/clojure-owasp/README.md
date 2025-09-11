# Segurança OWASP em Clojure

Curso sobre segurança em aplicações Clojure abordando principais vulnerabilidades do OWASP Top 10 com exemplos práticos de exploração e mitigação usando práticas seguras de desenvolvimento funcional.

## Objetivo

Demonstrar vulnerabilidades comuns em aplicações web e suas correções específicas para Clojure, combinando princípios de segurança com paradigma funcional para construção de aplicações resilientes.

## Estrutura dos Módulos

### owasp1.clj
Injection attacks: Command e SQL injection.
- Vulnerabilidade: concatenação insegura de strings com entrada do usuário
- Solução: parametrização e escape adequado de comandos
- Exemplos práticos de exploração e correção

### owasp2.clj  
Falhas criptográficas e segurança de senhas.
- Vulnerabilidades: senhas em texto plano e senhas fracas
- Soluções: hash BCrypt e validação contra listas de senhas comuns
- Uso da biblioteca `crypto-password` para hashing seguro

### owasp10.clj
Rate limiting e proteção contra força bruta.
- Vulnerabilidade: ataques de força bruta em login
- Implementação: contadores atômicos com Atoms por IP e usuário
- Estratégias de reset e limpeza de contadores

## Conceitos Abordados

### Vulnerabilidades Principais
- **Injection (A03:2021)**: Command injection via concatenação insegura
- **Cryptographic Failures (A02:2021)**: Senhas em texto plano
- **Authentication Failures (A07:2021)**: Ausência de rate limiting

### Mitigações Implementadas
- Parametrização segura de comandos e queries
- Hash BCrypt com salt automático para senhas
- Rate limiting thread-safe usando Atoms
- Validação de senhas contra listas de senhas comuns

### Segurança Funcional
- Imutabilidade reduzindo superfície de ataque
- Atoms para estado de segurança thread-safe  
- Separação de responsabilidades entre módulos
- Funções puras para validação sem efeitos colaterais

### Metodologia
- Demonstração de exploração de vulnerabilidades
- Implementação de correções seguras
- Validação da efetividade das mitigações
- Boas práticas defensivas adicionais

## Recursos Auxiliares

- Lista de senhas comuns (common-passwords.txt)
- Biblioteca crypto-password para criptografia
- Exemplos práticos de ataques e defesas
