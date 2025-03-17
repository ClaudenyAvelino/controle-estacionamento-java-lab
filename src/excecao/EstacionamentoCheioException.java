package excecao;

public class EstacionamentoCheioException extends RuntimeException {
    private String message;
    public EstacionamentoCheioException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}