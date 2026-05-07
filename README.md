🧩 Sudoku Game
Um jogo de Sudoku desenvolvido em Java com duas interfaces: gráfica (Swing) e console, organizados em módulos Maven independentes.

📋 Sobre o Projeto
Este projeto foi desenvolvido como exercício prático de Java, aplicando conceitos de orientação a objetos, padrões de projeto e organização modular com Maven. O jogo permite ao usuário resolver um tabuleiro de Sudoku tanto por uma interface gráfica intuitiva quanto pelo terminal.

🚀 Funcionalidades

✅ Tabuleiro de Sudoku 9x9 totalmente jogável
✅ Interface gráfica com Java Swing
✅ Interface via console
✅ Timer em tempo real
✅ Highlight de linha e coluna ao selecionar uma célula
✅ Verificação de erros com contador
✅ Botão de reiniciar jogo
✅ Validação de conclusão do jogo
✅ Leitura do tabuleiro via arquivo .txt


🗂️ Estrutura do Projeto
sudoku-game/
├── sudoku-core/        # Lógica central do jogo (modelos e serviços)
│   └── src/main/java/org/sudokugame/
│       ├── model/      # Board, Space, GameStatusEnum
│       ├── service/    # BoardService, NotifierService, HighlightManager...
│       └── util/       # BoardTemplate
│
├── sudoku-ui/          # Interface gráfica com Java Swing
│   └── src/main/java/org/sudokugame/
│       ├── UIMain.java
│       └── ui/custom/  # Botões, painéis, campos, telas
│
└── sudoku-console/     # Interface via terminal
    └── src/main/java/org/sudokugame/
        └── ConsoleMain.java

🛠️ Tecnologias Utilizadas

Java 25
Java Swing — interface gráfica
Maven — gerenciamento de dependências e módulos
IntelliJ IDEA — ambiente de desenvolvimento


⚙️ Como Rodar
Pré-requisitos

Java 25+
Maven 3.8+

1. Clone o repositório
bashgit clone https://github.com/felixxneto/Sudoku-Game.git
cd Sudoku-Game
2. Compile o projeto
bashmvn clean install
3. Configure o tabuleiro
Crie um arquivo board.txt na raiz do projeto com o seguinte formato:
linha,coluna;valorEsperado,fixo
Exemplo:
0,0;5,true
0,1;3,true
0,2;4,false
...
4. Rode a interface gráfica
bashjava -jar sudoku-ui/target/sudoku-ui.jar
5. Rode a versão console
bashjava -jar sudoku-console/target/sudoku-console.jar

🎮 Como Jogar

Clique em uma célula vazia e digite um número de 1 a 9
Use Verificar jogo para checar erros a qualquer momento
Use Reiniciar Jogo para começar do zero
Quando terminar, clique em Concluir jogo


📁 Formato do board.txt
Cada linha do arquivo representa uma célula do tabuleiro:
linha,coluna;valorEsperado,fixo

linha e coluna: posição de 0 a 8
valorEsperado: número correto para aquela célula (1-9)
fixo: true se o número aparece pré-preenchido, false se o jogador deve preencher


👨‍💻 Autor
Feito por Felix — GitHub
