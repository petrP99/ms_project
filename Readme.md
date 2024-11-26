http://kube.local:99/display/JD/Experience+Service

**1) Разработать Experience Service**

запуск докера
docker run --name mykeycloak -p 8888:8080  -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=123 quay.io/keycloak/keycloak:latest start-dev



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