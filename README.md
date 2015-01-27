# ProjetoFinal
========================
Batalha Naval - Projeto Final do curso Geração Tec
Nome do projeto? Jogo - Batalha Naval. 

O que ele faz? Jogo Multiplayer (Clientes se comunicam através do Servidor Java).

Servidor ira esperar por duas conexões para iniciar o jogo, ele também vai gerar um log de conexões e manter
uma base de dados para Histórico de jogadas e Histórico de Vencedores (Ranking).
Os clientes irão se comunicar pela entrada e saída padrão do server/socket (com opção de nome/ip e porta).
Cada cliente/jogo é composto por um tabuleiro/mapa de 100 posições (10x10), com cinco peças/navios.
O jogo é dividido em duas fases: o posicionamento dos navios e a batalha. Na primeira parte do jogo os clientes
escolhem a posição de cada peça/navio no mapa. Depois o jogo começa e a cada rodada uma bomba é lançada contra
o tabuleiro do inimigo, o primeiro afundar/acertar todos as pecas/barcos do adversário ganha o jogo.

Quais as tecnologias utilizadas para realizar o projeto?

- Arquivos (Log - Registro de Conexões)
- MySql (Base de Dados para Histórico de jogadas e Histórico de Vencedores - Ranking)
- Sockets (Conexão Cliente/Servidor - Recebe dados)
- Threads (Conexão Cliente/Servidor - Envia dados)
- Swing (Interface Gráfica)

Quem são os integrantes (nome e e-mail):

Alexandre <alexandreess@gmail.com>
Carlos Eduardo Passos de Sousa <carloseduardosousa@gmail.com>
Henrique Wilhelm <henrique.wilhelm@gmail.com> 
Jaison dos santos <jaison1906@gmail.com>
Otavio Ribeiro <otavioribeiro@capflorianopolis.org.br>

* Ideias

- Log (Arquivos)
Numero conexões
Quantidade de jogos finalizados
Quantidade de jogos acabados com sucesso
Quantidade de jogos inacabados

- Ranking (Base de Dados)
Navios intactos
Navios abatidos
Placar
Pontos (por numero tentativas e/ou tempo)

-Música/Sons? (Bomba explodindo, bomba na agua, sei lá)
Alguém pode pesquisar como manipular o áudio em java...

-Tempo
Podemos contabilizar o tempo de cada jogo e também controlar o tempo de resposta de cada cliente. Mais uma pesquisa...

- Single player (Cliente x Computador)
Dá começar desde agora afinal são opções diferentes de jogo, vai ficar com um layout diferente do Multiplayer mas assim agente consegue dividir melhor as tarefas, acho que é tranquilo não vai ser com inteligencia artificial mas dá bem pra fazer... rsrsrs

...

Obs: Por acaso essas são ideias que podem ser implementadas a parte do projeto principal, são classes que não dependem do jogo em si, só vão ser chamadas e alimentadas lá de dentro... Por exemplo Hanking (parâmetros...) mais os set`s e get`s (Insert e Select na Base de Dados), o mesmo para Musica/Sons, chamamos Musica (diretório) mais o play e stop...

Parte 2: 27/08/2014

- Licença definida aberta.

The MIT License (MIT)
Copyright (c) 2014. Authors.

Authors: 
Alexandre <alexandreess@gmail.com>
Carlos Eduardo Passos de Sousa <carloseduardosousa@gmail.com>
Henrique Wilhelm <henrique.wilhelm@gmail.com> 
Jaison dos santos <jaison1906@gmail.com>
Otavio Ribeiro <otavioribeiro@capflorianopolis.org.br>

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

##Tecnologias utiizadas
- Swing
- Multi Threads
- Java 1.7
- My SQL
=======

