public class NoDupEnc<T> {

    // Variáveis de instância que armazenam o elemento, o próximo nó e o nó anterior
    private T elemento;  // O elemento armazenado no nó
    private NoDupEnc<T> proximo;  // Referência para o próximo nó da lista
    private NoDupEnc<T> anterior;  // Referência para o nó anterior da lista

    // Construtor que cria um nó com o elemento e sem referências para o próximo e anterior
    public NoDupEnc(T elemento) {
        this.elemento = elemento;  // Inicializa o elemento do nó
        this.proximo = null;  // O próximo nó é inicialmente null
        this.anterior = null;  // O nó anterior também é inicialmente null
    }

    // Construtor que cria um nó com o elemento, próximo nó e nó anterior
    public NoDupEnc(T elemento, NoDupEnc<T> proximoNo, NoDupEnc<T> noAnterior) {
        this.elemento = elemento;  // Inicializa o elemento do nó
        this.proximo = proximoNo;  // Inicializa a referência para o próximo nó
        this.anterior = noAnterior;  // Inicializa a referência para o nó anterior
    }

    // Método que retorna o nó anterior
    public NoDupEnc<T> getAnterior() {
        return anterior;  // Retorna a referência para o nó anterior
    }

    // Método que define o nó anterior
    public void setAnterior(NoDupEnc<T> anterior) {
        this.anterior = anterior;  // Define o nó anterior
    }

    // Método que retorna o elemento armazenado no nó
    public T getElemento() {
        return elemento;  // Retorna o elemento do nó
    }

    // Método que define o elemento armazenado no nó
    public void setElemento(T elemento) {
        this.elemento = elemento;  // Define o novo valor para o elemento
    }

    // Método que retorna o próximo nó
    public NoDupEnc<T> getProximo() {
        return proximo;  // Retorna a referência para o próximo nó
    }

    // Método que define o próximo nó
    public void setProximo(NoDupEnc<T> proximo) {
        this.proximo = proximo;  // Define o próximo nó
    }

    // Método que retorna uma representação em string do nó, exibindo apenas o elemento
    @Override
    public String toString() {
        return "" + getElemento();  // Retorna o elemento do nó como string
    }

}
