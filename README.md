Sistema de Controle de Estoque
=================
<!--ts-->
   * [Sobre](#Sobre)
   * [Como usar](#como-usar)
      * [Pre-Requisitos](#pre-requisitos)
      * [Clonar projeto](#clonar-projeto)
      * [Criar banco](#criar-banco)
      * [Configurar Hibernate](#configurar-hibernate)
   * [Tecnologias](#tecnologias)
   * [Autoras](#Autoras)
<!--te-->

# Sobre
O  objetivo do projeto √© simular um sistema de controle de estoque. Para isso, o projeto foi constru√≠do a partir de conhecimentos anteriores, bem como, os conhecimentos adquiridos durante a disciplina Linguagem de Programa√ß√£o II (IMD/UFRN)

# Como usar
  # Pre-requisitos
      - Java, PostgreSQL
  # Clonar projeto
      √â necess√°rio ter o GitBash
      No Windows
      ```
      git clone https://github.com/karinamaria/ProjetoGomoku.git
      ```
  # Criar banco
  	  No PostgreSQL crie um banco chamado `controle-estoque`
  # Configurar Hibernate
  	  1. No projeto clonado, v· atÈ o arquivo `persistence.xml` no caminho `src/main/resources/META-INF/persistence.xml`
  	  2. V· ate as seguintes linhas
  	  ```
  	   <property name="javax.persistence.jdbc.user" value="postgres" /> 
       <property name="javax.persistence.jdbc.password" value="root" />
      ```
      E troque o valor das linhas para configuraÁ„o do seu banco de dados.
  	  
# Tecnologias
- Java (vers√£o 14)
- JavaFx
- Scener Builder
- Maven
- PostgreSQL
- Hibernate

# Autoras
- Karina Maria - [@karinamaria](https://github.com/karinamaria)
- Maria Eduarda - [@mariaeloi](https://github.com/mariaeloi)
