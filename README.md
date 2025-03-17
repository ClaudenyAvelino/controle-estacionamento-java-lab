# Controle de Estacionamento - Java

Este projeto implementa um sistema de controle de estacionamento em Java, simulando o funcionamento de um estacionamento com vagas limitadas e uma fila de espera. O projeto foi desenvolvido como uma atividade prática para o aprendizado de estruturas de dados e algoritmos.

## Descrição

O sistema simula um estacionamento com uma única faixa de entrada e saída. Os carros são estacionados em ordem de chegada, e quando o estacionamento está cheio, os carros que chegam entram em uma fila de espera. O sistema permite estacionar e remover carros, além de gerar um relatório diário com a placa, horário de chegada e saída de cada carro, ordenado por horário de chegada.

## Funcionalidades

* Estacionar um carro (`estacionarCarro(ICarro carro)`): Adiciona um carro ao estacionamento se houver vagas disponíveis. Caso contrário, adiciona o carro à fila de espera.
* Remover um carro (`removerCarro(String placa)`): Remove um carro do estacionamento com a placa especificada. Se o carro não for o último a entrar, os carros à sua frente são movidos temporariamente para uma pilha auxiliar e, em seguida, retornados à pilha principal. Se houver carros na fila de espera, o primeiro carro da fila é movido para o estacionamento.
* Gerar relatório diário (`relatorioDiario()`): Gera um relatório com a placa, horário de chegada e saída de cada carro que entrou no estacionamento, ordenados por horário de chegada. O relatório também inclui a lista de carros na fila de espera.

## Estruturas de Dados e Algoritmos

* **Pilha (`PilhaVet`):** Implementada com um vetor (array) para representar as vagas do estacionamento (LIFO - Last-In, First-Out).
* **Fila (`FilaEnc`):** Implementada com uma lista duplamente encadeada circular para gerenciar a fila de espera (FIFO - First-In, First-Out).
* **Ordenação (`MergeSort`):** Implementação manual do algoritmo Merge Sort para ordenar os carros no relatório diário.
* **Pilha Temporária:**  Utilizada no método `removerCarro()` para armazenar temporariamente os carros que estão à frente do carro a ser removido.


## Restrições

* **Sem bibliotecas externas (exceto `Comparable`, `Iterator`, `Iterable`):**  Todas as estruturas de dados e algoritmos foram implementados manualmente, sem o uso de bibliotecas externas do Java (como `java.util`).  A interface `Comparable` é usada para a ordenação, e as interfaces `Iterator` e `Iterable` são usadas para percorrer a pilha.
* **Tamanho fixo da pilha:** A pilha `vagas` tem um tamanho fixo, definido no construtor da classe `Estacionamento`.


## Tratamento de Exceções

O sistema inclui tratamento para as seguintes exceções:

* `PlacaDuplicadaException`: Lançada quando se tenta adicionar um carro com uma placa já existente.
* `EstacionamentoVazioException`: Lançada quando se tenta remover um carro de um estacionamento vazio.
* `EstacionamentoCheioException`: Lançada quando se tenta adicionar um carro e o estacionamento e a fila de espera estão cheios.



## Como Executar

1. Compile todas as classes Java do projeto.
2. Execute a classe `App`.
3. Siga as instruções no console para interagir com o sistema.


## Exemplo de Uso
Seja bem vindo!

Opções:

Estacionar carro

Remover carro

Gerar relatório

Sair
Escolha uma opção:
1
Digite a placa do carro: ABC1234
Carro ABC1234 estacionado.
...

## Autor

claudeny avelino, maria aline e maria de nazare


## Licença

[MIT](https://choosealicense.com/licenses/mit/)