import java.util.Iterator;

public class PilhaVet<T> implements IPilha<T> {
    private final int tamVetor;
    private T[] V;
    private int vlIncremento;
    private int topo;

    public PilhaVet() {
        tamVetor = 10; // Tamanho inicial do vetor
        V = (T[]) new Object[tamVetor];
        topo = 0;
        vlIncremento = 10; // Tamanho do incremento ao redimensionar
    }

    @Override
    public T top() {
        if (estaVazia()) {  // Verificação se a pilha está vazia
            return null;
        }
        return V[topo - 1];  // Retorna o topo da pilha
    }

    @Override
    public void limpar() {
        for (int i = 0; i < topo; i++) {
            V[i] = null; // Limpa os elementos da pilha
        }
        topo = 0; // Reseta o topo
    }

    @Override
    public int tamanho() {
        return this.topo;  // Retorna o número de elementos na pilha
    }

    public void redimensionar() {
        int novoTamanho = V.length + vlIncremento;
        T[] novoArray = (T[]) new Object[novoTamanho];

        // Cópia manual dos elementos (sem System.arraycopy)
        for (int i = 0; i < topo; i++) {
            novoArray[i] = V[i];
        }


        V = novoArray;
    }

    @Override
    public boolean estaVazia() {
        return topo == 0;  // A pilha está vazia se o topo for 0
    }

    @Override
    public boolean contem(T elemento) {
        for (int i = 0; i < topo; i++) {
            if (V[i].equals(elemento)) {
                return true;  // Retorna true se o elemento for encontrado
            }
        }
        return false;  // Caso contrário, retorna false
    }

    @Override
    public int distancia(T elemento) {
        for (int i = 0; i < topo; i++) {
            if (V[i].equals(elemento)) {
                return topo - i - 1;  // Retorna a distância do topo
            }
        }
        return -1;  // Se o elemento não for encontrado, retorna -1
    }

    @Override
    public T desempilhar() {
        if (estaVazia()) {  // Verifica se a pilha está vazia
            return null;
        }
        T elemento = this.top();  // Pega o topo da pilha
        V[topo - 1] = null;  // Limpa o elemento removido
        topo--;  // Decrementa o topo
        return elemento;  // Retorna o elemento desempilhado
    }

    @Override
    public void empilhar(T elemento) {
        if (topo == V.length) {  // Verifica se a pilha está cheia
            this.redimensionar();  // Redimensiona a pilha se necessário
        }
        V[topo] = elemento;  // Empilha o novo elemento
        topo++;  // Incrementa o topo
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < topo; i++) {
            sb.append(V[i]);
            if (i < topo - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();  // Converte a pilha em uma string legível
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < topo;
            }

            @Override
            public T next() {
                return V[currentIndex++];
            }
        };
    }
}
