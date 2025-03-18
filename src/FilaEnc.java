import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilaEnc<T> implements IFila<T>, Iterable<T> {

    int contElementos;  // Contador de elementos na fila.
    NoDupEnc<T> noCabeca;  // Nó cabeça, que serve como ponto de partida da fila.

    // Construtor da fila, inicializa com um nó cabeça vazio e contador de elementos igual a 0.
    public FilaEnc() {
        noCabeca = new NoDupEnc<T>(null);  // Nó cabeça que não contém nenhum elemento (é nulo).
        contElementos = 0;  // Inicializa o contador de elementos como 0.
    }

    // Método que retorna um iterador para percorrer os elementos da fila.
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NoDupEnc<T> atual = noCabeca.getProximo();  // Inicia no primeiro nó real após o nó cabeça.

            @Override
            public boolean hasNext() {
                return atual != null;  // Verifica se há mais elementos na fila.
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T elemento = atual.getElemento();  // Obtém o elemento do nó atual.
                    atual = atual.getProximo();  // Avança para o próximo nó.
                    return elemento;
                } else {
                    throw new NoSuchElementException();  // Lança exceção se não houver mais elementos.
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remoção via iterador não permitida");  // Método de remoção não suportado.
            }
        };
    }

    // Método que limpa a fila, reinicializando o nó cabeça e o contador de elementos.
    @Override
    public void limpar() {
        noCabeca = new NoDupEnc<T>(null);  // Reinicializa o nó cabeça.
        contElementos = 0;  // Reseta o contador de elementos.
    }

    // Retorna o número de elementos na fila.
    @Override
    public int tamanho() {
        return contElementos;  // Retorna o contador de elementos.
    }

    // Verifica se a fila está vazia.
    @Override
    public boolean estaVazia() {
        return contElementos == 0;  // A fila está vazia se o contador de elementos for 0.
    }

    // Verifica se um elemento está na fila.
    @Override
    public boolean contem(T elemento) {
        NoDupEnc<T> atual = noCabeca.getProximo();  // Começa após o nó cabeça.
        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return true;  // Retorna true se o elemento for encontrado.
            }
            atual = atual.getProximo();  // Avança para o próximo nó.
        }
        return false;  // Retorna false se o elemento não for encontrado.
    }

    // Retorna a posição do elemento na fila (distância até o início).
    @Override
    public int distancia(T elemento) {
        int distancia = 0;  // Inicializa a distância.
        NoDupEnc<T> atual = noCabeca.getProximo();  // Começa após o nó cabeça.
        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return distancia;  // Retorna a distância se o elemento for encontrado.
            }
            atual = atual.getProximo();  // Avança para o próximo nó.
            distancia++;  // Incrementa a distância.
        }
        return -1;  // Retorna -1 se o elemento não for encontrado.
    }

    // Remove o primeiro elemento da fila e retorna seu valor.
    @Override
    public T remover() {
        if (!estaVazia()) {
            T elemento = getInicio().getElemento();  // Obtém o elemento no início da fila.
            if (contElementos == 1) {
                this.limpar();  // Se for o único elemento, limpa a fila.
            } else {
                // Remove o primeiro elemento da fila, ajustando a referência do nó cabeça.
                noCabeca.setProximo(getInicio().getProximo());
                contElementos--;  // Decrementa o contador de elementos.
            }
            return elemento;  // Retorna o elemento removido.
        }
        return null;  // Retorna null se a fila estiver vazia.
    }

    // Adiciona um novo elemento no final da fila.
    @Override
    public void enfileirar(T elemento) {
        NoDupEnc<T> novoNo = new NoDupEnc<T>(elemento);  // Cria um novo nó com o elemento.

        if (this.estaVazia()) {
            noCabeca.setProximo(novoNo);  // Se a fila estiver vazia, o novo nó aponta para o primeiro elemento.
            novoNo.setAnterior(noCabeca);  // O primeiro elemento aponta de volta para o nó cabeça.
        } else {
            NoDupEnc<T> fim = getFim();  // Obtém o último nó da fila.
            fim.setProximo(novoNo);  // O último nó aponta para o novo nó.
            novoNo.setAnterior(fim);  // O novo nó aponta de volta para o nó anterior.
        }
        contElementos++;  // Incrementa o contador de elementos.
    }

    // Converte a fila em uma string, exibindo todos os seus elementos.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NoDupEnc<T> atual = noCabeca.getProximo();  // Começa após o nó cabeça.
        while (atual != null) {
            sb.append(atual.getElemento()).append(" ");  // Adiciona o elemento do nó atual à string.
            atual = atual.getProximo();  // Avança para o próximo nó.
        }
        return sb.toString().trim();  // Retorna a string com todos os elementos.
    }

    // Método auxiliar para obter o primeiro nó da fila.
    public NoDupEnc<T> getInicio() {
        return noCabeca.getProximo();  // Retorna o primeiro nó real da fila.
    }

    // Método auxiliar para obter o último nó da fila.
    public NoDupEnc<T> getFim() {
        NoDupEnc<T> atual = noCabeca;
        while (atual.getProximo() != null) {
            atual = atual.getProximo();  // Percorre até o último nó.
        }
        return atual;  // Retorna o último nó.
    }
}
