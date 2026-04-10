import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class LivroService {
    private List<Livro> acervo = new ArrayList<>();

    public void cadastrar(Livro novoLivro) throws Exception {
        if (novoLivro == null)
            throw new Exception("objeto nulo.");

        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty())
            throw new Exception("Título inválido.");
        novoLivro.setTitulo(novoLivro.getTitulo().trim().toUpperCase());

        if (novoLivro.getAutor() == null || novoLivro.getAutor().isEmpty())
            throw new Exception("Autor inválido.");
        novoLivro.setAutor(novoLivro.getAutor().trim().toUpperCase());

        if (novoLivro.getAnoPublicacao() < 1900 || novoLivro.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano de publicação inválido");

        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(novoLivro.getTitulo())
                    && livro.getAutor().equalsIgnoreCase(novoLivro.getAutor())
                    && livro.getAnoPublicacao() == novoLivro.getAnoPublicacao())
                throw new Exception("Já existe livro cadastrado com esse Título, Autor e ano de publicação.");
        }

        // nesta parte estaria chamando a camada Repository

        acervo.add(novoLivro);
    }

    public List<Livro> listar() {
        return acervo;
    }

    public List<Livro> pesquisar(String titulo) {
        List<Livro> encontrados = new ArrayList<>();
        titulo = titulo.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getTitulo().contains(titulo))
                encontrados.add(livro);

        }

        return encontrados;
    }

    public void remover(int removido) throws Exception {
        if (removido < 0 || removido >= acervo.size())
            throw new Exception("Índice inválido");
        acervo.remove(removido);
    }

    public void editar(int indice, Livro livroEditado) throws Exception {
        if (indice < 0 || indice >= acervo.size())
            throw new Exception("Índice inválido.");

        for (int i = 0; i < acervo.size(); i++) {
            Livro livro = acervo.get(i);

            if (i != indice
                    && livro.getTitulo().equalsIgnoreCase(livroEditado.getTitulo())
                    && livro.getAutor().equalsIgnoreCase(livroEditado.getAutor())
                    && livro.getAnoPublicacao() == livroEditado.getAnoPublicacao()) {
                        throw new Exception("Já existe um livro com esses dados.");
                    }
        }
        acervo.set(indice, livroEditado);
    }
}