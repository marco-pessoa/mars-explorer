# mars-explorer
Elo7 technical evaluation project

Conforme solicitado este projeto foi implementado em duas fases: inicialmente 1) uma aplicação simples, capaz de receber um texto com instruções para a implementação e execução de uma missão exploratória em Marte; 2) adaptação da aplicação criada na fase anterior para atender solicitações por intermédio de um WebService REST.

##Fase 1: Aplicação Java/Standalone
A lógica para execução das instruções de uma missão exploratória foi implementada na fase 1 usando apenas as facilidades nativas da linguagem Java (versão 8) e testes unitários, criados com a biblioteca JUnit - lembrando que, desde o início, o gerenciamento de dependências foi feito utilizando a ferramenta Apache Maven. Além dos testes unitários, criei uma aplicação simples que lê as instruções da missão a partir de um arquivo texto fornecido pelo usuário:

   org.myhouseonmars.mars.explorer.standalone.ReadTextFileInstructions
   
Trata-se de uma classe executável em que deve-se passar, como parâmetro, o caminho para o arquivo texto com as instruções da missão.


##Fase 2: REST Service

Para a fase 2, dada a necessidade de disponibilizar a aplicação em um servidor WEB, optei por configurá-la à partir do framework Spring, que permite sua implantação em simples Servlet Servers como o Apache Tomcat e o Jetty, além de prover recursos como a Injeção de Dependências. Embora os requisitos não previssem a criação e execução de missões a partir de páginas WEB, deixei a aplicação pré-configurada para o uso do Spring MVC com a declaração de um DispatcherServlet. 

Embora o Spring facilite muito a criação de serviços REST (com simples anotações como @RestController e @RequestMapping), optei pelo uso da especificação JAX-RS em sua implementação oficial, o Jersey, por nenhum motivo em especial além da oportunidade de aprendizado, incluindo sua integração com o Spring, que mostrou-se um tanto trabalhosa. De todo modo essa integração foi bem-sucedida e foram criados dois métodos (exemplos de URL):

    http://<servidor>:<porta>/mars-explorer/api/rest/check
    
    (Simples método GET para verificar a disponibilidade do serviço)
    
    http://<servidor>:<porta>/mars-explorer/api/rest/mission
    
    (Método POST que recebe um arquivo texto com as instruções da missão)
    
Para os testes criei outra aplicação standalone, semelhante a da Fase 1 que, no entanto, implementa um cliente REST para o segundo método utilizando a biblioteca Jersey para enviar o arquivo fornecido pelo usuário ao serviço:

  org.myhouseonmars.mars.explorer.test.rest.MarsExplorerRestServiceClient

Trata-se de outra classe executável em que deve-se passar, como parâmetro, o caminho para o arquivo texto com as instruções da missão.
