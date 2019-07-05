| Camada de Aplicação  | Camada de Transporte | Porta  |
| -------------------- | -------------------- | ------ |
| FTP                  | TCP                  | 21     |
| SMTP                 | TCP                  | 25     |
| HTTP                 | TCP                  | 80     |
| Cliente              | ---                  | >1023  |

**Exemplo de formato**  
`permit/deny tcp/udp origem destino established`  

Pode filtrar por porta também  
`permit/deny tcp/udp origem eq/gt porta_origem destino eq/gt porta_destino established`  

established = tem que ser tráfico de retorno  

**IP de Rede Privada**  
10.0.0.0  
127.0.0.0  
172.16.0.0  
192.168.0.0  

Roteador está conectado a rede externa, da rede externa só deveria vir pacote com origem de IP público  
Se aparecer um pacote com origem de rede privada, deve ser bloqueado pois é IP spoofing  

**Multicast**  
224.0.0.0  

**IP de Inicialização**  
0.0.0.0  

Não faz sentido esse ip vir da rede externa, esse ip apenas é usada na inicialização da rede  

### Vindo da Rede Externa  
**Bloquear**  
IP de Rede Privada  
Multicast  
IP de Inicialização  
IPs da Rede Interna  

### Indo para Rede Externa  
**Permitir**  
Ip da Rede Interna  

### I1
deny ip_privado any  
deny localhost any  
deny 0.0.0.0 any  
deny multicast any  
deny rede_interna any  
permit any rede_screened  

### E1
permit rede_screened any  

### I2
permit tcp any gt 1023 s1 eq 80 `WEB`  
permit tcp any gt 1023 s2 eq 25 [Message Transfer Agent] `SMTP`  
permit tcp any gt 1023 s2 eq 587 [Message User Agent] `SMTP`  
permit tcp rede_interna gt 1023 proxy eq 8080 `WEB`  

Note que até agora o s1 consegue receber da rede interna na porta 80  

### E2
permit tcp s2 gt 1023 any eq 25 [Message Transfer Agent] `SMTP`  
permit tcp proxy gt 1023 any eq 80 `WEB`  

### I3
-

### E3
permit tcp rede_interna gt 1023 s2 eq 587 [Message User Agent] `SMTP`  
permit tcp rede_interna gt 1023 proxy eq 8080 `WEB`  
