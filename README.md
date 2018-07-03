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
| jneuralnetwork | Реализации нейронных сетей |
| jpatterns | Классы обеспечивающие базовую реализацию паттернов программирования |

# jdb
Модуль ```jdb``` включает 4 подмодуля.

Таблица - Подмодули jdb

| **Модуль** | **Описание** |
|:--------:|-----------------------------------------|
| jdb-core | Базовый модудь для реализации остальных |
| jdb-jdbc | Взаимодействие с базой данных посредством классов Jdbc |
| jdb-spring | Взаимодействие с базой данных посредством классов Spring |
| jdb-hibernate | Взаимодействие с базой данных посредством классов Hibernate |

Обязательным модулем при использовании в проекте является ```jdb-core```, так как он определяет базовые классы и интерфейсы при реализии остальных подмодулей. Конечной реализацией являются проекты ```jdb-jdbc```, ```jdb-spring``` и ```jdb-hibernate``` (рекомендуется использовать один из данных вариантов реализации).

```jdb-core``` определяет следующие элементы проекта:
1. Entity - классы, которые опиывают сущность базы данных (являются отражением таблиц);
2. Dto - классы, дублирующие необходимую информацию Entity-классов, для использования пользователем (разработчиком);
3. Mapper - классы, которые предназначены для конвертирования объектов Entity-классов в Dto-классы и обратно;
4. Repository - классы, направленые на выполнение операций над таблицами базы данных. Один Repository связан с одним Entity и Dto, то есть, один Repository-класс определяет операции выполняемые над одной конкретной таблицей базы данных;
5. Migration - классы для выполнения миграции базы данных (***примечание:** неполная реализация*)
6. ManagerDatabase - класс, инкапсулирующий в себе основные возможности для работы с вышеописанными элементами.

Рекомендуемым способом взаимодействия с элементами ```jdb``` является использование класса ManagerDatabase. Также доступна версия ManagerDatabaseSingleton, которая реализует соответствующий паттерн Singleton, для ограничения количества создаваемых экземпляров объекта и предоставляения глобального доступа.

