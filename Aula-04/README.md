
# Criptografia Assimétrica

* Chave pública
  * **Chave** par da chave privada
  * Utilizando essa chave você pode decifrar aquilo cifrado com **chave privada**
  * Chave conhecida por todos
* Chave privada
  * **Chave** par da chave pública
  * Utilizando essa chave você pode decifrar aquilo cifrado com **chave pública**
  * Chave só conhecida pelo dono do par de chaves
* O algoritmo de cifragem não é necessariamente o mesmo que o de decifrar
* Criptografia Assimétrica é custoso
  * O texto cifrado acaba bem maior que o texto plano
  * O tempo de cifragem é maior que o da criptografia simétrica

#### Cipher with private key
![Cipher with private key](privatekeycipher.jpg)  
![Decipher with public key](publickeydecipher.jpg)  

#### Cipher with public key
![Cipher with public key](publickeycipher.jpg)  
![Decipher with private key](privatekeydecipher.jpg)  

Vamos supor que temos Alice e Bob, cada um deles tem sua chave pública e privada.  

Lembrando que a chave pública de cada um é conhecida por todos (Alice conhece a chave pública de Bob e Bob conhece a chave pública de Alice). A chave privada apenas o dono da chave conhece.  

Lembrando também que as informações só podem ser recuperadas pela chave par da chave que foi usada para cifrar.  
Tudo cifrado pela chave privada da Alice, só pode ser recuperado pela chave pública de Alice.  
Tudo cifrado pela chave pública de Alice só pode ser recuperado pela chave privada de Alice.  

* Sigilo
  * Se Alice quer que apenas Bob seja capaz de ler a mensagem enviada por ela, ela pode simplesmente cifrar com a chave pública de Bob.  
  * Apenas Bob possui a chave privada para recuperar a informação, assim garantindo que apenas Bob consiga ler esssa mensagem.  

* Autenticidade
  * Se Alice quer que Bob saiba que a mensagem veio dela, ela pode simplesmente cifrar com a chave privada dela.  
  * Bob conhece a chave pública de Alice, se após decifrar a mensagem fizer sentido, então você sabe que só a Alice pode ter cifrado essa mensagem, ou seja, veio da Alice.  

Tudo ocorre perfeitamente se temos **certeza** que as chaves públicas são dos reais donos.  

# Man-in-the-middle
Se os dois pontos da comunicação já possuem a chave pública do outro ponto, em teoria estaria tudo seguro. Mas compartilhar a chave é o maior problema que podemos ter em qualquer modo de criptografia, pois pode ter alguém no meio da comunicação desdo início.  

* Man-in-the-middle
  * Precisa estar entre as duas conexões
    * Difícil de se fazer em uma rede aberta como internet
  * Precisa capturar a chave   

Conexão normal

![Conexão normal gif](normal.gif)  

Man-in-the-middle apenas ouvindo as mensagens  

![Man-in-the-middle ouvindo conexão](maninthemiddle.gif)  

Man-in-the-middle fingindo ser a outra conexão  

![Man-in-the-middle fingindo ser você](maninthemiddle2.gif)  

* Durante a primeira conexão  
  * Quando conexão A enviar a mensagem, man-in-the-middle poderá captura a mensagem e abrir da mesma maneira que a conexão B iria abrir.  
  * Com o texto plano obtido, o man-in-the-middle poderá alterar a mensagem e executar todos os protocolos de segurança (utilizando a chave pública e privada do man-in-the-middle).  
  * Quando o man-in-the-middle enviar a mensagem para conexão B, conexão B vai achar que a chave pública recebida é da conexão A quando na verdade é do man-in-the-middle.  

Se o man-in-the-middle fizer o mesmo durante a primeira conexão de B com A, ele obterá sucesso em fingir ser ambos em uma conexão.  

# Envelope Digital
Em inglês: Digital Envelope

O algoritmo assimétrico é bem custoso. Uma soluação para isso utilizar o algoritmo simétrico para criptograr a informação e o assimétrico para passar a chave do algoritmo simétrico.  

![Creating Digital Envelope](envelopedigital1.jpg)  
![Opening Digital Envelope](envelopedigital2.jpg)  
\* O normal seria você criptografar com a chave publica do destino, pois você quer que só ela tenha como ler a mensagem

É normal pessoa ficarem gerando chaves secretas a cada transmissão, pois ela pode ser obtida pela chaves privada/pública ao receber a informação.  
Além disso, descobrir a chave secreta não seria nada demais pois transmissão seguinte iria ser outra.  

# Resumo de Mensagem
Em inglês: Message Digest

Atráves de um algoritmo de hash você obtem uma saída que representa o conjunto a informação jogada no algoritmo  
A probabilidade de dar colisão é bem baixa, então consideramos que apenas para aquela dada entrada obtemos aquela saída.  

![Calculating Digest](digest.jpg)  

# Salt
Antigamente bancos de dados armazenavam o usuário e a senha dele. Quando o usuário falava a senha, o sistema conferia com a senha do banco de dados.  
Lado negativo: Se alguém conseguisse acesso ao banco de dados, conseguiria todas as senhas.  

| Usuário | Senha |
| ------- | ----- |
| Thiago  | 12345 |
| Miguel  | 67890 |
| João    | 12345 |

Depois os bancos de dados armazenavam o usuário e digests da senha. Quando o usuário falava a senha, o sistema calculava o digest da senha falada e comparava com o digest armazenado.  
Lado negativo: Se alguém descobrisse a senha que leva a um digest X, essa pessoa saberia que todos com o mesmo digest usam a mesma senha.  

Hash(12345) = A1B0DE  
Hash(67890) = JKH653  

| Usuário | Digest |
| ------- | ------ |
| Thiago  | A1B0DE |
| Miguel  | JKH653 |
| João    | A1B0DE |

Salt é um valor aleatório a ser adicionada a senha antes de calcular o digest.  
Esse valor é escolhido durante a criação da conta e deverá ser utilizado sempre para conferir se a senha está correta.  

Hash(12345 + 100) = 83JDIR  
Hash(67890 + 47) = JKH653  
Hash(12345 + 93) = PQMS74  

| Usuário | Digest | Salt |
| ------- | ------ | ---- |
| Thiago  | 83JDIR | 100  |
| Miguel  | JKH653 | 47   |
| João    | PQMS74 | 93   |

# Assinatura Digital
Em inglês: Digital Signature

![Creating digital signature](digitalsignature.jpg)  