import java.util.Iterator;

public interface IPilha<T> {

    // Consulta o topo da pilha sem remove-la e retorna o topo
    public T top();

    // Limpa toda a pilha
    public void limpar();

    // tamanho de elementos que a pilha possui
    public int tamanho();

    // Retorna se a pilha esta ou nao vazia
    public boolean estaVazia();

    // Indica se a pilha possui o elemento
    public boolean contem(T elemento);

    // retorna a distancia de um objeto em relacao ao topo
    // o vizinho mais proximo possui distancia 1 do topo
    // se o elemento for o proprio topo, entao retorna-se 0
    // caso o elemento nao exista, retorna-se -1
    public int distancia(T elemento);

    // Remove o topo da pilha e retorna o objeto removido
    public T desempilhar();

    // Adiciona um elemento no topo da pilha
    public void empilhar(T elemento);

    // imprime o topo no final - tanto no vetor, quanto na listEnc
    public String toString();

    Iterator<T> iterator();

}
