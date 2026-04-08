import java.util.List;

LivroService service = new LivroService();

void main() {
    String menu = """
            ===== Sistema da bibliotéca =====
            1- Cadastrar livro
            2- Listar livro
            3- Pesquisar livro
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
                case 0 -> {
                    IO.println("Saindo...");
                    break;
                }
                default -> IO.println("Opção inválida");
            }
        } catch (Exception e) {
            IO.println("Erro: " + e.getMessage());
        }
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
    String pesquisa = Input.scanString("Digite o título: ");

    List<Livro> livros = service.pesquisar(pesquisa);

    imprimirLista(livros);
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