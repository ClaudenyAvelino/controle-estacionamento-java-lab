/**
* Uma interface de ordenação genérica para um algoritmo de ordenação básico 
* A implementação deve ordenar o vetor fornecido por parâmetro.
*
* @param <T> o tipo de elementos no vetor
*/
public interface IOrdenacao<T> {

    /**
    * Ordena o vetor especificado em ordem crescente usando um algoritmo de ordenação básico
    *
    * @param vetor o vetor a ser ordenado
    * @throws IllegalArgumentException se o vetor for nulo
    */
    void ordenar(T[] vetor);

}
