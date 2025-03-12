public interface IEstacionamento {

    void estacionarCarro(ICarro carro);
    void removerCarro(String placa);
    void relatorioDiario();

}
