### Metodologia de Desenvolvimento de Aplicações
Levar em conta requisitos de proteção de informação ou dados.  

**Por exemplo**:  
Você não vai mais abrir um arquivo como está costumado a abrir, usando as primitivas quaisquer (open/write/read).  

Você vai utilizar bibliotecas que para te darem acesso aos dados (ler/escrever), essa bibliotecas vão requerir que você tenha um token. Esse token é fornecido a você ao logar como usuário (comprovando quem você é como usuário). O token vale para aquela sessão de uso (vale por um tempo limitado).  

Isso evita que um bug no sistema de acesso a informações que um usuário de certo nível não poderia ter. Pois a biblioteca vai requisitar o token desse usuário e perceber que ele não tem permissão para editar.  

Isso reduz o risco de uma programação errada causar defeito.  

### Garantia da Qualidade
Testar a segurança do seu sistema para garantir que o seu sistema está saindo com o mínimo de problemas possíveis, tratando os mais perigosos.  
A equipe que vai executar o teste, tem por objetivo tentar hackear o seu sistema.  

### Recursos Humanos
Não importa o quanto você investiu em segurança, se você não conseguir fazer as pessoas aceitarem a tecnologia/segurança e utilizarem ela, sua segurança é inútil.  
Você tem que convencer as pessoas a utilizarem os protocolos de segurança e como isso pode trazer benificios para elas também.

### Profissional de Segurança da Informação
Conhece estrategias de segurança, mas não é quem decide quais vai ser usado.  
Quem sabe qual a melhor para a empresa é o gerente, pois ele que entende de diversas áreas e pode dizer qual vai ser melhor de se aplicar.  

Exemplo: Você pode sugerir grandes protocolos de segurança mas o gerente sabe que não tem como financiar eles.  

# Ameaças Comuns
É preciso estara preparado para diversas ameaças que podem levar a diversos danos ou perdas.  

Erro no HD, você perdeu dados do banco de dados.  
Incêndio, você perder máquinas e documentos.  
Ataques de invasões, perda de informações.  
Empregadods supostamente confiáveis, decidem atacar a própria empresa e roubar informação.  

Pesquisadores apontam que maior partes das ameaças é um empregado autorizado no seu sistema, não é técnico e que por acaso descobriu uma vulnerabilidade.  
Outra parte dos problemas vem das pessoas que utilizam seu sistema (usuários/empregados).  

**O que isso quer dizer**? Que se você tem pouco recurso, vale mais a pena investir em treinamento do seu pessoal do que nas tecnologias em si.  

Uma pequena parte de ameaça vem de empregados desonestos. Podiam ser desonestos antes ou se tornaram depois de alguma noticia ruim (descobriu que vai ser demitido).  
É bem normal esses empregados fazerem "sequestro de informação". Quando ele é obrigado a sair da empresa, ele leva conhecimento que apenas ele tinha sobre um software/documento.  

**O que isso quer dizer**? A informação nunca deve ficar em apenas uma pessoa, as informações precisam estar distribuidas ou documentadas.  

Perdas de infra-estrutura ou facilidades físicas hoje em dia não são tanto um problema pois são mais fácil de se planejar contra (em questão de dados). Incêndio, vazamento de água, terremoto, agitação civil (greves)... Não são tão ruins quanto antigamente e os casos anteriores (em questão de dados e informações que sempre podem ser armazenadas em outros locais/servers/banco de dados).  

**O que vai salvar você nesses casos**? O plano de continuidade de negocios, um plano que traça tudo que você deve fazer para recuperar seu sistema em caso de disastres.  

Ataques de hackers/crakers recebem muita atenção da imprensa porém são umas menores causas de problemas.  

**O que isso quer dizer**? Hoje em dia a segurança nos software estão bem mais altas e são atualizadas constantemente.  

# Gerenciamento de Risco

* Identificar os riscos/vulnerabilidades
* Avaliar a possibilidade desse risco acontecerem e o impacto  
* Determinar os controles e salvaguardas apropriados para minimizar os riscos até um nível aceitável

40:50

# ...

**O conteudo dessas aulas tem sido muito grande para se escrever, são coisas interessantes mas que custam muito tempo explicar/escrever direito. Infelizmente vou pular essas partes que são bem mais conceito e explicações de como tornar um ambiente seguro ou gerencia-lo**  
