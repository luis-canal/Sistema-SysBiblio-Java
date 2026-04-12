import java.util.List;

LivroService service = new LivroService();

void main() {
    String menu = """
            ===== Sistema da Bibliotca =====
            1- Cadastrar livro
            2- Listar livro
            3- Pesquisar livro
            4- Remover um livro do acervo
            5- Editar um livro do acervo
            0- Sair
            =================================
            """;
    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite uma opção: ");
        try {
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> pesquisar();
                case 4 -> remover();
                case 0 -> {
                    IO.println("Saindo...");
                    break;
                }
                default -> IO.println("Opção inválida");
            }
        } catch (Exception e) {
            IO.println("Erro: " + e.getMessage());
        }
        if (opcao != 0)
            IO.readln("Pressione Enter para continuar...");
    } while (opcao != 0);
}

void cadastrar() throws Exception {
    String titulo = Input.scanString("Digite o título do livro: ");
    String autor = Input.scanString("Digite o autor do livro: ");
    int anoPublicacao = Input.scanInt("Digite o ano de publicação do livro: ");
    int numeroPaginas = Input.scanInt("Digite o número de páginas do livro: ");

    Livro novoLivro = new Livro(titulo, autor, anoPublicacao, numeroPaginas);

    service.cadastrar(novoLivro);

    IO.println("Livro cadastrado com sucesso.");
}

void listar() {
    List<Livro> livros = service.listar();

    imprimirLista(livros);
}

void pesquisar() {
    String menu = """
            ===== Pesquisa =====
            1- Por título
            2- Por autor
            3- Por ano
            ===================
            """;

    int opcaoPesquisa = Input.scanInt("Escolha uma opção: ");

    List<Livro> resultado;
    switch (opcaoPesquisa) {
        case 1 -> {
            String titulo = Input.scanString("Digite o título: ");
            resultado = service.pesquisarTitulo(titulo);
        }
        case 2 -> {
            String autor = Input.scanString("Digite o autor: ");
            resultado = service.pesquisarAutor(autor);
        }
        case 3 -> {
            int ano = Input.scanInt("Digite o ano: ");
            resultado = service.pesquisarAno(ano);
        }
        default -> {
            IO.println("Opção inválida.");
            return;
        }
    }
    imprimirLista(resultado);
}

void imprimirLista(List<Livro> livros) {
    if (livros.isEmpty()) {
        IO.println("Nenhum livro cadastrado.");
        return;
    }
    int i = 1;
    for (Livro livro : livros) {
        IO.println(i++ + " - " + livro);
    }
}

void remover() throws Exception {
    List<Livro> livros = service.listar();
    if (livros.isEmpty()) {
        IO.println("Nenhum livro cadastrado.");
        return;
    }
    imprimirLista(livros);

    int removido = Input.scanInt("Digite o índice do livro a ser removido: ");

    service.remover(removido - 1);
    IO.println("Livro removido com sucesso!");
}

void editarLivro() throws Exception {
    List<Livro> livros = service.listar();
    if (livros.isEmpty()) {
        IO.println("Nenhum livro cadastrado.");
        return;
    }
    imprimirLista(livros);
    
    int editado = Input.scanInt("Digite o índice do livro a ser editado: ");

    if (editado < 1 || editado > livros.size()) {
        IO.println("Índice inválido.");
        return;
    }
    
    Livro livroAtual = livros.get(editado - 1); 

    IO.println("Deixe vazio para manter o valor atual.");

    IO.println("Título atual: " + livroAtual.getTitulo());
    String novoTitulo = IO.readln("Novo título: ");
    if (novoTitulo.isEmpty())
        novoTitulo = livroAtual.getTitulo();

    IO.println("Autor atual: " + livroAtual.getAutor());
    String novoAutor = IO.readln("Novo autor: ");
    if (novoAutor.isEmpty())
        novoAutor = livroAtual.getAutor();

    IO.println("Ano de publicação atual: " + livroAtual.getAnoPublicacao());
    String stringNovoAnoPublicacao = IO.readln("Novo ano de publicação: ");
    int novoAnoPublicacao = stringNovoAnoPublicacao.isEmpty() ? livroAtual.getAnoPublicacao() : Integer.parseInt(stringNovoAnoPublicacao);

    IO.println("Número de páginas atual: " + livroAtual.getNumeroPaginas());
    String stringNovoNumeroPaginas = IO.readln("Novo número de páginas: ");
    int novoNumeroPaginas = stringNovoNumeroPaginas.isEmpty() ? livroAtual.getNumeroPaginas() : Integer.parseInt(stringNovoNumeroPaginas);

    Livro livroEditado = new Livro(novoTitulo, novoAutor, novoAnoPublicacao, novoNumeroPaginas);

    service.editar(editado -1, livroEditado);

    IO.println("Livro editado com sucesso!");
}