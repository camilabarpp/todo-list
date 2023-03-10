# ToDo List em Angular 15 e Java 17

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.4-blue.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-green.svg)](https://java.org/java/)
[![JUnit](https://img.shields.io/badge/JUnit-5.9.0-green.svg)](https://junit.org/junit5/)
[![Gradle](https://img.shields.io/badge/Gradle-7.6-red.svg)](https://gradle.org/)



# Para executar o projeto

Git clone no repositório todo-list
```bash 
https://github.com/camilabarpp/todo-list.git
```
Para abrir o projeto em Java, no terminal digite, para executar a api:
```
cd backend/todo-list-java/
gradle build
gradle bootRun
```

Para abrir o projeto em Angular, no terminal digite, para executar-la:
```
cd frontend/todo-list-angular/
npm i
npm run start
```


# 🚀 Sobre o projeto
É uma api de cadastro de tarefas, onde é possível criar, editar listar e deletar tarefas. Exemplo do request body abaixo:

```bash
    {
        "id": 2,
        "name": "Fazer feijão",
        "description": "Começar a fazer as 17:00",
        "completed": false,
        "weekDay": "Segunda-feira"
    },
```


# ****ENDPOINTS****

List all tasks (GET), Delete all tasks (DELETE), Create a task (POST)
```bash 
http://localhost:8080/api/v1/tasks
```
List a task by ID (GET), Delete a task by ID (DELETE), Update a task by ID (PUT)
```bash 
http://localhost:8080/api/v1/tasks/1
```

# Tecnologias utilizadas
## Backend
- Java 17
- Spring Boot
- Junit e Mockito
- Gradle
- MySQL

## Frontend
- Angular 15
- Angular CLI: 15
- Typescript 4.9
- Node: 18.14.2
- NGXS - Component Store



<img src="https://github.com/camilabarpp/todo-list/blob/main/frontend/desktop.png" alt="texto alternativo">

## Autor

Camila Ramão Barpp


[![Instagram Badge](https://img.shields.io/badge/-instagram-red?style=for-the-badge&logo=instagram&logoColor=white&link=https://github.com/camilabarpp)](https://www.instagram.com/camilabarpp/)
[![Linkedin Badge](https://img.shields.io/badge/-Linkedin-blue?style=for-the-badge&logo=Linkedin&logoColor=white&link=https://github.com/camilabarpp)](https://www.linkedin.com/in/camilabarpp/)
[![Spotify Badge](https://img.shields.io/badge/-Spotify-3bb34b?style=for-the-badge&logo=Spotify&logoColor=161f16&link=https://github.com/camilabarpp)](https://open.spotify.com/user/21o2si6ombl5lygoggs5m6bsy)




Outros repositórios

[![DoctorCare](https://img.shields.io/badge/DoctorCare-darkgreen.svg)](https://camilabarpp.github.io/DoctorCare/)
[![Pokedex](https://img.shields.io/badge/Pokedex-darkblue.svg)](https://camilabarpp.github.io/Pokedex/)
[![PeopleRegistration](https://img.shields.io/badge/PeopleRegistration-darkgreen.svg)](https://github.com/camilabarpp/people-registration)

