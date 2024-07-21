## Infra Docker setting
mac에서 가능합니다.

```text
brew install kcat
```
```text
cd ./infra/local
./start-up.sh

// 종료
./shutdown.sh
```

## Run API Module
Java version을 21로 세팅 후 실행해 주세요.

root project에서 실행해 주세요.
```
./build-image.sh
```
```text
./run-api-module.sh
```

## API Document
```
http://localhost:8080/swagger-ui/index.html
```

dev server용 yml파일은 숨김 처리 하였습니다.

local 서버에서 실행할 수 있게 application-local.yml 파일을 사용하실 수 있습니다.

## 시스템 구조
![image](https://github.com/user-attachments/assets/5aab764c-87aa-4afa-bd53-105deb022f2a)
