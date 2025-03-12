/**
* Representa um carro no cenário de estacionamento.
*/
public interface ICarro extends Comparable<ICarro> {

    String getPlaca();
    void setPlaca(String placa);

    int getHorarioChegada();
    void setHorarioChegada(int horarioChegada);

    int getHorarioSaida();
    void setHorarioSaida(int horarioSaida);

}
