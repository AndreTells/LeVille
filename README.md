# Projeto Le Ville
## Descrição Resumida do Projeto
 O jogo consiste em expandir sua cidade até possuir mais de 50%. Porém, o jogador perderá se:
 - Tiver 0 de população ao final de um turno
 - Tiver mais população do que espaco disponível
 - Não produzir ganhar coomida ao final de algum turno
<br>
Para evitar tal, o jogador poderá escolher o que construir em cada espaço do mapa que pertencer a sí, aumentando ou diminuindo atributos de sua cidade. Além disso, eventos aleatórios ocorrem no início de cada turno, esses atrapalhando o jogador e impedindo que este vença facilmente.

## Equipe
- André Silva Telles - 165263

## Vídeos do Projeto
vídeo da primeira versão do projeto:<br>
[![vídeo de prévia](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/imagem_video.png)](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/video%20de%20apresentacao-165263-andre%20silva%20telles%20(1).mp4)

# Slides do Projeto
slides usados da primeira versão do projeto:<br>
[![slides de prévia do projeto](https://github.com/AndreTells/LeVille/blob/main/images/imagem_apresentacao.png)](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/Trabalho%20de%20MC322-Jogo-165263-andre%20silva%20telles%20(1).pdf)

## Slides de Prévia
<colocar slides de prévia do projeto>

# Documentção dos Componentes
# Diagramas

## Diagrama Geral de Componentes
![Diagrama geral do Projeto](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-geral-do-projeto.jpg)

## Componente Builder
Este Componente pertence ao controller e é responsável por e conectar todos os outros componentes
![Builder](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-builder.jpg)
**Ficha Técnica**
item | detalhamento
----- | -----
Classe | jogo.controller.Builder
Autores | André Silva Telles
Interfaces |  IRBoardModelBuilder<br>  IRScreenManager<br>  IRUIManager<br>  IRMouse<br>  IRBoard3DManager<br>  IREventManager

### Interfaces
![Builder-Interfaces](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-builder.jpg)
interface agregadora do componente em java:
~~~java 
public interface IGameBuilder 
   extends IRBoardModelBuilder,IRScreenManager,
	    IRUIManager,IRMouse,IRBoard3DManager,IREventManager
     {
     public void buildGame();
}
~~~

O metodo build desta é o resposável por conectar todos os componentes do jogo

## Componente Screen 
Tem a função de apresentar o UI, parte em 2D, e o tabuleiro, parte em 3D para o usuário em um JFrame.
![Screen](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-screen.jpg)

**Ficha Técnica**
Classe | autor | Interfaces
----- | ----- | -----
jogo.view.screen.ScreenManager | André Silva Telles | IScreenManager
jogo.view.screen.GameCanvas | André Silva Telles | GLEventListener
jogo.view.screen.GameFrame | André Silva Telles | JFrame

### Interfaces
![Screen-Interfaces](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-screen.jpg)
interface responsável por organizar conexão do componente com componentes externos 
~~~java 
public interface IScreenManager {
	public void set2D(IContainer container);
	
	public void set3D(IBoard3DManager board);
	
	public void setMouse(IMouse mouse);
}
~~~


## Componente BoardView3D
Resposável por armazenar como cada celula é apresentada para o usuário. Controlando:
- Os modelos 3D disponíveis ao jogo
- Carregar os modelos 3D nos assets	
- O estado atual de cada celula
- como desenhar tais celulas na tela
![BoardView3D](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-boardview3d.jpg)

**Ficha Técnica**
Classe | autor | Interfaces
----- | ----- | -----
jogo.view.boardview3d.BoardViewManager | André Silva Telles | IBoard3DManager
jogo.view.boardview3d.CellView | André Silva Telles | ICellViewController
jogo.view.boardview3d.RayPicker | André Silva Telles | IMouseObserver
jogo.view.boardview3d.Game3DObjectManager | André Silva Telles | N/A
jogo.view.boardview3d.camera.GLCamera | André Silva Telles | N/A
jogo.view.boardview3d.camera.CameraDraggObserver | André Silva Telles | IMouseObserver
jogo.view.boardview3d.camera.CameraMotionObserver | André Silva Telles | IMouseObserver
jogo.view.boardview3d.customobject.Obj3D | André Silva Telles | N/A
jogo.view.boardview3d.customobject.Face3D | André Silva Telles | N/A


### Interfaces
![BoardView3D-Interfaces](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-boardview3d.jpg)




## Componente UI
Responsável por organizar os elementos 2D que seram apresentados na tela.
Para tal, foi empregado o design pattern _Composite_ . Este consite em ter um elemento geral, o componente, e 2 sub elementos que herdam deste, o compostos e a folha. O composto pode possuir vários componentes como filho e a maioria de seus métodos é repassado para seus filhos. Já a folha fica encarregada de executar a operação implementada. Os compostos implementados foram:
- GLButton
- GLContainer
- GLPopUPMenu
- UI

e as folhas foram:
- GLLabel
- GLRectangle

O método principal deste é o _draw(GL2 gl)_. Este expressa como desenhar os elementos na tela
![UI](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-ui.jpg)

**Ficha Técnica**
Classe | autor | Interfaces
----- | ----- | -----
jogo.view.ui.UIManager | André Silva Telles | IUIManager
jogo.view.ui.UI | André Silva Telles | IStats
jogo.view.ui.GLElementLeaf | André Silva Telles | N/A
jogo.view.ui.GLElementComposite | André Silva Telles | N/A
jogo.view.ui.GLElementComponent | André Silva Telles | N/A
jogo.view.ui.GL2DObserver | André Silva Telles | N/A
jogo.view.ui.leaf.GLLabel | André Silva Telles | N/A
jogo.view.ui.leaf.GLRectangle | André Silva Telles | N/A
jogo.view.ui.composite.GLContainer | André Silva Telles | IContainer
jogo.view.ui.composite.GLPopUpMenu | André Silva Telles | IPopUpMenu
jogo.view.ui.composite.GLButton | André Silva Telles | N/A

### Interfaces
![BoardView3D-Interfaces](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-ui.jpg)


## Componente Mouse 
serve como uma ponte entre o componente screen, os componentes desenhados na screen(UI e BoardView3D) e o controller. Checando se algum elemento foi ativado ou não(clicado, arrastado, movimentos, etc...) e informa sinaliza que o controller deve iniciar alguma ação
![Mouse](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-mouse.jpg)
**Ficha Técnica**
item | detalhamento
----- | -----
Classe | jogo.controller.Builder
Autores | André Silva Telles
Interfaces |  IRBoardModelBuilder<br>  IRScreenManager<br>  IRUIManager<br>  IRMouse<br>  IRBoard3DManager<br>  IREventManager

### Interfaces
![Mouse-Interfaces](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-mouse.jpg)
interface agregadora do componente em java:
~~~java 
public interface IMouse
	extends MouseListener,MouseMotionListener,
		IRMouseObserver,IRemoveMouseObserver{
	public void addActionObservers(String id ,IMouseObserver observer);
	
	public void removeActionObserver(String id);
	 
	public void addMotionObservers(String id ,IMouseObserver observer);
	
	public void removeMotionObserver(String id);
	
	public void addDraggObservers(String id ,IMouseObserver observer);
	
	public void removeDraggObserver(String id);
}
~~~

## Componente GameController 
responsável por executar as ações requisitadas pelo usuário através do mouse
![GameController](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-gamecontroller.jpg)

**Ficha Técnica**
Classe | autor | Interfaces
----- | ----- | -----
jogo.controller.gamecontroller.CellController | André Silva Telles | IActor
jogo.controller.gamecontroller.ConstructPopUpController | André Silva Telles | IActor
jogo.controller.gamecontroller.EventPopUpController | André Silva Telles | IActor
jogo.controller.gamecontroller.TurnController | André Silva Telles | IActor


### Interfaces
![GameController-Interfaces](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-gamecontroller.jpg)
interface que permite que observers excutem tarefas que podem ser facilmente alteradas, basta trocar o IActor. As Classes deste componentes são tais tarefas
~~~java
public interface IActor {
	public void act(MouseEvent e);
	public void act(MouseEvent e,boolean missed);
}
~~~



## Componente BoardModel
responsável por guardar as informações do jogador e do tabuleiro, por exemplo guardar:
- a quantidade de comida que o jogador possui
- quais celulas foram tomadas pelo jogador
- as informações das celulas(posição, componentes na celula, atributos da celula, etc...)
- etc...
![BoardModel](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-BoardModel.jpg)

**Ficha Técnica**
Classe | autor | Interfaces
----- | ----- | -----
jogo.model.boardmodel.BoardManager | André Silva Telles | IBoardModelBuilder
jogo.model.boardmodel.CellModel | André Silva Telles | N/A
jogo.model.boardmodel.Player | André Silva Telles | IPlayerController
jogo.model.boardmodel.BoardModel | André Silva Telles | IBoardEvent<br> IBoardController
jogo.model.boardmodel.mapgenerator.MapGenerator | André Silva Telles | N/A
jogo.model.boardmodel.mapgenerator.ImprovedNoise | André Silva Telles | N/A
jogo.model.boardmodel.components.Castle | André Silva Telles | N/A
jogo.model.boardmodel.components.City | André Silva Telles | N/A
jogo.model.boardmodel.components.Component | André Silva Telles | N/A
jogo.model.boardmodel.components.ConstructableComponent | André Silva Telles | N/A
jogo.model.boardmodel.components.Farm | André Silva Telles | N/A
jogo.model.boardmodel.components.Forest | André Silva Telles | N/A
jogo.model.boardmodel.components.Grass | André Silva Telles | N/A
jogo.model.boardmodel.components.LumberMill | André Silva Telles | N/A
jogo.model.boardmodel.components.Mountain | André Silva Telles | N/A
jogo.model.boardmodel.components.PreserveForest | André Silva Telles | N/A
jogo.model.boardmodel.components.Water | André Silva Telles | N/A

### Interfaces
![BoardModel-Interfaces-1](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-BoardModel(parte-1).jpg)
![BoardModel-Interfaces-2](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-BoardModel(parte-2).jpg)
interface que comanda ligações do componente com outros componentes
~~~java
public interface IBoardModelBuilder {
	public Player getPlayer();
	
	public IBoardController getBoardController();
	
	public IBoardEvent getBoardEvent();
}
~~~

## Componente Events 
responsável por ler e executar eventos aleatórios no tabuleiro
![Events](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-events.jpg)


**Ficha Técnica**
Classe | autor | Interfaces
----- | ----- | -----
jogo.model.events.EventManager | André Silva Telles | IEventManager
jogo.model.events.Event | André Silva Telles | N/A
jogo.model.events.CityEvent | André Silva Telles | N/A

![Events-Interfaces](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/diagrama-interfaces-Events.jpg)



## Detalhamento das Interfaces


### Interface IRBoard3DManager

~~~java 
public interface IRBoard3DManager {
   public void connect(IBoard3DManager board_view_manager);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente BoardView3D 

### Interface IRBoardModelBuilder 

~~~java 
public interface IRBoardModelBuilder {
   public void connect(IBoardModelBuilder model);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente BoardModel 

### Interface IREventManager

~~~java 
public interface IREventManager {
   public void connect(IEventManager event_manager);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente Events

### Interface IRMouse

~~~java 
public interface IRMouse {
   public void connect(IMouse mouse);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente Mouse

### Interface IRScreenManager

~~~java 
public interface IRScreenManager {
   public void connect(IScreenManager screen);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente Screen

### Interface IRUIManager

~~~java 
public interface IRUIManager {
   public void connect(IUIManager ui_manager);
}
~~~
Método | Objetivo
-------| --------
connect | conectar o Componente Builder com o Componente UI

### Interface IGameBuilder

~~~java 
public interface IGameBuilder
	extends IRBoardModelBuilder,IRScreenManager,
	IRUIManager,IRMouse,IRBoard3DManager,IREventManager
	{
	public void buildGame();
	
}
~~~
Método | Objetivo
-------| --------
buildGame | conecta os Componentes do jogo uns com os outros

### Interface IRMouseObserver

~~~java 
public interface IRMouseObserver {
	public void addActionObservers(String id ,IMouseObserver observer);
	
	public void addMotionObservers(String id ,IMouseObserver observer);
	
	public void addDraggObservers(String id ,IMouseObserver observer);
}
~~~
Método | Objetivo
-------| --------
addActionObservers | adiciona um observer com id ao mouse, notificando este sempre que o mouse clicar em algo
addMotionObservers | adiciona um observer com id ao mouse, notificando este sempre que o mouse se mover
addDraggObservers  | adiciona um observer com id ao mouse, notificando este sempre que o mouse arrastar algo

### Interface IRemoveMouseObserver

~~~java 
public interface IRemoveMouseObserver {
	public void removeActionObserver(String id);
	
	public void removeMotionObserver(String id);
	
	public void removeDraggObserver(String id);
}
~~~
Método | Objetivo
-------| --------
removeActionObserver | remove um observer de ações com id do mouse
removeMotionObserver | remove um observer de movimentos com id do mouse
removeDraggObserver  | remove um observer de arrastar com id do mouse

### Interface IMouseObserver

~~~java 
public interface IMouseObserver {
	public boolean conditonIsMet(float pos_x,float pos_y );
	
	public void performAction(MouseEvent e,boolean missed);

	public int getRank();
}
~~~
Método | Objetivo
-------| --------
conditonIsMet | checa se a condição para o observer realizar uma ação é cumprida, sem sim, retorna true, se não, retorna false
performAction | excuta a ação do observer
getRank | retorna o rank do observer para que seja possível comparar observers caso duas condições sejam cumpridas simultâneamente

### Interface IActor

~~~java 
public interface IActor {
	public void act(MouseEvent e);
	public void act(MouseEvent e,boolean missed);
}
~~~
Método | Objetivo
-------| --------
act | realiza uma ação baseada no MouseEvent e se a condição para realizar a ação foi cumprida ou não


# Plano de Exceções
## Diagrama da hierarquia de exceções
![Plano-de-Exceções](https://github.com/AndreTells/tarefasMC322/blob/main/Le%20Ville(New)/images/Diagrama%20da%20hierarquia%20de%20exce%C3%A7%C3%B5es.jpg)

## Descrição das classes de exceção
Classe | Descrição
----- | -----
ArquivoNaoEncontrado | Indica que o arquivo desejado nao foi encontrado na pasta de assets
ArquivoEventosNaoEncontrado | Indica que o arquivo de eventos aleatórios nao foi encontrado na pasta de assets
ArquivoModeloNaoEncontrado | Indica que o arquivo de um modelo 3D nao foi encontrado na pasta de assets
MouseInvalido | Indica que GLMouse nao foi inicializado
AtributoInvalido | Indica que algum dos valores passados na criação de um GLElementComponent é null ou esta fora do escopo do atributo(ex:  altura negativa,...)
ComponenteInvalido | Indica que a string passada não referencia um modelo 3d que o jogo tem acesso a 
