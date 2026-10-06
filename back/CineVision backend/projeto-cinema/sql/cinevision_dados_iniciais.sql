
USE cinevision;

INSERT INTO genero (nome) VALUES
  ('Ficção Científica'), ('Ação'), ('Drama'), ('Comédia'), ('Terror'), ('Animação');

INSERT INTO filme (titulo, titulo_original, sinopse, duracao_min, classificacao, data_estreia) VALUES
  ('Interestelar', 'Interstellar',
   'Uma equipe de exploradores viaja através de um buraco de minhoca no espaço.',
   169, '10', '2014-11-06');

INSERT INTO filme_genero (id_filme, id_genero)
SELECT f.id_filme, g.id_genero
FROM filme f, genero g
WHERE f.titulo = 'Interestelar' AND g.nome IN ('Ficção Científica', 'Drama');
