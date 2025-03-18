public class Estacionamento implements IEstacionamento {

    private PilhaVet<ICarro> vagas; // Pilha para armazenar os carros estacionados
    private FilaEnc<ICarro> filaEspera; // Fila para armazenar os carros que estão esperando
    private ICarro[] carrosEstacionados; // Array para armazenar os carros estacionados
    private int numCarrosEstacionados; // Número de carros atualmente estacionados
    private final int MAX_VAGAS; // Capacidade máxima de vagas no estacionamento
    private int horarioChegada = 1; // Variável para controlar o horário de chegada dos carros
    private int horarioSaida = 1; // Variável para controlar o horário de saída dos carros

    // Construtor que inicializa o estacionamento com a capacidade máxima de vagas
    public Estacionamento(int maxVagas) {
        this.MAX_VAGAS = maxVagas;
        this.vagas = new PilhaVet<>(MAX_VAGAS); // Cria a pilha de vagas com o tamanho máximo
        this.filaEspera = new FilaEnc<>(); // Cria a fila de espera vazia
        this.carrosEstacionados = new ICarro[maxVagas]; // Inicializa o array de carros estacionados
        this.numCarrosEstacionados = 0; // Inicializa o número de carros estacionados
    }

    // Redimensiona o array carrosEstacionados sem usar Arrays.copyOf()
    private void redimensionarCarrosEstacionados() {
        int novoTamanho = carrosEstacionados.length * 2; // Dobra o tamanho do array
        ICarro[] novoArray = new ICarro[novoTamanho]; // Cria um novo array com o novo tamanho
        for (int i = 0; i < numCarrosEstacionados; i++) { // Copia manualmente os elementos
            novoArray[i] = carrosEstacionados[i];
        }
        carrosEstacionados = novoArray; // Substitui o array antigo pelo novo
    }

    // Exibe a mensagem de boas-vindas ao usuário
    public void mensagemBoasVnindas() {
        String mensagemInicial = """
                ************************************
                ********* Seja bem vindo! **********
                ************************************
                """;
        System.out.print(mensagemInicial); // Exibe a mensagem na tela
    }

    // Exibe as opções do menu de interações com o estacionamento
    public void opcoesMenu() {
        String menuOpcoes = """
                Opções:
                1. Estacionar carro
                2. Remover carro
                3. Gerar relatório
                4. Sair
                Escolha uma opção:
                """;
        System.out.print(menuOpcoes); // Exibe as opções no console
    }

    // Verifica se o estacionamento está vazio (não há carros estacionados)
    public boolean estacionamentoVazio() {
        return vagas.estaVazia(); // Retorna true se o estacionamento estiver vazio
    }

    // Verifica se a placa já existe no estacionamento ou na fila de espera
    boolean placaJaExiste(String placa) {
        for (ICarro carro : carrosEstacionados) {
            if (carro != null && carro.getHorarioSaida() == 0 && carro.getPlaca().equals(placa)) {
                return true; // A placa já existe no estacionamento
            }
        }
        for (ICarro carro : filaEspera) {
            if (carro != null && carro.getPlaca().equals(placa)) {
                return true; // A placa já existe na fila de espera
            }
        }
        return false; // A placa não existe nem no estacionamento, nem na fila
    }

    // Método para estacionar um carro no estacionamento
    @Override
    public void estacionarCarro(ICarro carro) {
        // Verifica se a placa do carro já existe no estacionamento ou na fila de espera
        if (placaJaExiste(carro.getPlaca())) {
            System.out.println("Erro: Já existe um carro com a placa " + carro.getPlaca() + " no estacionamento ou na fila de espera.");
            return; // Se já existe, não estaciona o carro
        }

        // Se houver vaga disponível, estaciona o carro
        if (vagas.tamanho() < MAX_VAGAS) {
            // Se o array de carros estacionados estiver cheio, redimensiona
            if (numCarrosEstacionados == carrosEstacionados.length) {
                redimensionarCarrosEstacionados();
            }
            vagas.empilhar(carro); // Coloca o carro na pilha de vagas
            carro.setHorarioChegada(horarioChegada++); // Define o horário de chegada
            carrosEstacionados[numCarrosEstacionados++] = carro; // Adiciona o carro ao array de carros estacionados
            System.out.println("Carro " + carro.getPlaca() + " estacionado.");
        } else {
            // Se não houver vaga, coloca o carro na fila de espera
            filaEspera.enfileirar(carro);
            System.out.println("Carro " + carro.getPlaca() + " na fila de espera.");
        }
    }

    // Método para remover um carro do estacionamento
    @Override
    public void removerCarro(String placa) {
        // Verifica se o estacionamento está vazio
        if (vagas.estaVazia()) {
            System.out.println("O estacionamento está vazio.");
            return; // Se estiver vazio, não há como remover um carro
        }

        PilhaVet<ICarro> temp = new PilhaVet<>(MAX_VAGAS); // Pilha temporária para armazenar carros removidos
        ICarro carroRemovido = null; // Carro a ser removido

        // Procura pelo carro na pilha de vagas
        while (!vagas.estaVazia()) {
            ICarro carroAtual = vagas.desempilhar(); // Retira um carro da pilha
            if (carroAtual != null && carroAtual.getPlaca().equals(placa)) { // Verifica se é o carro a ser removido
                carroRemovido = carroAtual;
                carroRemovido.setHorarioSaida(horarioSaida++); // Define o horário de saída do carro
                break;
            }
            temp.empilhar(carroAtual); // Se não for o carro desejado, coloca o carro na pilha temporária
        }

        // Se o carro foi encontrado e removido
        if (carroRemovido != null) {
            System.out.println("Carro " + carroRemovido.getPlaca() + " removido.");

            // Verifica se há carros na fila de espera e vagas disponíveis
            if (!filaEspera.estaVazia() && vagas.tamanho() < MAX_VAGAS) {
                estacionarCarro(filaEspera.remover()); // Estaciona o próximo carro da fila de espera
            } else if (!filaEspera.estaVazia()) {
                System.out.println("Há carros na fila de espera, mas o estacionamento está cheio.");
            } else {
                System.out.println("Vaga liberada, mas a fila de espera está vazia.");
            }
        } else {
            System.out.println("Carro com placa " + placa + " não encontrado.");
        }

        // Restaura a pilha de vagas com os carros que não foram removidos
        while (!temp.estaVazia()) {
            vagas.empilhar(temp.desempilhar());
        }
    }

    // Método para gerar o relatório diário do estacionamento
    @Override
    public void relatorioDiario() {
        if (numCarrosEstacionados == 0 && filaEspera.estaVazia()) {
            System.out.println("Não há carros para gerar relatório.");
            return; // Se não há carros, exibe mensagem e sai
        }

        // Cria uma cópia do array de carros estacionados para ordenação
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

        // Ordena a cópia usando MergeSort
        if (carrosParaOrdenar.length > 0) {
            MergeSort<ICarro> mergeSort = new MergeSort<>();
            mergeSort.ordenar(carrosParaOrdenar); // Ordena os carros estacionados
        }

        // Imprime o relatório dos carros estacionados
        System.out.println("Relatório Diário - Carros Estacionados:");
        for (ICarro carro : carrosParaOrdenar) {
            System.out.println("Placa: " + carro.getPlaca() +
                    ", Chegada: " + carro.getHorarioChegada() +
                    ", Saída: " + (carro.getHorarioSaida() == 0 ? "Ainda estacionado" : carro.getHorarioSaida()));
        }

        // Imprime o histórico dos carros que já saíram
        System.out.println("\nHistórico de Carros que Saíram:");
        for (ICarro carro : carrosEstacionados) {
            if (carro != null && carro.getHorarioSaida() != 0) {
                System.out.println("Placa: " + carro.getPlaca() +
                        ", Chegada: " + carro.getHorarioChegada() +
                        ", Saída: " + carro.getHorarioSaida());
            }
        }

        // Imprime os carros na fila de espera
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
