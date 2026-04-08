import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

List<Livro> acervo = new ArrayList<>();

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

    if (novoLivro.getAnoPublicacao() < 1900 || novoLivro.getAnoPublicacao() > LocalDate.now().getYear())
        throw new Exception("Ano de publicação inválido");

    for (Livro livro : acervo) {
        if (livro.getTitulo().equalsIgnoreCase(novoLivro.getTitulo()) && livro.getAutor().equalsIgnoreCase(novoLivro.getAutor()) && livro.getAnoPublicacao() == novoLivro.getAnoPublicacao())
            throw new Exception("Já existe livro cadastrado com esse Título, Autor e ano de publicação.");
    }

    acervo.add(novoLivro);
    IO.println("Livro cadastrado com sucesso.");
}

void listar() {
    if (acervo.isEmpty()) {
        IO.println("Nenhum livro cadastrado.");
        return;
    }
    int i = 1;
    for (Livro livro : acervo) {
        IO.println(i++ + " - " + livro);
    }
}

void pesquisar() {
    String pesquisa = Input.scanString("Digite o título: ");
    int i = 1;
    boolean encontrou = false;
    for (Livro livro : acervo) {
        if (livro.getTitulo().toLowerCase().contains(pesquisa.toLowerCase())){
            IO.println(i++ + " - " + livro);
            encontrou = true;
        }
    }
    if (!encontrou)
        IO.println("Não encontrado.");
}