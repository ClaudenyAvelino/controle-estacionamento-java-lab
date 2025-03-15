public class Estacionamento implements IEstacionamento {

    private PilhaVet<ICarro> vagas;
    private FilaEnc<ICarro> filaEspera;
    private ICarro[] carrosEstacionados;
    private int numCarrosEstacionados;
    private final int MAX_VAGAS;
    private int horarioChegada = 1;
    private int horarioSaida = 1;

    public Estacionamento(int maxVagas) {
        this.MAX_VAGAS = maxVagas;
        this.vagas = new PilhaVet<>(MAX_VAGAS);
        this.filaEspera = new FilaEnc<>();
        this.carrosEstacionados = new ICarro[maxVagas];
        this.numCarrosEstacionados = 0;
    }

    // Redimensiona o array carrosEstacionados sem usar Arrays.copyOf()
    private void redimensionarCarrosEstacionados() {
        int novoTamanho = carrosEstacionados.length * 2;
        ICarro[] novoArray = new ICarro[novoTamanho];
        for (int i = 0; i < numCarrosEstacionados; i++) { // Copia manualmente os elementos
            novoArray[i] = carrosEstacionados[i];
        }
        carrosEstacionados = novoArray;
    }

    public void mensagemBoasVnindas() {
        String mensagemInicial = """
                ************************************
                **********Seja bem vindo!**********
                ************************************
                """;
        System.out.print(mensagemInicial);
    }

    public void opcoesMenu() {
        String menuOpcoes = """
                Opções:
                1. Estacionar carro
                2. Remover carro
                3. Gerar relatório
                4. Sair
                Escolha uma opção:
                """;
        System.out.print(menuOpcoes);
    }


    // Verifica se a placa já existe no estacionamento ou na fila de espera
    boolean placaJaExiste(String placa) {
        for (ICarro carro : carrosEstacionados) {
            if (carro != null && carro.getHorarioSaida() == 0 && carro.getPlaca().equals(placa)) {
                return true;
            }
        }
        for (ICarro carro : filaEspera) {
            if (carro != null && carro.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    // Método para estacionar um carro
    @Override
    public void estacionarCarro(ICarro carro) {
        if (placaJaExiste(carro.getPlaca())) {
            System.out.println("Erro: Já existe um carro com a placa " + carro.getPlaca() + " no estacionamento ou na fila de espera.");
            return;
        }

        if (vagas.tamanho() < MAX_VAGAS) {
            if (numCarrosEstacionados == carrosEstacionados.length) {
                redimensionarCarrosEstacionados();
            }
            vagas.empilhar(carro);
            carro.setHorarioChegada(horarioChegada++);
            carrosEstacionados[numCarrosEstacionados++] = carro;
            System.out.println("Carro " + carro.getPlaca() + " estacionado.");
        } else {
            filaEspera.enfileirar(carro);
            System.out.println("Carro " + carro.getPlaca() + " na fila de espera.");
        }
    }

    // Método para remover um carro
    @Override
    public void removerCarro(String placa) {
        if (vagas.estaVazia()) {
            System.out.println("O estacionamento está vazio.");
            return;
        }

        PilhaVet<ICarro> temp = new PilhaVet<>(MAX_VAGAS);
        ICarro carroRemovido = null;

        while (!vagas.estaVazia()) {
            ICarro carroAtual = vagas.desempilhar();
            if (carroAtual != null && carroAtual.getPlaca().equals(placa)) {
                carroRemovido = carroAtual;
                carroRemovido.setHorarioSaida(horarioSaida++);
                break;
            }
            temp.empilhar(carroAtual);
        }

        if (carroRemovido != null) {
            System.out.println("Carro " + carroRemovido.getPlaca() + " removido.");

            if (!filaEspera.estaVazia() && vagas.tamanho() < MAX_VAGAS) {
                estacionarCarro(filaEspera.remover());
            } else if (!filaEspera.estaVazia()) {
                System.out.println("Há carros na fila de espera, mas o estacionamento está cheio.");
            } else {
                System.out.println("Vaga liberada, mas a fila de espera está vazia.");
            }
        } else {
            System.out.println("Carro com placa " + placa + " não encontrado.");
        }


        // Restaura a pilha 'vagas'
        while (!temp.estaVazia()) {
            vagas.empilhar(temp.desempilhar());
        }

    }


    // Método para gerar o relatório diário
    @Override
    public void relatorioDiario() {
        if (numCarrosEstacionados == 0 && filaEspera.estaVazia()) {
            System.out.println("Não há carros para gerar relatório.");
            return;
        }

        // 1. Cria uma cópia do array carrosEstacionados para a ordenação, apenas com os carros que estão atualmente no estacionamento
        int carrosEstacionadosAtualmente = 0;
        for (ICarro carro : carrosEstacionados) {
            if (carro != null && carro.getHorarioSaida() == 0) {
                carrosEstacionadosAtualmente++;
            }
        }
        ICarro[] carrosParaOrdenar = new ICarro[carrosEstacionadosAtualmente];
        int indiceNaoNulos = 0;
        for (int i = 0; i < carrosEstacionados.length; i++) {
            if (carrosEstacionados[i] != null && carrosEstacionados[i].getHorarioSaida() == 0) {

                carrosParaOrdenar[indiceNaoNulos++] = carrosEstacionados[i];
            }

        }

        // 2. Ordena a cópia usando MergeSort (se não for vazia)
        if (carrosParaOrdenar.length > 0) {
            MergeSort<ICarro> mergeSort = new MergeSort<>();

            mergeSort.ordenar(carrosParaOrdenar);
        }

        // 3. Imprime o relatório dos carros estacionados
        System.out.println("Relatório Diário - Carros Estacionados:");
        for (ICarro carro : carrosParaOrdenar) {

            System.out.println("Placa: " + carro.getPlaca() +
                    ", Chegada: " + carro.getHorarioChegada() +
                    ", Saída: " + (carro.getHorarioSaida() == 0 ? "Ainda estacionado" : carro.getHorarioSaida()));
        }

        // 4. Imprime o histórico dos carros que já saíram
        System.out.println("\nHistórico de Carros que Saíram:");
        for (ICarro carro : carrosEstacionados) {
            if (carro != null && carro.getHorarioSaida() != 0) {
                System.out.println("Placa: " + carro.getPlaca() +
                        ", Chegada: " + carro.getHorarioChegada() +
                        ", Saída: " + carro.getHorarioSaida());
            }

        }

        // 5. Imprime os carros na fila de espera
        if (!filaEspera.estaVazia()) {
            System.out.println("\nCarros na fila de espera:");
            for (ICarro carro : filaEspera) {
                System.out.println("Placa: " + carro.getPlaca());
            }

        } else if (carrosEstacionadosAtualmente == 0) {
            System.out.println("Sem carros estacionados, mas há carros na fila de espera.");
        }
    }
}
