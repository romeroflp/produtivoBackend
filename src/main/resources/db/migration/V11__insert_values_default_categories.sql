ALTER TABLE category ADD COLUMN color_icon VARCHAR(20) UNIQUE;

INSERT INTO category(name, user_id, color_icon)
VALUES('TRABALHO', NULL, '#1E88E5'),
      ('ESTUDOS', NULL, '#5E35B1'),
      ('PESSOAL', NULL, '#8E24AA'),
      ('SAÚDE', NULL, '#43A047'),
      ('EXERCÍCIOS', NULL, '#FB8C00'),
      ('COMPRAS', NULL, '#EC407A'),
      ('FINANÇAS', NULL, '#2E7D32'),
      ('CASA', NULL, '#8D6E63'),
      ('FAMÍLIA', NULL, '#F06292'),
      ('LAZER', NULL, '#26C6DA'),
      ('HOBBIES', NULL, '#FFB300'),
      ('PROJETOS', NULL, '#3949AB'),
      ('REUNIÕES', NULL, '#00897B'),
      ('VIAGENS', NULL, '#039BE5'),
      ('EVENTOS', NULL, '#D81B60'),
      ('METAS', NULL, '#FDD835'),
      ('ROTINA', NULL, '#78909C'),
      ('URGENTE', NULL, '#E53935'),
      ('IMPORTANTES', NULL, '#F4511E'),
      ('OUTROS', NULL, '#9E9E9E');