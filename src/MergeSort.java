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
        mergeSort(vetor, 0, vetor.length - 1); // Chama o método recursivo para ordenar o array.
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

            // Ordena a primeira metade do array (do índice esq ao meio).
            mergeSort(vetor, esq, meio);

            // Ordena a segunda metade do array (do índice meio+1 ao índice dir).
            mergeSort(vetor, meio + 1, dir);

            // Junta as duas metades ordenadas no array original.
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

        // Tamanhos dos subarrays esquerdo e direito.
        int n1 = meio - esq + 1;  // Tamanho do subarray esquerdo.
        int n2 = dir - meio;      // Tamanho do subarray direito.

        // Cria arrays temporários para armazenar as duas metades do array original.
        T[] E = (T[]) new Comparable[n1]; // Subarray esquerdo.
        T[] D = (T[]) new Comparable[n2]; // Subarray direito.

        // Copia os elementos para os arrays temporários.
        for (int i = 0; i < n1; i++) {
            E[i] = vetor[esq + i]; // Copia os elementos da primeira metade.
        }
        for (int j = 0; j < n2; j++) {
            D[j] = vetor[meio + 1 + j]; // Copia os elementos da segunda metade.
        }

        // Índices para percorrer os arrays temporários.
        int i = 0, j = 0, k = esq; // i para o subarray E, j para o subarray D, k para o array original.

        // Junta os arrays E e D no array original em ordem crescente.
        while (i < n1 && j < n2) {
            if (E[i].compareTo(D[j]) <= 0) {  // Se o elemento de E for menor ou igual ao de D.
                vetor[k] = E[i];  // Coloca o elemento de E no array original.
                i++;  // Avança no subarray E.
            } else {
                vetor[k] = D[j];  // Coloca o elemento de D no array original.
                j++;  // Avança no subarray D.
            }
            k++;  // Avança no array original.
        }

        // Copia os elementos restantes de E, se houver.
        while (i < n1) {
            vetor[k] = E[i];  // Coloca os elementos restantes de E no array original.
            i++;  // Avança no subarray E.
            k++;  // Avança no array original.
        }

        // Copia os elementos restantes de D, se houver.
        while (j < n2) {
            vetor[k] = D[j];  // Coloca os elementos restantes de D no array original.
            j++;  // Avança no subarray D.
            k++;  // Avança no array original.
        }
    }
}
