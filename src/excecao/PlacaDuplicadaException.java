package excecao;

public class PlacaDuplicadaException extends RuntimeException {
    private String message;
    public PlacaDuplicadaException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
