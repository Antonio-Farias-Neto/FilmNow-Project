# 🎬 FilmNow

FilmNow é um sistema simples de gerenciamento de filmes favoritos com suporte para exibição, categorização e manipulação de uma lista especial chamada **HotList**. Ele permite cadastrar até 100 filmes, visualizar detalhes, atribuir prioridades, carregar dados de arquivos CSV e interagir via terminal com menus de opções.

## 📁 Estrutura do Projeto

O projeto está organizado em cinco classes principais:

- `FilmNow`: Classe central de gerenciamento dos filmes e da HotList.
- `Filme`: Representa um filme com nome, ano de lançamento, locais de exibição e status na HotList.
- `DetalharFilme`: Exibe informações detalhadas de um filme, incluindo seus locais de exibição.
- `LeitorFilmNow`: Realiza leitura de filmes a partir de arquivos `.csv`.
- `MainFilmNow`: Interface de linha de comando para interação com o sistema.

## ⚙️ Funcionalidades

- Cadastrar, listar, detalhar e remover filmes.
- Adicionar/remover filmes na **HotList** (top 10).
- Adicionar/remover locais de exibição dos filmes.
- Buscar filmes por nome ou ano.
- Carregar filmes automaticamente de um arquivo CSV.

## 📦 Requisitos

- **Java 11 ou superior**.
- Opcional: IDE como IntelliJ IDEA, Eclipse, ou execução via terminal.

## ▶️ Execução

1. **Compilar os arquivos**:
   ```bash
   javac filmnow/*.java
````

2. **Executar o sistema**:

   ```bash
   java filmnow.MainFilmNow
   ```

3. **Opcionalmente**, certifique-se de que o arquivo `filmes_inicial.csv` esteja no diretório raiz do projeto com a seguinte estrutura:

   ```
   POSICAO,NOME,ANO,LOCAL
   1,Matrix,1999,Netflix
   2,Clube da Luta,1999,Prime Video
   ...
   ```

## 🧪 Exemplo de Uso

Ao iniciar o programa, o seguinte menu será exibido:

```
MENU
(A)Adicionar filme
(F)Remover Filme
(M)Mostrar todos
(MN)Mostrar Todos Pelo Nome
(MA)Mostrar Todos Pelo Ano
(D)Detalhar filme
(E)Exibir HotList
(H)Atribuir Hot
(R)Remover Hot
(L)Adicionarlocal
(O)Remover Local
(S)air
```

Escolha a opção digitando a letra correspondente e siga as instruções.

## 🧑‍💻 Autor

Desenvolvido por **Antonio Neto**.
Código de leitura de arquivos por **Professora Eliane**.
Projeto desenvolvido nas aulas de Programação da UFCG.
