public class Estacionamento implements IEstacionamento  {

    private PilhaVet<ICarro> vagas;
    private FilaEnc<ICarro> filaEspera;
    private ICarro[] carrosEstacionados;
    private int numCarrosEstacionados;
    private final int MAX_VAGAS;

    public Estacionamento(int maxVagas) {
        this.MAX_VAGAS = maxVagas;
        this.vagas = new PilhaVet<>();
        this.filaEspera = new FilaEnc<>();
        this.carrosEstacionados = new ICarro[maxVagas]; // Inicializa com o número máximo de vagas
        this.numCarrosEstacionados = 0;
    }

    // Redimensiona o array de carros estacionados
    private void redimensionarCarrosEstacionados() {
        int novoTamanho = carrosEstacionados.length * 2;
        ICarro[] novoArray = new ICarro[novoTamanho];
        for (int i = 0; i < numCarrosEstacionados; i++) {
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
    public boolean placaJaExiste(String placa) {
        // Verifica no estacionamento (array de carros)
        for (ICarro carro : carrosEstacionados) {
            if (carro != null && carro.getPlaca().equals(placa)) {
                return true;
            }
        }

        // Verifica na fila de espera utilizando o iterator da FilaEnc
        for (ICarro carro : filaEspera) { // Usando iterator implicitamente
            if (carro != null && carro.getPlaca().equals(placa)) {
                return true;
            }
        }

        return false;
    }



    // Método para estacionar um carro
    public void estacionarCarro(ICarro carro) {
        if (placaJaExiste(carro.getPlaca())) {
            System.out.println("Erro: Já existe um carro com a placa " + carro.getPlaca() + " no estacionamento ou na fila de espera.");
            return;
        }

        // Se o estacionamento não estiver cheio, estaciona o carro
        if (numCarrosEstacionados < MAX_VAGAS) {
            // Encontrando a primeira vaga disponível no array de carros estacionados
            for (int i = 0; i < carrosEstacionados.length; i++) {
                if (carrosEstacionados[i] == null) {
                    carrosEstacionados[i] = carro;
                    numCarrosEstacionados++;
                    System.out.println("Carro " + carro.getPlaca() + " estacionado.");
                    return;
                }
            }
        } else {
            // Se estiver cheio, coloca o carro na fila de espera
            filaEspera.enfileirar(carro);
            System.out.println("Carro " + carro.getPlaca() + " na fila de espera.");
        }
    }

    // Método para remover um carro
    @Override
    public void removerCarro(String placa) {
        ICarro carroRemovido = null;

        // Remove o carro do estacionamento
        for (int i = 0; i < carrosEstacionados.length; i++) {
            if (carrosEstacionados[i] != null && carrosEstacionados[i].getPlaca().equals(placa)) {
                carroRemovido = carrosEstacionados[i];
                carrosEstacionados[i] = null;
                numCarrosEstacionados--;
                System.out.println("Carro " + placa + " removido.");
                break;
            }
        }

        // Se o carro não foi encontrado, retorna
        if (carroRemovido == null) {
            System.out.println("Carro " + placa + " não encontrado no estacionamento.");
            return;
        }

        // Após a remoção, coloca o próximo carro da fila na vaga, se houver
        if (!filaEspera.estaVazia()) {
            ICarro carroFila = filaEspera.remover();
            estacionarCarro(carroFila);
        }
    }

    // Método para gerar o relatório diário
    @Override
    public void relatorioDiario() {
        if (numCarrosEstacionados == 0) {
            System.out.println("Não há carros estacionados para gerar relatório.");
            return;
        }

        // Cópia manual do array de carros estacionados (sem Arrays.copyOf())
        ICarro[] carrosParaOrdenar = new ICarro[numCarrosEstacionados];
        for (int i = 0; i < numCarrosEstacionados; i++) {
            carrosParaOrdenar[i] = carrosEstacionados[i];
        }

        // Ordenação dos carros com MergeSort
        MergeSort<ICarro> mergeSort = new MergeSort<>();
        mergeSort.ordenar(carrosParaOrdenar);

        // Imprime o relatório ordenado
        System.out.println("Relatório Diário:");
        for (ICarro carro : carrosParaOrdenar) {
            System.out.println("Placa: " + carro.getPlaca() +
                    ", Chegada: " + carro.getHorarioChegada() +
                    ", Saída: " + (carro.getHorarioSaida() == 0 ? "Ainda estacionado" : carro.getHorarioSaida()));
        }
    }
}
