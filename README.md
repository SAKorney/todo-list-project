# TODO-app
**Технологический стек:** 
- Java 17
- Maven
- Jersey
- Spring-boot
- Hibernate
- PostgreSQL
- Docker/Docker compose

**Запуск системы:**
1. docker-compose up
2. mvn spring-boot:run

P.s. База заполняется по умолчанию синтетическими данными для наглядности.

## API

### Фомат данных ###
> ``
{ "id": 0, "description": "Описание", "completed": false }
``

где:
- id - идентификатор задачи. При создании 0;
- description - описание задачи;
- completed - true - если выполнена, иначе - false.


### Получить все задачи ###  
**Вызов:**  
> ``GET ../api/v1/task``

**Результат:**  
> Список задач в формате JSON.

**Пример:**
> curl -X GET http://localhost:8080/api/v1/task

### Получить задачи с заданным статусом ###
**Вызов:**
> ``GET ../api/v1/task?status=VALUE``  
где VALUE одно из следующих значений:  
>- ***ACTIVE*** - действующие задачи  
>- ***COMPLETED*** - выполненые задачи  

**Результат:**  
> Список задач в формате JSON.
> 
**Пример:**
> curl -X GET http://localhost:8080/api/v1/task?status=ACTIVE  
> curl -X GET http://localhost:8080/api/v1/task?status=COMPLETED
### Получить задачу по идентификатору ###  
**Вызов:**  
>``GET ../api/v1/task/ID``  
где ID - идентификатор задачи.  
 
**Результат:**  
> Задача в формате JSON.  

**Пример:**
> curl -X GET http://localhost:8080/api/v1/task/1

### Создать новую задачу ### 
**Вызов:**  
>``
POST ../api/v1/task
``  
где в теле передаётся создаваемая задача в формате JSON.

**Результат:**  
>Созданная задача с присвоенным ID. Формат JSON.


**Пример:**
> curl -X POST http://localhost:8080/api/v1/task  \
> -H 'Content-Type: application/json' \
> -d '{ "id" : 0, "description" : "new task", "completed" : false }'

### Обновить задачу ### 
**Вызов:**  
> ``PUT ../api/v1/task``  
> где в теле передается обновляемая задача в формате JSON.

Результат:   
> Код **200**, если обновления применены.
**4XX** или **5XX** - в случае ошибки код.

**Пример:**
> curl -X PUT http://localhost:8080/api/v1/task  \
> -H 'Content-Type: application/json' \
> -d '{ "id" : 1, "description" : "new description", "completed" : false }'


