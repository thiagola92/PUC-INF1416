# Testes

| Nome do arquivo | Teste | Resultado |
| --------------- | ----- | --------- |
| Arq1 | Digest do arquivo igual ao digest na lista | OK |  
| Arq2 | Digest do arquivo diferente do digest na lista | NOT_OK |  
| Arq3 | Arq3 e Arq4 são idênticos, mesmo digest na lista e nos arquivos | COLLISION |  
| Arq4 | Arq3 e Arq4 são idênticos, mesmo digest na lista e nos arquivos | COLLISION |  
| Arq5 | Arquivo não é encontrado na lista, adiciona no final do arquivo | NOT_FOUND |  
| Arq6 | Na lista o digest do Arq6 na verdade é o do Arq7 | COLLISION |  
| Arq7 | Na lista o digest do Arq7 na verdade é o do Arq6 | COLLISION |  
| Arq8 | Digest do Arq8 não está igual ao da lista e é igual ao do Arq9 | COLLISION |  
| Arq9 | Digest do Arq9 não está igual ao da lista e é igual ao do Arq8 | COLLISION |  
| Arq10 | Um arquivo muito muito muito grande e com digest errado | NOT_OK |  
| Arq11 | Uma linha com dois digest diferentes, um deles sendo o digest certo | OK |  
| Arq11 | Uma linha com dois digest diferentes, nenhum deles sendo o digest certo então adiciona no final da linha | NOT_FOUND |  

# Lista de Digest
Lista de digest se encontra no arquivo `ArqListaDigest`  

Antes do programa ser executado
```
Arq1 MD5 DC818349E6073C157797B9E368D8F932
Arq2 MD5 523A3A5A4F6CFC77689DCE763EA89517
Arq3 MD5 1DF03CD0D086EBB9020235239E33CE33
Arq4 MD5 1DF03CD0D086EBB9020235239E33CE33
Arq6 MD5 A66F3C97880195DB9E748284AFFA9DF5
Arq7 MD5 E5EF11FE918082AD27F5D70701262936
Arq8 MD5 E5EF11FE918082AD27F5D70701262937
Arq9 MD5 E5EF11FE918082AD27F5D70701262938
Arq10 MD5 432C8C62FE9D13FD68B1AD0BB7F7FA25
Arq11 XXX E5EF11FE918082AD27F5D70701262938 MD5 E0E608E9D528CBBBAEAA76C55FA5747A
Arq12 SHA1 A5FB06212D9F93A568A8D935FE9C6420
```

Após execução deve ser alterado para
```
Arq1 MD5 DC818349E6073C157797B9E368D8F932
Arq2 MD5 523A3A5A4F6CFC77689DCE763EA89517
Arq3 MD5 1DF03CD0D086EBB9020235239E33CE33
Arq4 MD5 1DF03CD0D086EBB9020235239E33CE33
Arq6 MD5 A66F3C97880195DB9E748284AFFA9DF5
Arq7 MD5 E5EF11FE918082AD27F5D70701262936
Arq8 MD5 E5EF11FE918082AD27F5D70701262937
Arq9 MD5 E5EF11FE918082AD27F5D70701262938
Arq10 MD5 432C8C62FE9D13FD68B1AD0BB7F7FA25
Arq11 XXX E5EF11FE918082AD27F5D70701262938 MD5 E0E608E9D528CBBBAEAA76C55FA5747A
Arq12 SHA1 A5FB06212D9F93A568A8D935FE9C6420 MD5 A5FB06212D9F93A568A8D935FE9C6420
Arq5 MD5 DFF189634C8D282EFDCFDA420B534A45
```

Para que você possa testar novamente o backup do primeiro arquivo se encontra no arquivo `test_backup`  

# Git
Passando arquivo do linux para o windows resultou em digests diferentes pois o github fez conversão de final de linha (\r para \n).  
Para evitar que o github faça essa conversão eu armazenei dentro de um zip.  

Outra opção seria:  
https://stackoverflow.com/questions/3858931/git-convert-carriage-return-r-to-new-line-n-with-git-hook
