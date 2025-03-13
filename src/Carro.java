class Carro implements ICarro, Comparable<ICarro> {
    private String placa;
    private int horarioChegada;
    private int horarioSaida;

    public Carro(String placa) {
        this.placa = placa;
        this.horarioChegada = 0;
        this.horarioSaida = 0;
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

    @Override
    public int compareTo(ICarro outroCarro) {
        return Integer.compare(this.getHorarioChegada(), outroCarro.getHorarioChegada());
    }

}