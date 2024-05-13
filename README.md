# Simulação de Concessionária com Sockets

Este projeto simula um sistema de concessionárias que recebem veículos de uma fábrica externa e os vendem para clientes. A comunicação com a fábrica é realizada via sockets, e o estoque da concessionária é gerenciado por um buffer circular, representando a esteira de veículos.

## Requisitos

- Java Development Kit (JDK) 16 ou superior

## Como executar o projeto

Clone o repositório: 
```bash
git clone https://github.com/allanrodigo/auto_service_stream.git
```

Compile o código:
```bash
javac *.java
```

Execute a simulação (assumindo que a fábrica está rodando em outro computador):
```bash
java Main
```


## Arquitetura do Sistema

O sistema é composto por três componentes principais:

### Fábrica (Externa)
- Executa em outro computador e escuta conexões de sockets.
- Envia dados de veículos (tipo, cor, IDs) em formato JSON para as concessionárias.

### Concessionárias
- Implementadas como classes `Dealership` que herdam de uma classe base abstrata.
- Cada concessionária possui:
  - Um nome (`name`).
  - Um `Client` para conectar com a fábrica.
  - Um `Buffer` (circular) para armazenar veículos.
  - Métodos `add_vehicle()` para receber veículos da fábrica e `sell_vehicle()` para vender aos clientes.

### Clientes
- Implementados como threads (`Customer`).
- Cada cliente:
  - Tenta comprar um veículo aleatoriamente de uma das concessionárias.
  - Se a concessionária não tiver veículos, o cliente entra em espera.

## Classes Principais

- `Client`: Estabelece a conexão com a fábrica via socket e recebe os dados dos veículos.
- `Dealership`: Classe base abstrata para as concessionárias. Gerencia o estoque de veículos e a comunicação com a fábrica.
- `MercedesDealership`, `PorscheDealership`, `BmwDealership`: Classes concretas que implementam as concessionárias de cada marca.
- `Customer`: Representa um cliente que tenta comprar um veículo.
- `Buffer`: Implementa um buffer circular para armazenar os veículos na esteira.
- `Vehicle`: Representa um veículo com seus atributos (tipo, cor, IDs).
- `Main`: Inicializa a simulação e cria as concessionárias e os clientes.

## Funcionamento da Simulação

A classe `Main` cria as concessionárias e os clientes. Cada concessionária inicia uma thread que se conecta à fábrica e recebe veículos continuamente, adicionando-os ao seu buffer. Cada cliente inicia uma thread que tenta comprar um veículo aleatoriamente de uma concessionária. Se a concessionária tiver veículos disponíveis, o cliente compra um e a concessionária remove o veículo do buffer. Se a concessionária não tiver veículos, o cliente entra em espera até que um veículo esteja disponível.

## Logs

O sistema registra eventos importantes, como a chegada de veículos nas concessionárias e a venda de veículos aos clientes, em um arquivo de log (`dealership_logs.txt`).

## Considerações

Este projeto demonstra a aplicação de conceitos de programação concorrente em Java, como threads, semáforos e sockets. A simulação assume que a fábrica está rodando em outro computador e enviando dados de veículos via socket. O projeto pode ser expandido para incluir mais funcionalidades, como diferentes tipos de veículos, estatísticas da simulação e interface gráfica.

## Autor

[Allan Amantino](https://github.com/allanrodigo)


