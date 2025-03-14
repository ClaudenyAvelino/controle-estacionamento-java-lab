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
            if (carro != null && carro.getPlaca().equals(placa)) {
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
            // Redimensiona o array se necessário, ANTES de adicionar o carro
            if (numCarrosEstacionados == carrosEstacionados.length) {
                redimensionarCarrosEstacionados();
            }

            vagas.empilhar(carro);
            carro.setHorarioChegada(horarioChegada++);
            carrosEstacionados[numCarrosEstacionados++] = carro; // Adiciona ao array após o redimensionamento
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


        PilhaVet<ICarro> temp = new PilhaVet<>(MAX_VAGAS); // Pilha temporária com tamanho fixo
        ICarro carroRemovido = null;


        while (!vagas.estaVazia()) {

            ICarro carroAtual = vagas.desempilhar();

            if (carroAtual != null && carroAtual.getPlaca().equals(placa)) {
                carroRemovido = carroAtual;
                carroRemovido.setHorarioSaida(horarioSaida++);
                break;
            }

            if (carroAtual != null) {
                temp.empilhar(carroAtual);
            }
        }


        // Se o carro foi removido, move o próximo da fila para a vaga
        if (carroRemovido != null) {
            System.out.println("Carro " + carroRemovido.getPlaca() + " removido.");

            if (!filaEspera.estaVazia()) {
                ICarro proximoCarro = filaEspera.remover();

                // Adiciona o carro da fila no lugar do carro removido (ANTES de restaurar a pilha temp)
                estacionarCarro(proximoCarro);

            } else {

                System.out.println("Vaga liberada, mas a fila de espera está vazia.");

            }


            // Restaura a pilha 'vagas' com os carros da pilha temporária
            while (!temp.estaVazia()) {

                vagas.empilhar(temp.desempilhar());

            }

            // Remove o carro do array carrosEstacionados (após restaurar a pilha)

            for (int i = 0; i < numCarrosEstacionados; i++) {

                if (carrosEstacionados[i] == carroRemovido) {
                    for (int j = i; j < numCarrosEstacionados - 1; j++) {

                        carrosEstacionados[j] = carrosEstacionados[j + 1];
                    }
                    carrosEstacionados[numCarrosEstacionados - 1] = null;
                    numCarrosEstacionados--;
                    break;
                }
            }

        } else {
            // Restaura a pilha se o carro não foi encontrado
            while (!temp.estaVazia()) {
                vagas.empilhar(temp.desempilhar());
            }
            System.out.println("Carro com placa " + placa + " não encontrado.");
        }
    }


    // Método para gerar o relatório diário
    @Override
    public void relatorioDiario() {
        if (numCarrosEstacionados == 0) {
            System.out.println("Não há carros estacionados para gerar relatório.");
            return;
        }

        // Cria uma cópia do array carrosEstacionados SEM USAR Arrays.copyOf()
        ICarro[] carrosParaOrdenar = new ICarro[numCarrosEstacionados];
        for (int i = 0; i < numCarrosEstacionados; i++) {
            carrosParaOrdenar[i] = carrosEstacionados[i];
        }

        // Ordena a cópia usando MergeSort (implementação manual)
        MergeSort<ICarro> mergeSort = new MergeSort<>();
        mergeSort.ordenar(carrosParaOrdenar);


        System.out.println("Relatório Diário:");


        for (int i = 0; i < numCarrosEstacionados; i++) {

            if (carrosParaOrdenar[i] != null) {
                System.out.println("Placa: " + carrosParaOrdenar[i].getPlaca() +
                        ", Chegada: " + carrosParaOrdenar[i].getHorarioChegada() +
                        ", Saída: " + (carrosParaOrdenar[i].getHorarioSaida() == 0 ? "Ainda estacionado" : carrosParaOrdenar[i].getHorarioSaida()));

            }


        }


    }

}