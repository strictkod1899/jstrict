# Общие сведения
```jstrict``` предоставляет инструментарий для использования при реализации различного рода приложения (desktop, web).

# Подмодули
Таблица - Подмодули jstrict

| **Модуль** | **Описание** |
|:--------:|-----------------------------------------|
| jutils | Классы-утилиты, используемые для решения отдельных задач в статическом режиме |
| jfile | Взаимодействие с файловой системой |
| jdb | Взаимодействие с базой данных |
| jswing | Классы для реализации desktop-приложения с использованием swing |
| jfx | Классы для реализации desktop-приложения с использованием javafx |
| jneuralnetwork | Реализации нейронных сетей |
| jpatterns | Классы обеспечивающие базовую реализацию паттернов программирования |
| ioc | Простая реализация IoC-контейнера |
| jmq | Классы для реализации взаимодействия с очередями сообщений (например, RabbitMQ или Kafka)  |
| installer | Классы для реализации собственного процесса установки приложения |

# jdb
Модуль ```jdb``` включает 5 подмодулей.

Таблица - Подмодули jdb

| **Модуль** | **Описание** |
|:--------:|-----------------------------------------|
| jdb-core | Базовый модудь для реализации остальных |
| jdb-jdbc | Взаимодействие с базой данных посредством классов Jdbc |
| jdb-spring | Взаимодействие с базой данных посредством классов Spring |
| jdb-hibernate | Взаимодействие с базой данных посредством классов Hibernate |
| jdb-mybatis | Взаимодействие с базой данных посредством классов MyBatis |

Обязательным модулем при использовании в проекте является ```jdb-core```, так как он определяет базовые классы и интерфейсы при реализии остальных подмодулей. Конечной реализацией являются проекты ```jdb-jdbc```, ```jdb-spring```, ```jdb-hibernate``` и ```jdb-mybatis``` (рекомендуется использовать один из данных вариантов реализации).

```jdb-core``` определяет следующие элементы проекта:
1. Entity - классы, которые описывают сущность базы данных (являются отражением таблиц);
2. Dto - классы, дублирующие необходимую информацию Entity-классов, для использования пользователем (разработчиком);
3. Mapper - классы, которые предназначены для конвертирования объектов Entity-классов в Dto-классы и обратно;
4. Repository - классы, направленые на выполнение операций над таблицами базы данных. Один Repository связан с одним Entity и Dto, то есть, один Repository-класс определяет операции выполняемые над одной конкретной таблицей базы данных.

# jfile
Включает классы для взаимодействия с файловой системой.
Основные элементы:
1. PropertiesFileReader - взаимодействие с properties-файлами;
2. PropertiesResourceFileReader - взаимодействие с properties-файлами, хранящимися в jar-файле приложения.
3. AppConfig - расширение PropertiesResourceFileReader. Представляет инструмент для описания файла конфигурации приложения (по-умолчанию, app_development.properties) и считывания значений.
