# Datomic - Projetos de Estudo

Este diretório contém projetos de estudo relacionados ao Datomic, um banco de dados imutável e funcional.

## Dependências Necessárias

Para executar os projetos neste diretório, você precisa ter as seguintes dependências instaladas:

### 1. Datomic Pro

O Datomic Pro é necessário para executar os projetos. Siga as instruções de instalação na documentação oficial:

🔗 **[Datomic Pro Setup Guide](https://docs.datomic.com/setup/pro-setup.html)**

#### Instalação do Datomic Pro:

1. Baixe a versão mais recente do Datomic Pro:
   ```bash
   curl https://datomic-pro-downloads.s3.amazonaws.com/1.0.7394/datomic-pro-1.0.7394.zip -O
   ```

2. Descompacte o arquivo:
   ```bash
   unzip datomic-pro-1.0.7394.zip
   cd datomic-pro-1.0.7394
   ```

3. Para desenvolvimento local, inicie o transactor com as configurações de desenvolvimento:
   ```bash
   bin/transactor config/samples/dev-transactor-template.properties
   ```

   O transactor estará pronto quando a mensagem "System started" aparecer no stdout.

### 2. Clojure

Instale o Clojure seguindo as instruções oficiais para seu sistema operacional:

- **macOS**: `brew install clojure/tools/clojure`
- **Linux**: Siga as instruções em [clojure.org](https://clojure.org/guides/getting_started)
- **Windows**: Use o instalador disponível em [clojure.org](https://clojure.org/guides/getting_started)

### 3. Leiningen

O Leiningen é necessário para gerenciar as dependências e executar os projetos:

#### Instalação:

- **macOS**: `brew install leiningen`
- **Linux/Unix**: 
  ```bash
  curl https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein > ~/bin/lein
  chmod +x ~/bin/lein
  lein
  ```
- **Windows**: Baixe o `lein.bat` do [site oficial](https://leiningen.org/)

## Estrutura dos Projetos

### 1-chronological-database/
Contém exemplos e exercícios sobre as características cronológicas do Datomic, incluindo:
- Configuração do banco de dados
- Transações e queries básicas
- Histórico temporal de dados

## Como Executar

1. **Certifique-se de que o Datomic Pro está rodando:**
   ```bash
   # No diretório do Datomic Pro
   bin/transactor config/samples/dev-transactor-template.properties
   ```

2. **Navegue até o projeto desejado:**
   ```bash
   cd 1-chronological-database/ecommerce
   ```

3. **Instale as dependências:**
   ```bash
   lein deps
   ```

4. **Inicie o REPL:**
   ```bash
   lein repl
   ```

5. **Execute o código:**
   ```clojure
   (require '[ecommerce.core :as core])
   (core/-main)
   ```

## Configurações Importantes

- **Porta do Transactor**: Por padrão, o transactor de desenvolvimento usa as portas 4334 e 4335
- **Armazenamento**: Os projetos usam o storage H2 embarcado para desenvolvimento
- **Versão do Datomic**: Os projetos foram testados com a versão 1.0.7394

## Troubleshooting

### Erro de Conexão com o Transactor
Se você receber erros de conexão, verifique se:
- O transactor está rodando
- As portas 4334 e 4335 estão disponíveis
- Não há firewall bloqueando as conexões

### Dependências não Encontradas
Se houver problemas com dependências do Datomic:
- Verifique se o Datomic Pro está instalado corretamente
- Confirme que as credenciais estão configuradas (se necessário)
- Execute `lein clean` e `lein deps` novamente

## Recursos Adicionais

- [Documentação Oficial do Datomic](https://docs.datomic.com/)
- [Tutorial do Peer Library](https://docs.datomic.com/peer-tutorial/run-a-transactor.html)
- [Guia de Query](https://docs.datomic.com/query/query-executing.html)
