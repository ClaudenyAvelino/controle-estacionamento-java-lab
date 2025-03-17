/**
 * Implementação do algoritmo Merge Sort para ordenação de arrays.
 *
 * @param <T> Tipo genérico que deve implementar Comparable para permitir comparação.
 */
class MergeSort<T extends Comparable<T>> implements IOrdenacao<T> {

    /**
     * Método público que inicia a ordenação do array usando Merge Sort.
     *
     * @param vetor Array a ser ordenado.
     */
    @Override
    public void ordenar(T[] vetor) {
        if (vetor == null || vetor.length <= 1) {
            // Verifica se o array é nulo ou tem 0 ou 1 elemento (já ordenado por definição).
            return;
        }
        mergeSort(vetor, 0, vetor.length - 1);
    }

    /**
     * Método recursivo que divide o array em subarrays menores.
     *
     * @param vetor Array a ser ordenado.
     * @param esq   Índice esquerdo do subarray.
     * @param dir   Índice direito do subarray.
     */
    private void mergeSort(T[] vetor, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2; // Calcula o ponto médio do array.

            // Ordena a primeira metade.
            mergeSort(vetor, esq, meio);

            // Ordena a segunda metade.
            mergeSort(vetor, meio + 1, dir);

            // Junta as duas metades ordenadas.
            merge(vetor, esq, meio, dir);
        }
    }

    /**
     * Método que junta duas metades ordenadas do array.
     *
     * @param vetor Array original.
     * @param esq   Índice inicial da primeira metade.
     * @param meio  Índice do meio, ponto final da primeira metade.
     * @param dir   Índice final da segunda metade.
     */
    private void merge(T[] vetor, int esq, int meio, int dir) {

        // Tamanhos dos subarrays.
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        // Cria arrays temporários para armazenar as duas metades.
        T[] E = (T[]) new Comparable[n1]; // Subarray esquerdo.
        T[] D = (T[]) new Comparable[n2]; // Subarray direito.

        // Copia os elementos para os arrays temporários.
        for (int i = 0; i < n1; i++) {
            E[i] = vetor[esq + i];
        }
        for (int j = 0; j < n2; j++) {
            D[j] = vetor[meio + 1 + j];
        }

        int i = 0, j = 0, k = esq;

        // Junta os arrays E e D de volta no array original, em ordem.
        while (i < n1 && j < n2) {
            if (E[i].compareTo(D[j]) <= 0) {
                vetor[k] = E[i];
                i++;
            } else {
                vetor[k] = D[j];
                j++;
            }
            k++;
        }

        // Copia elementos restantes de E, se houver.
        while (i < n1) {
            vetor[k] = E[i];
            i++;
            k++;
        }

        // Copia elementos restantes de D, se houver.
        while (j < n2) {
            vetor[k] = D[j];
            j++;
            k++;
        }
    }
}
