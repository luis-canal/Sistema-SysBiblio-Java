public class LivroDigital extends Livro {
    private double tamanhoArquivo;
    private String formatoArquivo;

    public LivroDigital(String titulo, String autor, int anoPublicacao, int numeroPaginas, double tamanhoArquivo,
            String formatoArquivo) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.tamanhoArquivo = tamanhoArquivo;
        this.formatoArquivo = formatoArquivo;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    @Override
    public String toString() {
        String texto = super.toString();
        return texto
                + "| N. exemplares: " + this.tamanhoArquivo
                + "| Dimensões: " + this.formatoArquivo;
    }
}
