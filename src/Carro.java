// Classe Carro que implementa a interface ICarro e a interface Comparable<ICarro>
class Carro implements ICarro, Comparable<ICarro> {

    private String placa; // A placa do carro
    private int horarioChegada; // O horário de chegada do carro
    private int horarioSaida; // O horário de saída do carro

    // Construtor que inicializa a placa e os horários de chegada e saída do carro
    public Carro(String placa) {
        this.placa = placa; // Atribui a placa do carro
        this.horarioChegada = 0; // Inicializa o horário de chegada com 0 (não chegou ainda)
        this.horarioSaida = 0; // Inicializa o horário de saída com 0 (não saiu ainda)
    }

    // Método para obter a placa do carro
    @Override
    public String getPlaca() {
        return placa;
    }

    // Método para definir a placa do carro
    @Override
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    // Método para obter o horário de chegada do carro
    @Override
    public int getHorarioChegada() {
        return horarioChegada;
    }

    // Método para definir o horário de chegada do carro
    @Override
    public void setHorarioChegada(int horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    // Método para obter o horário de saída do carro
    @Override
    public int getHorarioSaida() {
        return horarioSaida;
    }

    // Método para definir o horário de saída do carro
    @Override
    public void setHorarioSaida(int horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    // Método para comparar o carro com outro carro baseado no horário de chegada
    @Override
    public int compareTo(ICarro outroCarro) {
        // Compara os horários de chegada dos dois carros
        return Integer.compare(this.getHorarioChegada(), outroCarro.getHorarioChegada());
    }
}
