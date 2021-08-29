## get started

**_requirements_**

1. java 16
2. postgresql
3. nodejs
4. npm

**_getstarted_**

run spring app
1. database schema will be created in automatically
2. test data will be filled in automatically 
3. after first run app, change application.properties `spring.jpa.hibernate.ddl-auto=update` `spring.sql.init.mode=never` so hibernate automatically will be create tables
and ddl script will not work out every init.

run front local server
1. go to `booking` folder 
2. install all dependencies by command `npm install`
3. run front local server by command `npm run serve`

