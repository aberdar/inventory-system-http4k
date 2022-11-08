# Система инвентаризации

## Описание структуры приложения

### Библиотеки
Для реализации функционала приложение использует:
* Библиотека http4k для обработки HTTP-запросов.
* Шаблонизатор pebble для формирования HTML-страниц.
* Библиотека Foundation для CSS.
* Ktlint (kotlin linter) для статического анализа кода.
* Auth0.
* Система сборки приложения Gradle.

### Пакеты
* ru.ac.uniyar
  * ru.ac.uniyar.domain
    * ru.ac.uniyar.domain.operations - операции над данными
      * ru.ac.uniyar.domain.operations.computations - "соленое" хеширование паролей
    * ru.ac.uniyar.domain.storage - слой хранения данных
  * ru.ac.uniyar.web
    * ru.ac.uniyar.web.filters - фильтры
    * ru.ac.uniyar.web.handlers - обработчики HTTP-запросов
    * ru.ac.uniyar.web.models - модели шаблонов
    * ru.ac.uniyar.web.templates - расширение стандартных шаблонизаторов
* resources.ru.ac.uniyar
  * resources.ru.ac.uniyar.public - foundation
  * resources.ru.ac.uniyar.web.models - view model 
