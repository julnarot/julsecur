### use java 17 ubuntu
``
export JAVA_HOME=/usr/lib/jvm/jdk-17
``
### run

``
./gradlew bootRun
``
## login enpoint

``
curl --location 'http://localhost:8080/api/v1/auth/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "julnarot",
    "lastName": ".....",
    "email": "julnarot@gmail.com",
    "password": "123456"
}'
``


### h2 console

``
http://localhost:8080/h2-console
``