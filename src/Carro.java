class Carro implements ICarro, Comparable<ICarro> {
    private String placa;
    private int horarioChegada;
    private int horarioSaida;

    // Modificar o construtor para aceitar apenas a placa
    public Carro(String placa) {
        this.placa = placa;
        this.horarioChegada = 0; // Atribuindo um valor padrão para o horário de chegada
        this.horarioSaida = 0;   // Atribuindo um valor padrão para o horário de saída
    }

    @Override
    public String getPlaca() {
        return placa;
    }

    @Override
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public int getHorarioChegada() {
        return horarioChegada;
    }

    @Override
    public void setHorarioChegada(int horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    @Override
    public int getHorarioSaida() {
        return horarioSaida;
    }

    @Override
    public void setHorarioSaida(int horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public int compareTo(ICarro outroCarro) {
        // Compara por horário de chegada
        return Integer.compare(this.getHorarioChegada(), outroCarro.getHorarioChegada());
    }
}
