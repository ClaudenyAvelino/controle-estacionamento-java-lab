import java.util.Iterator;

public class PilhaVet<T> implements IPilha<T> {
    private final int tamVetor;
    private T[] V;
    private int vlIncremento;
    private int topo;

    public PilhaVet(int MAX_VAGAS) {
        tamVetor = 10; // Tamanho inicial do vetor
        V = (T[]) new Object[tamVetor];
        topo = 0;
        vlIncremento = 10; // Tamanho do incremento ao redimensionar
    }

    @Override
    public T top() {
        if (estaVazia()) {  // Verifica se a pilha está vazia
            return null;
        }
        return V[topo - 1];  // Retorna o elemento no topo da pilha
    }

    @Override
    public void limpar() {
        for (int i = 0; i < topo; i++) {
            V[i] = null; // Limpa os elementos da pilha
        }
        topo = 0; // Reseta o topo da pilha
    }

    @Override
    public int tamanho() {
        return this.topo;  // Retorna o número de elementos na pilha
    }

    public void redimensionar() {
        int novoTamanho = V.length + vlIncremento;
        T[] novoArray = (T[]) new Object[novoTamanho];

        // Cópia manual dos elementos da pilha para o novo array
        for (int i = 0; i < topo; i++) {
            novoArray[i] = V[i];
        }

        V = novoArray;  // Atualiza a referência da pilha com o novo array redimensionado
    }

    @Override
    public boolean estaVazia() {
        return topo == 0;  // Retorna verdadeiro se a pilha estiver vazia (topo == 0)
    }

    @Override
    public boolean contem(T elemento) {
        for (int i = 0; i < topo; i++) {
            if (V[i].equals(elemento)) {
                return true;  // Retorna verdadeiro se o elemento for encontrado
            }
        }
        return false;  // Retorna falso caso o elemento não seja encontrado
    }

    @Override
    public int distancia(T elemento) {
        for (int i = 0; i < topo; i++) {
            if (V[i].equals(elemento)) {
                return topo - i - 1;  // Retorna a distância do topo até o elemento encontrado
            }
        }
        return -1;  // Retorna -1 se o elemento não for encontrado
    }

    @Override
    public T desempilhar() {
        if (estaVazia()) {  // Verifica se a pilha está vazia
            return null;
        }
        T elemento = this.top();  // Obtém o topo da pilha
        V[topo - 1] = null;  // Limpa o elemento removido
        topo--;  // Decrementa o topo da pilha
        return elemento;  // Retorna o elemento desempilhado
    }

    @Override
    public void empilhar(T elemento) {
        if (topo == V.length) {  // Verifica se a pilha atingiu o seu tamanho máximo
            this.redimensionar();  // Redimensiona a pilha se necessário
        }
        V[topo] = elemento;  // Empilha o novo elemento no topo
        topo++;  // Incrementa o topo após empilhar o elemento
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < topo; i++) {
            sb.append(V[i]);
            if (i < topo - 1) {
                sb.append(", ");  // Adiciona vírgula entre os elementos, exceto no último
            }
        }
        sb.append("]");
        return sb.toString();  // Retorna a representação da pilha em formato de string
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < topo;  // Retorna verdadeiro se houver mais elementos
            }

            @Override
            public T next() {
                return V[currentIndex++];  // Retorna o próximo elemento e incrementa o índice
            }
        };
    }
}
