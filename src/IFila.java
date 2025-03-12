import java.util.Iterator;

public interface IFila<T> {

    // Limpa toda a fila
    public void limpar();

    // tamanho de elementos que a FILA possui
    public int tamanho();

    // Retorna se a fila esta ou nao vazia
    public boolean estaVazia();

    // Indica se a fila possui o elemento
    public boolean contem(T elemento);

    //retorna a distancia de um objeto em rela�‹o ao in’cio da fila
    //o vizinho mais proximo possui distancia 1 do in’cio
    public int distancia(T elemento);

    // Remove o primeira da fila 
    public T remover();

    // Adiciona um elemento no final da fila
    public void enfileirar(T elemento);

    public String toString();

    public Iterator<T> iterator();
}