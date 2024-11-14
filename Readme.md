**1) Разработать Experience Service**

`Таблица experience:
id
sequence_number
period_from
period_to
present_time
industry_id
company
position
achievements
link`

Таблица duties:
experience_id
duty_name

Сделать два метода в контроллере:
getExperienceById
add

Для того, чтобы получить информацию о предприятиях - отправить запрос по restTemplate на Industry Service

**2) Разработать Industry Service**

`Таблица industries:
id
name`

Примечание:

Тесты не нужны
В качестве БД H2 embedded