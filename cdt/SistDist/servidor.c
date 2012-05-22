#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h> // contantes IPPROTO_TCP         
#include <pthread.h>
#define MAX_PENDENTES 20
#define BUFF_SIZE 50
#define MAX_CONNECT 10


int ativo = 1; //Transferindo Arquivo
int passivo = 1; //Conectado Apenas
int porta_escuta, sock_serv;
pthread_t t_stdin;
socklen_t cli_len, recv_len;
struct sockaddr_in serv_addr, cli_addr;

struct list_cli {
  int socket;
  pthread_t t_connect;
  struct list_cli * anterior;
  struct list_cli * proximo;
};

typedef struct list_cli cliente;


void erro(char *msg)
{
  perror(msg);
  exit(0);
}

void le_linha(char *buffer, int n)
{
  char c;
  int i=0;
  while( (c = getchar()) != '\n' )
    {
      if(i != n-2) // 2 pq falta acrescer o \0 depois do while
	{
	  buffer[i] = c;
	  i++;
	}
    }
  buffer[i] = '\0';
}

void *recebe_entrada(void *args)
{
  char buffer[BUFF_SIZE];

  while(1)
    {
      bzero(buffer, BUFF_SIZE-1);
      le_linha(buffer, BUFF_SIZE);

      if(strcmp(buffer, "version") == 0)
	{
	  puts("\nServidor de MyFTP");
	  puts("Autores:");
	  puts("      Josiane Rodrigues");
	  puts("      Lidia Lizziane");
	  puts("      Rodrigo Bernardino");
	  puts("Versao: 1.3\n");
	}
      else if(strcmp(buffer, "shutdown") == 0)
	{
	  printf("\n------- ENCERRANDO SERVIDOR ----------\n");
	  // FALTA MATAR AS THREADS DE CONEXAO!!!!
	  close(sock_serv);
	  exit(0);
	} 
      else
	{
	  puts("\n%%%%%%%% Comando invalido! %%%%%%%%%%");
	  puts("Comandos possiveis:");
	  puts("shutdown   -   desliga o servidor");
	  puts("version    -   mostra a versao e autores");
	}
    }
}

void *conexao(void *args)
{
  cliente *myself = (cliente *) args;
  int socket = myself->socket;
  pthread_t t_thread = myself->t_connect;
  char buffer[BUFF_SIZE];
  while(1)
    {
      bzero(buffer, BUFF_SIZE);
      if(recv(socket, buffer, BUFF_SIZE - 1, 0) < 0)
	{
	  perror("Erro ao receber Requisicao");
	}
      
      sprintf(buffer, "PRONTO PARA RECEBER");
      
      if(send(socket, buffer, BUFF_SIZE, 0) < 0)
	{
	  perror("Erro ao enviar resposta");
	}
      puts("Resposta enviada!!");
    }
}

int main(int argc, char * argv[])
{

  cliente *cli_temp, *cli_ultimo = NULL;
  char buffer[BUFF_SIZE];

  if(argc < 2) 
    {
      fprintf(stderr, "Erro, informe a porta!\n");
      exit(1);
    }
  porta_escuta = atoi(argv[1]);
  pthread_create(&t_stdin, NULL, recebe_entrada, NULL);//inicia a thread para receber entrada do usuario

  if((sock_serv = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
      erro("Criar socket falhou");
    }
  printf("socket ok!\n");
  
  bzero((char *) &serv_addr, sizeof(serv_addr));

  serv_addr.sin_family      = AF_INET; // família de endereço da internet
  serv_addr.sin_addr.s_addr = INADDR_ANY; // aceita de qualquer IP, interface
  serv_addr.sin_port = htons(porta_escuta);

  if(bind(sock_serv, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0)
    {
      erro("bind falhou");
    }
  printf("bind() OK!\n");

  
  if(listen(sock_serv, MAX_PENDENTES) < 0)
    {
      erro("listen falhou");
    }
  printf("Listen OK!\n");

  cli_len = sizeof(cli_addr);

  while(1)
    {
      ///////
      cli_temp = (cliente *)malloc(sizeof(cliente));

      cli_temp->anterior  = cli_ultimo;
      cli_temp->proximo = NULL;

      if(cli_ultimo != NULL)
	cli_ultimo->proximo = cli_temp;

      cli_ultimo = cli_temp;
      cli_temp = NULL;
      //////


      if((cli_ultimo->socket = accept(sock_serv,(struct sockaddr *) &cli_addr, &cli_len)) < 0)
	{
	  erro("accept falhou");
	}
      printf("nova conexao!\n");

      pthread_create(&cli_ultimo->t_connect, NULL, conexao, (void *)cli_ultimo);
    }
}
