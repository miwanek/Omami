Omami-backend
================

Simple web chat written in java/js.

URL to web application
------------
[Omami - best chat ever made](https://omami.herokuapp.com/)
 
How to run application locally
------------

- Prepare database docker image:
```
$ docker build  docker  -t omami-postgres:1.0
```

- Create container from prepared image:
```
$ docker run -d -p 5432:5432 omami-postgres:1.0
```

- Run backend application in dev profile
```
$ gradlew :backend:bootRun -Pdev
```

- Run frontend application
```
$ gradlew :frontend:npm_start 
```



