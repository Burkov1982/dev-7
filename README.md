# dev-7
Проект реализован с помощью Postgresql, TomCat 8.5.69, плагина SmartTomCat для Intellij IDEA.

Необходимо добавить конфигурацию SmartTomCat для запуска программы. Поля заполнить так:
1. TomCat Server: указать путь к папке TomCat
1. Deployment Direction: указать полный путь к пакету “webapp”
1. Context Path:/
1. Server port: любое значение, программа тестировалась 8080
1. Admin port: 8005

Для инициализации и заполнения базы данных в пакете "resources" есть файлы initDB.sql и populateDB.sql, вставьте данные из этих файлов в QuieryTool и запустите.
