import java.util.Iterator;

public class PilhaVet<T> implements IPilha<T>{
    private final int tamVetor;
    private T[] V;
    private int vlIncremento;
    private int topo;

    public PilhaVet() {
        tamVetor = 10;
        V = (T[]) new Object[tamVetor];
        topo = 0;
        vlIncremento = 10;

    }

    @Override
    public T top() {
        if (estaVazia()) {  // Verificação adicionada
            return null;
        }
        return V[topo - 1];
    }

    @Override
    public void limpar() {

    }

    @Override
    public int tamanho() {
        return this.topo;
    }

    public void redimencionar() {
        T[] elem = (T[]) new Object[V.length + vlIncremento];
        System.arraycopy(V, 0, elem, 0, V.length);
        V = elem;
    }
    @Override
    public boolean estaVazia() {
        return topo == 0; // Correção importante!
    }

    @Override
    public boolean contem(T elemento) {
        return false;
    }

    @Override
    public int distancia(T elemento) {
        //pilha: 1, 2, 3, 7, 8
        //distancia(3), retorno 2
        return 0;
    }

    @Override
    public T desempilhar() {
        if (estaVazia()) {  // Verificação adicionada
            return null;
        }
        T elemento = this.top();
        V[topo - 1] = null;
        topo--;
        return elemento;
    }

	/*@Override
	public void empilhar(T elemento) {
		if (topo == tamVetor){
			this.redimencionar();
		}
		V[topo] = elemento;
		topo++;
	}*/

    @Override
    public void empilhar(Object elemento) {
        if (topo == tamVetor){
            this.redimencionar();
        }
        V[topo] = (T) elemento;
        topo++;
    }


    @Override
    public String toString() {
        return null;

    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
