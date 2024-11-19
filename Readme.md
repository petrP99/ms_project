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

2. [ ] Таблица duties:

* experience_id
* duty_name

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