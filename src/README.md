

Como Instalar e Executar o Jogo

Para instalar o jogo basta baixar a pasta para o sistema operacional apropriado e seguir os passos abaixo
* para linux, certificar-se que a versão java sendo usada é 8
* para linux, certificar-se que a versão java sendo usada é 14

entrar na pasta baixada e escrever o comando:
~~~cmd
  > java -jar launcher.jar
~~~

Jogar :)

# Como Jogar
Ao iniciar o jogo, o jogador tem 3 formas de interagir com o jogo:
* arrastar o mouse(segurar o botão e mover o mouse) para mover o tabuleiro
* clicar com o botão esquerdo do mouse para clicar em botões 
* clicar com o botão esquerdo para mostrar o menu de elementos que podem ser construídos no espaço do tabuleiro selecionado

Na parte direita da tela, o jogador pode ver algumas informações sobre sua cidade:
* A população, representada da forma : < qtd de população > / < qtd de casas> + < qtd de casas que seram adicionadas >
* A produção disponível, representada da forma: < qtd de produção > + < qtd de produção que será adicionada >
* A comida, representada da forma : < qtd de comida > / < qtd de comida objetivo > + < qtd de comida que será adicionada >

A comida permite que sua cidade cresça, sempre que o jogador atingir a quantidade de comida objetivo, o jogador ganha mais uma população e recebe uma nova quantidade de comida objetivo. Porém o jogador precisa tomar cuidado para não acabar "ganhando" 0 ou menos de comida em algum turno, isto causaria o fim do jogo !!

Além disso, é necessário apontar que, caso a quantidade de população exceda a quantidade de casas disponíveis, o jogador perde. Felizmente, há uma forma de prevenir isso. 
Construindo elementos no tabuleiro e tomando novos espaços, o jogador pode aumentar ou diminuir todos esses atributo, permitindo que este cresça sua cidade sem problemas. Porém, há um custo para tal, tomar um espaço do tabuleiro(que é necessário para construir no mesmo) requer 10 de produção, o custo e benefício de construir cada componente possível é apresentado na tabela abaixo.

**Guia dos componentes que podem ser construídos**

Nome | custo de produção | comida | produção | casas
----- | ----- |----- |----- |----- 
City | 30  | -2 | 5 | 1
LumberMill | 40 | 0 | 14 | 0
Farm |  40 | 14 | 0 | 0
Castle | 50 | -6 | 2 | 3
PreserveForest | 20 | -4 | -1 | 0

Após construir tudo que deseja, o jogador deve apertar o botão de próximo turno(localizado no canto inferior esquerdo). No início do novo turno, ocorrera um evento aleatório que pode ser vantajoso ou não para o jogador. Esses serão apresentados no canto superior esquerdo da tela.
Isto se repete até que o jogador consiga 20 de população ou perca.
Para ajudar o jogador, avisos sobre atributos em um estado preocupante são disponibilizados no menu a direita(abaixo aos atributos da cidade).Além disso, a qualquer momento do jogo, o jogador pode clicar em uma célula para ver quais componentes esta possui, que atributos ela adicionaria a cidade do jogador e se esta célula pertence ao jogador ou não.
Abaixo está listado os atributos de todos os componentes:

**Guia de todos Componentes**

| Componentes                                                  |
| :----------------------------------------------------------- |
| <img align="left" width="200" height="200"  src = "..\media\modelo_grama.png"> Nome: Grama<br> atributos:<br> - +2 comida<br> - +0 produção<br> - +0 casas<br> requisitos para construir: N/A |
| <img align="left" width="200" height="200"  src = "..\media\modelo_floresta.png">Nome: Floresta<br> atributos:<br> - +1 comida<br>- +2 produção<br> - +0 casas<br> requisitos para construir: N/A |
| <img align="left" width="200" height="200"  src = "..\media\modelo_montanha.png">Nome: Montanha<br/> atributos:<br/> - +0 comida<br/> - +10 produção<br/> - +0 casas<br/> requisitos para construir: N/A |
| <img align="left" width="200" height="200"  src = "..\media\modelo_agua.png">Nome: Água<br/> atributos:<br/> - +0 comida<br/> - +0 produção<br/> - +0 casas<br/> requisitos para construir: N/A |
| <img align="left" width="200" height="200"  src = "..\media\modelo_cidade.png"> Nome: Cidade<br/> atributos:<br/> - -2 comida<br/> - +5 produção<br/> - +1 casas<br/> requisitos para construir: Célula deve possuir *apenas* *grama* |
| <img align="left" width="200" height="200"  src = "..\media\modelo_madeireira.png">Nome: Madeireira<br/> atributos:<br/> - +0 comida<br/> - +14 produção<br/> - +0 casas<br/> requisitos para construir: Célula deve possuir o componente *floresta* |
| **<img align="left" width="200" height="200"  src = "..\media\modelo_fazenda.png">Nome**: Fazenda<br/> **atributos**:<br/> - +14 comida<br/> - +0 produção<br/> - +0 casas<br/> **requisitos para construir**: Célula deve possuir *apenas*  o componente grama e ser adjacente uma  célula com água |
| <img align="left" width="200" height="200"  src = "..\media\modelo_castelo.png">**Nome**: Castelo<br/> **atributos**:<br/> - -6 comida<br/> - +2 produção<br/> - +3 casas<br/> **requisitos para construir**: Célula deve possuir *apenas*  o componente grama e ser adjacente a uma célula com uma montanha |
| <img align="left" width="200" height="200"  src = "..\media\modelo_floresta_preservada.png">Nome: Floresta Preservada<br/> atributos:<br/> - -4 comida<br/> - -1 produção<br/> - +0 casas<br/> requisitos para construir: Célula deve possuir o componente *floresta* e ser adjacente a uma célula com uma |



