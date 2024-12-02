http://kube.local:99/display/JD/Experience+Service

**1) Разработать Experience Service**

1. [ ] Таблица experience:

* id
* sequence_number
* period_from
* period_to
* present_time
* industry_id
* company
* position
* achievements
* link

Сделать два метода в контроллере:
`getExperienceById`
`add`

**2) Разработать Industry Service**

3. [ ] Таблица industries:

* id
* name

Примечание:

* Для того, чтобы получить информацию о предприятиях - отправить запрос по restTemplate на Industry Service
* Тесты не нужны
* В качестве БД H2 embedded

запуск докера

`docker run --name mykeycloak -p 8888:8080  -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=123 quay.io/keycloak/keycloak:latest start-dev`
в keycloak создать:

1. рилм security-realm
2. клиент security-client
3. добавить роли ROLE_USER, ROLE_ADMIN, ROLE_ADMIN_INDS
4. юзера
5. получить токен по адресу http://localhost:8080/realms/security-realm/protocol/openid-connect/token с body(x-www):

- client_id
- client_secret
- username
- password
- grant_type - password

6. получить все experinces http://localhost:9999/api/v1/exs/getAll
