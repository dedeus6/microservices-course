#Automatizando o processo de buildar a aplicação com o maven
FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


# IMAGEM DA MINHA APLICAÇÃO

#FROM é onde eu inicio minha imagem, ou seja, estou utilizando a imagem do openjdk para rodar o java dentro do meu container.
FROM openjdk:17
#WORKDIR é a pasta dentro do container onde minha aplicação vai ficar
WORKDIR /app
#COPY é o arquivo do meu projeto que eu quero jogar para dentro da pasta do container
COPY --from=build ./app/target/*.jar ./app.jar
#EXPOSE é para colocar a porta em que meu container será disponibilizado
EXPOSE 8761
#ENTRYPOINT é o comando que eu vou utilizar para buildar a aplicação
ENTRYPOINT java -jar app.jar