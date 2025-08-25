# OWASP Security - Clojure

## Objetivo de Estudo
Este projeto é baseado no curso da Alura sobre segurança em aplicações Clojure, explorando as principais vulnerabilidades do OWASP Top 10 e suas respectivas mitigações usando práticas seguras de desenvolvimento.

## O que este projeto representa
Um conjunto de exemplos práticos demonstrando vulnerabilidades comuns em aplicações web e suas correções, aplicando princípios de segurança no contexto da programação funcional com Clojure.

### Estrutura dos Módulos de Segurança

**owasp1.clj** - Injection Attacks
- **Vulnerabilidade**: Command Injection e SQL Injection
- **Problema**: Concatenação de strings com entrada do usuário
- **Solução**: Parametrização e escape de comandos
- **Exemplos**: Execução de comandos bash e queries SQL vulneráveis

**owasp2.clj** - Authentication e Password Security  
- **Vulnerabilidades**: Senhas em texto plano e senhas fracas
- **Soluções**: Hash BCrypt e validação contra listas de senhas comuns
- **Bibliotecas**: `crypto-password` para hashing seguro
- **Práticas**: Validação de força de senha

**owasp10.clj** - Rate Limiting e Brute Force Protection
- **Vulnerabilidade**: Ataques de força bruta
- **Solução**: Limitação de tentativas por IP e usuário
- **Implementação**: Contadores atômicos com Atoms
- **Reset**: Limpeza de contadores após login bem-sucedido

## Vulnerabilidades Abordadas

### 1. Injection (A03:2021)

**Vulnerável:**
```clojure
(defn run-cluster [config-file]
  (let [command (str "/bin/kafka " config-file)] 
    (sh "bash" "-c" command)))
    
; Ataque: (run-cluster "server.properties; ls /")
```

**Corrigido:**
```clojure
(defn run-cluster [config-file]
  (sh "/bin/kafka" config-file))
```

### 2. Cryptographic Failures (A02:2021)

**Vulnerável:**
```clojure
(defn register-new-user [username password]
  (add "Users" {:username username :password password}))
```

**Corrigido:**
```clojure  
(defn register-new-user [username password]
  (let [encrypted-password (password/encrypt password)]
    (add "Users" {:username username :password encrypted-password})))
```

### 3. Identification and Authentication Failures (A07:2021)

**Implementação de Rate Limiting:**
```clojure
(def username-attempts (atom {}))
(def ip-attempts (atom {}))
(def login-limit 30)

(defn attempt-login? [ip username]
  (and (<= (get @ip-attempts ip) login-limit)
       (<= (get @username-attempts username) login-limit)))
```

## Práticas de Segurança Implementadas

### Validação de Entrada
- Verificação de senhas contra listas de senhas comuns
- Sanitização de parâmetros antes de execução
- Validação de tipos e formatos

### Criptografia
- **BCrypt** para hash de senhas
- Salt automático para cada senha
- Verificação segura de senhas sem exposição

### Controle de Acesso
- Rate limiting por IP e usuário
- Bloqueio temporário após múltiplas tentativas
- Reset de contadores após autenticação válida

### Logging e Monitoramento
- Rastreamento de tentativas de login
- Contadores de ataques por origem
- Alertas para comportamentos suspeitos

## Bibliotecas e Dependências

### crypto-password (0.2.1)
- Hash seguro de senhas com BCrypt
- Verificação de senhas sem exposição
- Geração automática de salt

### Recursos Auxiliares
- **common-passwords.txt**: Lista de senhas fracas conhecidas
- **Validação**: Prevenção de senhas comuns

## Conceitos de Segurança Funcional

### Imutabilidade como Vantagem
- Estados imutáveis reduzem vulnerabilidades
- Menor superfície de ataque
- Rastreabilidade de mudanças

### Atoms para Estado Seguro
- Contadores thread-safe para rate limiting
- Operações atômicas para dados de segurança
- Consistência em ambientes concorrentes

### Separação de Responsabilidades
- Módulos dedicados para cada aspecto de segurança
- Funções puras para validação
- Efeitos colaterais isolados

## Metodologia de Demonstração

### 1. Mostrar Vulnerabilidade
- Implementação insegura funcional
- Demonstração do exploit
- Explicação do impacto

### 2. Aplicar Correção
- Refatoração segura
- Validação da correção
- Testes de resistência

### 3. Melhoria Contínua
- Práticas defensivas adicionais
- Monitoramento e alertas
- Educação da equipe

## Aplicabilidade Prática

### Para Desenvolvedores
- Padrões seguros em Clojure
- Bibliotecas recomendadas
- Code review focado em segurança

### Para Arquitetos
- Princípios de defense-in-depth
- Arquiteturas seguras por design
- Integração com ferramentas de segurança

### Para DevSecOps
- Automação de verificações
- Pipelines seguros
- Monitoramento contínuo

Este projeto serve como referência prática para construir aplicações Clojure seguras, combinando os benefícios da programação funcional com práticas consagradas de segurança em aplicações web.
