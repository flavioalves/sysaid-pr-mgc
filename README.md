
# A quick start Java module for Sysaiud

### PASSO A PASSO PARA DEPLOY DO MÓDULO (MYSQL)

1. Rodar o script para criar os menus e as tabelas da aplicação
	- create_menu.sql 
	- your_tables_script_goes_here.sql

2. Adicionar a configuração de banco no arquivo context do tomcat 
- No tomcat de produção a configuração deve ser feita no arquivo server.xml 

<!-- Configuracao do MySql --!>
  <Resource name="jdbc/sysaid" auth="Container"
    type="javax.sql.DataSource" driverClassName="com.mysql.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/sysaid"
    username="root" password="rootdb" maxActive="20" maxIdle="10" maxWait="-1"/>
	
<!-- /Configuracao do MySql -->

3. No arquivo persistence.xml do projeto, verificar se a configuração do dialeto está setada corretamente:
  <property name="hibernate.dialect" value="org.hibernate.dialect.Oracle10gDialect"/>
  <property name="hibernate.default_schema" value="SYSAIDHOM"/>

4. Colocar a pasta "NOME_PROJETO", com os arquivos html que chamam em seus iframes as páginas desse módulo, dentro da pasta do sysad ($SYSAID_HOME/)


## Clone this repository

	Clone this repository:
	  git clone git@github.com:flavioalves/sysaid-module.git my_new_project
	  cd my_new_project
	
	
	Set git remote pointing to your repository:
	  git remote set-url origin https://github.com/path.git
	  git push -u origin master


	Rename the project to a name of your choosing and commit:
	  Eclipse > rename project 
	  git add .
	  git commit -am 'Renaming my project'
	
	