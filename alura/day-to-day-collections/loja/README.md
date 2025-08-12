# Loja - Sistema de Gerenciamento de Pedidos

Este projeto é parte do curso "Clojure: Coleções do Dia a Dia" da Alura, focando no aprendizado de manipulação de coleções em Clojure através de um sistema simples de gerenciamento de pedidos de uma loja.

## Objetivo do Projeto

O projeto tem como objetivo ensinar e praticar:
- Manipulação de coleções em Clojure (vetores, mapas, etc.)
- Funções de alta ordem (`map`, `filter`, `reduce`, etc.)
- Threading macros (`->`, `->>`)
- Agrupamento e transformação de dados
- Estruturas de dados imutáveis
- Programação funcional em Clojure

## Estrutura do Projeto

### Arquivos Principais:
- `src/loja/db.clj` - Base de dados simulada com pedidos de exemplo
- `src/loja/aula1.clj` - Conceitos básicos de coleções
- `src/loja/aula2.clj` - Recursão e contagem de elementos
- `src/loja/aula3.clj` - Agrupamento de dados por usuário

### Dados de Exemplo:
O sistema trabalha com pedidos que contêm:
- `:usuario` - ID do usuário que fez o pedido
- `:itens` - Mapa com os itens do pedido (mochila, camiseta, tênis)
- Cada item possui `:id`, `:quantidade` e `:preco-unitario`

## Como Executar

### Pré-requisitos:
- Leiningen instalado
- Java 8 ou superior

### Executando o REPL:
```bash
lein repl
```

### Testando as funções:
```clojure
;; Carregar um namespace específico
(require '[loja.aula3 :as aula3])

;; Ver todos os pedidos
(require '[loja.db :as db])
(db/todos-os-pedidos)

;; Agrupar pedidos por usuário
(aula3/imprimir-pedidos-por-usuario)
```

## Exemplos de Uso

### Contando pedidos por usuário:
```clojure
(->> (db/todos-os-pedidos)
     (group-by :usuario)
     (map (fn [[usuario pedidos]] [usuario (count pedidos)]))
     (into {}))
```

### Listando todos os usuários únicos:
```clojure
(->> (db/todos-os-pedidos)
     (map :usuario)
     distinct)
```

## Conceitos Aprendidos

1. **Coleções Imutáveis**: Trabalho com vetores e mapas imutáveis
2. **Threading Macros**: Uso de `->>` para pipeline de transformações
3. **group-by**: Agrupamento de dados por critérios específicos
4. **Recursão**: Implementação de contadores recursivos
5. **doseq**: Iteração para efeitos colaterais (como println)
6. **Lazy Sequences**: Trabalho com sequências lazy e `doall`

## License

Copyright © 2025 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
