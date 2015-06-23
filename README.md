# Implementação de Banco de Dados - TF

## Alunos: Guilherme Taschetto e Jean Schuschardt

### Arquivos contidos no pacote:

  - `./diagramas.astah` arquivo contendo o diagrama de classes e o diagrama de sequência conforme solicitados
  - `./BTreeSim/` projeto Java (Netbeans) com implementação de um simulador de BTrees

### Escopo

Não realizamos completamente o escopo solicitado pelo professor. A implementação consiste somente de um simulador de B+Trees em memória com as seguintes funções:

  - Adicionar uma chave ao índice
  - Adicionar um range sequencial de chaves ao índices
  - Adicionar um range aleatório de chaves ao índice
  - Remover uma chave do índice
  - Procurar uma chave no índice
  - Gerar uma visualização gráfica do índice

### Dependências

A única dependência do projeto é a ferramenta GraphViz, utilizada para geração da visualização gráfica da árvore. No Windows, o simulador usará a versão do GraphViz empacotada com o projeto. No Linux, será necessário instalar o pacote do GraphViz (`sudo apt-get install graphviz`).

### Instruções para Execução

Após a compilação, executar o arquivo `./dist/BTreeSim.jar` e utilizar as opções da interface gráfica.
