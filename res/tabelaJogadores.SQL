DROP DATABASE projeto;
CREATE DATABASE projeto;
USE projeto;


DELETE FROM jogadores;
DROP TABLE jogadores;

CREATE TABLE jogadores (
id INT AUTO_INCREMENT,
nome VARCHAR(200),
senha VARCHAR(255),
pais  VARCHAR(255),
imagem INT,
medalhas INT,
vitorias INT,
derrotas INT,
totalJogos INT,
PRIMARY KEY(id),
INDEX (nome),
INDEX (senha)
);

INSERT INTO jogadores () VALUES (1,"teste", "123456","eua",1,5,25,5,30);
INSERT INTO jogadores () VALUES (2,"usuario", "usuario","italia",2,2,10,20,30);
INSERT INTO jogadores () VALUES (3,"henrique", "henrique","alemanha",3,3,15,15,30);
INSERT INTO jogadores () VALUES (4,"alexandre", "alexandre","franca",4,4,20,10,30);
INSERT INTO jogadores () VALUES (5,"carlos", "carlos","inglaterra",5,2,10,23,10);
INSERT INTO jogadores () VALUES (6,"otavio", "otavio","japao",1,5,25,5,30);
INSERT INTO jogadores () VALUES (7,"jaison", "jaison","eua",6,1,5,25,30);
INSERT INTO jogadores () VALUES (8,"nassor", "nassor","franca",5,0,0,0,0);

SELECT * FROM jogadores;