class MergeSort<T extends Comparable<T>> implements IOrdenacao<T> {

    @Override
    public void ordenar(T[] vetor) {
        if (vetor == null || vetor.length <= 1) {
            return;
        }
        mergeSort(vetor, 0, vetor.length - 1);
    }

    private void mergeSort(T[] vetor, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(vetor, esq, meio);
            mergeSort(vetor, meio + 1, dir);
            merge(vetor, esq, meio, dir);
        }
    }

    private void merge(T[] vetor, int esq, int meio, int dir) {

        // Tamanhos dos subarrays
        int n1 = meio - esq + 1;
        int n2 = dir - meio;


        T[] E = (T[]) new Comparable[n1];
        T[] D = (T[]) new Comparable[n2];


        for (int i = 0; i < n1; i++) {
            E[i] = vetor[esq + i];
        }
        for (int j = 0; j < n2; j++) {
            D[j] = vetor[meio + 1 + j];
        }

        int i = 0, j = 0, k = esq;
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

        while (i < n1) {
            vetor[k] = E[i];
            i++;
            k++;
        }
        while (j < n2) {
            vetor[k] = D[j];
            j++;
            k++;
        }
    }
}