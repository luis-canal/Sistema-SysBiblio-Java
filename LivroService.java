import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class LivroService {
    private List<Livro> acervo = new ArrayList<>();

    public void cadastrar(Livro novoLivro) throws Exception {
        validarLivro(novoLivro, -1);

        // nesta parte estaria chamando a camada Repository

        acervo.add(novoLivro);
    }

    public List<Livro> listar() {
        return acervo;
    }

    public List<Livro> pesquisarTitulo(String titulo) {
        List<Livro> encontrados = new ArrayList<>();
        titulo = titulo.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getTitulo().contains(titulo))
                encontrados.add(livro);

        }

        return encontrados;
    }

    public List<Livro> pesquisarAutor(String autor) {
        List<Livro> encontrados = new ArrayList<>();
        autor = autor.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getAutor().contains(autor))
                encontrados.add(livro);

        }

        return encontrados;
    }

    public List<Livro> pesquisarAno(int ano) {
        List<Livro> encontrados = new ArrayList<>();

        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() == ano)
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

        validarLivro(livroEditado, indice);
        acervo.set(indice, livroEditado);
    }

    private void validarLivro(Livro livro, int indiceIgnorado) throws Exception {
        if (livro == null)
            throw new Exception("Objeto nulo.");

        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty())
            throw new Exception("Título inválido.");
        livro.setTitulo(livro.getTitulo().trim().toUpperCase());

        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty())
            throw new Exception("Autor inválido.");
        livro.setAutor(livro.getAutor().trim().toUpperCase());

        if (livro.getAnoPublicacao() < 1900 || livro.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano de publicação inválido.");

        for (int i = 0; i < acervo.size(); i++) {
            Livro l = acervo.get(i);

            if (i != indiceIgnorado &&
                    l.getTitulo().equalsIgnoreCase(livro.getTitulo()) &&
                    l.getAutor().equalsIgnoreCase(livro.getAutor()) &&
                    l.getAnoPublicacao() == livro.getAnoPublicacao()) {
                        throw new Exception("Já existe um livro com esses dados.");
            }
        }

    }
}