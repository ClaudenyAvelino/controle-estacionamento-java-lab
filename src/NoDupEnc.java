public class NoDupEnc<T> {

    private T elemento;
    private NoDupEnc<T> proximo;
    private NoDupEnc<T> anterior;

    public NoDupEnc(T elemento) {
        this.elemento = elemento;
        this.proximo = null;
        this.anterior = null;
    }

    public NoDupEnc(T elemento, NoDupEnc<T> proximoNo,NoDupEnc<T> noAnterior) {
        this.elemento = elemento;
        this.proximo = proximoNo;
        this.anterior = noAnterior;

    }

    public NoDupEnc<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NoDupEnc<T> anterior) {
        this.anterior = anterior;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NoDupEnc<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoDupEnc<T> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return "" + getElemento();
    }

}
