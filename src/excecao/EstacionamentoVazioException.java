package excecao;

public class EstacionamentoVazioException extends RuntimeException {
   private String message;
    public EstacionamentoVazioException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
