./gradlew api-module:clean
./gradlew api-module:build -x test

docker build --platform linux/amd64 --build-arg STAGE=local -t bsj1209/smart-worker:latest api-module/.
#docker build --build-arg STAGE=docker -t bsj1209/smart-worker:dev api-module/.
