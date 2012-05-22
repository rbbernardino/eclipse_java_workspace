#include  <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <pthread.h>

#define SERV_PORTA 1234
#define PROTOCOLO 0

#define BUFF_SIZE 50

int sock_cli, recv_len;
socklen_t cli_len;

char ip[16]; int i1, i2, i3, i4;
int result;

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
      if(i != n-2) // 2 pq falta acrescer o \0
	{
	  buffer[i] = c;
	  i++;
	}
    }
  buffer[i] = '\0';
}

void valida_linha(char *buff_cmd, char *buff_file,  int n)
{
  char c;
  int i = 0;
  
  // acumula comando até achar espaço ou fim da linha! 
  while(1)
    {
      c=getchar();
      if( (c == ' ') || (c == '\n') )
	  break;

      if(i != n-2) // 2 pq falta acrescer o \0
	{
	  buff_cmd[i] = c;
	  i++;
	}
    }

  buff_cmd[i] = '\0';

  i = 0; 
  while(1)
    {
      c=getchar();
      if( (c == '\n') )
	  break;

      if(i != n-2) 
	{
	  buff_file[i] = c;
	  i++;
	}
    }
  buff_file[i] = '\0';
  printf("comando: %s\n", buff_cmd);
  printf("nome do arquivo: %s\n\n", buff_file);
}

void le_ip(char *buffer, char *ip)
{
  le_linha(buffer, BUFF_SIZE);
  
  while(sscanf(buffer, "%d.%d.%d.%d", &i1, &i2, &i3, &i4) != 4)
    {
      printf("IP INVALIDO! digite no seguinte formato: NNN.NNN.NNN.NNN\n");
      printf("digite ip:\n");
      le_linha(buffer, BUFF_SIZE);
    }

  sprintf(ip, "%d.%d.%d.%d", i1, i2, i3, i4);
}

void upload(char *nome_arquivo)
{
  FILE *file, *copia;
  char buffer[11], buffer_msg[BUFF_SIZE];
  char ch;
  int i, tam_bloco;
  
  if( (file=fopen( nome_arquivo, "rb" )) == NULL )
    {
      printf("arquivo \"%s\" nao existe!\n\n", nome_arquivo);
      return;
    }

  puts("ARQUIVO EXISTE!");

  sprintf(buffer_msg, "UPLOAD %s", nome_arquivo);
  
  if( send(sock_cli, buffer_msg, strlen(buffer_msg),0) < 0 )
    {
      perror("Erro ao Enviar Requisicao!!");
    }

  puts("Esperando resposta Servidor...");

  //copia = fopen( nome_arquivo, "wb" );
  

  while( !feof(file) )
    {
      for(i = 1; i < 11; i++)
	{
	  ch = getc(file);
	  buffer[i] = ch;
	  if(feof(file))
	    break;
	}
      
      buffer[0] = i;
      /*    for(i = 1; i < buffer[0]; i++)
	{
	  putc(buffer[i], copia);
	}
      */
    }
    
  fclose(file);
}

void download(char *nome_arquivo)
{
 

}

void list()
{

}


int main()
{
  char buffer[BUFF_SIZE], ip[15];
  char buff_file[BUFF_SIZE], buff_cmd[BUFF_SIZE];
  struct sockaddr_in serv_addr;

  if((sock_cli = socket(PF_INET, SOCK_STREAM, PROTOCOLO))<0)
    {
      erro("socket falhou");
    }
  printf("socket() ok!\n");
  
  printf("digite ip:\n");
  le_ip(buffer, ip);
  
  serv_addr.sin_family = AF_INET; 
  serv_addr.sin_addr.s_addr = inet_addr(ip);
  serv_addr.sin_port = htons(SERV_PORTA);  
  
  if(connect(sock_cli, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0)
    {
      erro("connect falhou");
    }
  printf("connect() ok!\n");
  
  while(1)
    {
      puts("Digite sua Requisição: ");

      /* valida_linha:
       *  -  le comando e armazena em buff_cmd
       *  -  le o resto e armazena em buff_file
       */
      bzero(buffer, BUFF_SIZE-1);
      valida_linha(buff_cmd, buff_file, BUFF_SIZE);

      if ( 0 == strcmp(buff_cmd, "upload" ))
	{
	  upload(buff_file);
	  puts("Arquivo Enviado com Sucesso!");
	  close(sock_cli);
	  exit(0);
	}
      else if ( 0 == strcmp(buff_cmd, "download" ))
	{
	  // download(buff_file);
	  puts("download digitado , faz nada!");
	  close(sock_cli);
	  exit(0);
	}
      else if ( 0 == strcmp(buff_cmd, "list" ))
	{
	  //list();
	  puts("list digitado , faz nada!");
	  close(sock_cli);
	  exit(0);
	}      
      else if ( 0 == strcmp(buff_cmd, "quit" ))
	{
	  close(sock_cli);
	  exit(0);
	}
      else
	{
	  puts("comando invalido!");
	  close(sock_cli);
	  exit(0);
	}


      if(send(sock_cli, buffer, strlen(buffer), 0) != strlen(buffer))
	{
	  erro("send falhou");
	}
      puts("requisicao enviada!");

      bzero(buffer, BUFF_SIZE-1);
      if((recv_len = recv(sock_cli, buffer, 4, 0)) < 0)
	{
	  erro("recv falhou");
	}
    }
}
