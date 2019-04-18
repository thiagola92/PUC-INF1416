Arq1 --- Teste: Digest do arquivo igual ao digest na lista --- OK
Arq2 --- Teste: Digest do arquivo diferente do digest na lista --- NOT_OK
Arq3 --- Teste: Arq3 e Arq4 são idênticos, mesmo digest na lista e nos arquivos --- COLLISION
Arq4 --- Teste: Arq3 e Arq4 são idênticos, mesmo digest na lista e nos arquivos --- COLLISION
Arq5 --- Teste: Arquivo não é encontrado na lista, adiciona no final do arquivo --- NOT_FOUND
Arq6 --- Teste: Na lista o digest do Arq6 na verdade é o do Arq7 --- COLLISION
Arq7 --- Teste: Na lista o digest do Arq7 na verdade é o do Arq6 --- COLLISION
Arq8 --- Teste: Digest do Arq8 não está igual ao da lista e é igual ao do Arq9 --- COLLISION
Arq9 --- Teste: Digest do Arq9 não está igual ao da lista e é igual ao do Arq8 --- COLLISION
Arq10 --- Teste: Um arquivo muito muito muito grande e com digest errado --- NOT_OK
Arq11 --- Teste: Uma linha com dois digest diferentes, um deles sendo o digest certo --- OK
Arq11 --- Teste: Uma linha com dois digest diferentes, nenhum deles sendo o digest certo então adiciona no final da linha --- NOT_FOUND
