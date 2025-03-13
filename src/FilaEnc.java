import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilaEnc<T> implements IFila<T>, Iterable<T> {
    int contElementos;
    NoDupEnc<T> noCabeca;

    public FilaEnc() {
        noCabeca = new NoDupEnc<T>(null);
        contElementos = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NoDupEnc<T> atual = noCabeca.getProximo();

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T elemento = atual.getElemento();
                    atual = atual.getProximo();
                    return elemento;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override //remove o anterior, adiciona novo metodo remove
            public void remove() {
                throw new UnsupportedOperationException("Remoção via iterador não permitida");
            }


        };
    }


    @Override
    public void limpar() {
        noCabeca = new NoDupEnc<T>(null);
        contElementos = 0;
    }

    @Override
    public int tamanho() {
        return contElementos;
    }

    @Override
    public boolean estaVazia() {
        return contElementos == 0;
    }

    @Override
    public boolean contem(T elemento) {
        NoDupEnc<T> atual = noCabeca.getProximo();
        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    @Override
    public int distancia(T elemento) {
        int distancia = 0;
        NoDupEnc<T> atual = noCabeca.getProximo();
        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return distancia;
            }
            atual = atual.getProximo();
            distancia++;
        }
        return -1; // Retorna -1 se o elemento não for encontrado
    }

    @Override
    public T remover() {
        if (!estaVazia()) {
            T elemento = getInicio().getElemento();
            if (contElementos == 1) {
                this.limpar();
            } else {
                // Remove o primeiro elemento da fila
                noCabeca.setProximo(getInicio().getProximo());
                contElementos--;
            }
            return elemento;
        }
        return null;
    }

    @Override
    public void enfileirar(T elemento) {
        NoDupEnc<T> novoNo = new NoDupEnc<T>(elemento);
        if (this.estaVazia()) {
            noCabeca.setProximo(novoNo);
            novoNo.setAnterior(noCabeca); // O primeiro elemento aponta de volta para o noCabeca
        } else {
            NoDupEnc<T> fim = getFim();
            fim.setProximo(novoNo);
            novoNo.setAnterior(fim);
        }
        contElementos++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NoDupEnc<T> atual = noCabeca.getProximo(); // Começa após a "cabeça" (noCabeca)
        while (atual != null) {
            sb.append(atual.getElemento()).append(" ");
            atual = atual.getProximo();
        }
        return sb.toString().trim(); // Retorna a string com todos os elementos
    }

    public NoDupEnc<T> getInicio() {
        return noCabeca.getProximo(); // Retorna o primeiro elemento da fila
    }

    public NoDupEnc<T> getFim() {
        NoDupEnc<T> atual = noCabeca;
        while (atual.getProximo() != null) {
            atual = atual.getProximo();
        }
        return atual; // Retorna o último elemento da fila
    }
}