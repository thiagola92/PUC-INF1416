# Trabalho
Em um sistema normal, ele daria uma maneira de como você armazenar os arquivos de forma secreta e uma maneira recuperar esses arquivos secretos.  
Nesse trabalho apenas faremos a parte de recuperar.  

# Autenticação
Quando um usuário é cadastrado, ele tem que fornecer uma senha pessoal e um certificado digital.  
Por isso para o usuário se autenticar ele tem que passar por 3 etapas.  

## 1ª etapa
Usuário passa um e-mail e é preciso verificar se é o mesmo do certificado digital de quando o usuário cadastrou.  

`Digite o seu e-mail: thiagola92@email.com`  

## 2ª etapa
Usuário fornece uma senha e nós calculamos o digest da senha + salt desse usuário para saber se acertou a senha.  

Senha possui 6/7/8 números.  

Essa senha é fornecida da mesma maneira que bancos fazem (tecla virtual númerico), dão conjuntos de números e você seleciona onde está o próximo número da sua senha.  

Vamos supor que a senha seja 038495 e o nosso menu seja  

| 1 ou 4 | 0 ou 3 | 5 ou 7 | 6 ou 2 | 8 ou 9 |
| :----: | :----: | :----: | :----: | :----: |
|        |        |        |        |        |

Primeiro digito da senha é 0, então clicamos na segunda opção  

| 1 ou 4 | 0 ou 3 | 5 ou 7 | 6 ou 2 | 8 ou 9 |
| :----: | :----: | :----: | :----: | :----: |
|        |    X   |        |        |        |

Após isso as opções devem atualizar  

| 7 ou 9 | 5 ou 1 | 0 ou 8 | 3 ou 6 | 2 ou 4 |
| :----: | :----: | :----: | :----: | :----: |
|        |        |        |        |        |

Agora clicamos no próximo digito da senha, que é 3  

| 7 ou 9 | 5 ou 1 | 0 ou 8 | 3 ou 6 | 2 ou 4 |
| :----: | :----: | :----: | :----: | :----: |
|        |        |        |    X   |        |

E assim vai se repetindo até o usuário preencher toda a senha.  

No final teremos que o usuário preencheu sempre duas opções para cada clique.  

[ 0 ou 3 ]  
[ 3 ou 6 ]  
[ 8 ou 5 ]  
[ 2 ou 4 ]  
[ 0 ou 9 ]  
[ 4 ou 5 ]  

Isso nos gera 64 combinações de possíveis senhas, teremos que testar cada uma delas.  

### Banco de dados
Dentro do banco **não** iremos armazenar a senha do usuário.  
Quando o usuário é cadastrado, geramos um salt para ele e concatenamos com a senha do usuário para depois formar um digest.  

Para verificar se o usuário digitou a senha certa, iremos concatenamos com o salt e geramos o digest, iremos comparar esse digest gerado com o do usuário.  

O que o banco de dados precisa armazenar é  

| digest | salt |
| :-:    | :-:  |
| M3ODM9MFJISN23... | 0b3Ff2X5Fl |
| ... | ... |

Salt nesse trabalho é composto por 10 caracteres do conjunto [A-Z][a-z][0-9]  

## 3ª etapa
Quando o usuário se cadastrou no sistema, ele forneceu uma senha e um certificado digital.  

Nesse certificado digital a gente possui a chave pública, vamos fazer o usuário passar a chave privada dele para verificarmos se realmente é a chave par daquela chave pública.  

Para verificarmos basta a gente cifrar com a chave pública uma string e verificar se decifrando com a chave privada que o usuário passou, conseguimos a mesma string de volta.  

Mas o usuário não pode passar essa chave em texto plano, por isso ciframos ela com uma chave secreta e passamos para o sistema atráves de um arquivo.  

Esse arquivo vai estar codificado em `BASE64`, no formato `PEM` e padrão `PKCS8`.  
```
-----BEGIN PRIVATE KEY-----
MIIBrzBJBgkqhkiG9w0BBQ0wPDAbBgkqhkiG9w0BBQwwDgQImQO8S8BJYNACAggA
MB0GCWCGSAFlAwQBKgQQ398SY1Y6moXTJCO0PSahKgSCAWDeobyqIkAb9XmxjMmi
hABtlIJBsybBymdIrtPjtRBTmz+ga40KFNfKgTrtHO/3qf0wSHpWmKlQotRh6Ufk
0VBh4QjbcNFQLzqJqblW4E3v853PK1G4OpQNpFLDLaPZLIyzxWOom9c9GXNm+ddG
LbdeQRsPoolIdL61lYB505K/SXJCpemb1RCHO/dzsp/kRyLMQNsWiaJABkSyskcr
eDJBZWOGQ/WJKl1CMHC8XgjqvmpXXas47G5sMSgFs+NUqVSkMSrsWMa+XkH/oT/x
P8ze1v0RDu0AIqaxdZhZ389h09BKFvCAFnLKK0tadIRkZHtNahVWnFUks5EP3C1k
2cQQtWBkaZnRrEkB3H0/ty++WB0owHe7Pd9GKSnTMIo8gmQzT2dfZP3+flUFHTBs
RZ9L8UWp2zt5hNDtc82hyNs70SETaSsaiygYNbBGlVAWVR9Mp8SMNYr1kdeGRgc3
7r5E
-----END PRIVATE KEY-----
```

Salvar e recuperar em `BASE64`  
```Java
byte[] textBase64 = Base64.getMimeEncoder().encode("test".getBytes());
byte[] text = Base64.getMimeDecoder().decode(textBase64);
```

Recuperar private key da `BASE64`
```Java
byte[] privateKeyBytes = Base64.getMimeDecoder().decode(textBase64);
PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

KeyFactory keyFactory = KeyFactory.getInstance("RSA");
PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
```

Uma frase secreta é usada como semente para o `SHA1PRNG` gerar números aleatórios.  
```Java
SecureRandom sha1prng = SecureRandom.getInstance("SHA1PRNG");
sha1prng.setSeed("exemplo de frase secreta".getBytes());
```

A chave secreta é gerada com o `DES` e o PRNG.  
```Java
KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
keyGenerator.init(56, sha1prng) ;
Key key = keyGenerator.generateKey();
```

A chave privada cifrada é decifrada utilizando o algoritmo `DES/ECB/PKCS5Padding` e a chave secreta gerada.  
```Java
Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
cipher.init(Cipher.DECRYPT_MODE, key);
byte[] decipherText = cipher.doFinal("aqui deveria ser a chave privada cifrada".getBytes("UTF8"));
```
<sub>\*cipher.update() retorna a cada chamada aquela parte do texto cifrada, então você tem que ir guardando cada pedaço (não que eu tenha utilizado esse método no exemplo)</sub>  

É preciso criar uma assinatura digitalno padrão RSA (`MD5withRSA`) para um array de `2048` bytes.  
# Arquivos

| .enc |
| :-: |
| Arquivo encriptado |
| Cifrado com `DES/ECB/PKCS5Padding` |

| .env |
| :-: |
| Envelope Digital |
| Cifrado com `RSA?` |
| Chave gerada com `DES` (56 bits) |
| SecureRandom com `SHA1PRNG` |

| .asd |
| :-: |
| Assinatura Digital |
| Assinado com `MD5withRSA`? |
