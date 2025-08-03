# n8n Chat Application

n8n 웹훅과 연동되는 실시간 채팅 애플리케이션입니다. Spring Boot와 Node.js 두 가지 백엔드 구현을 제공합니다.
<img width="864" height="504" alt="스크린샷 2025-08-03 180755" src="https://github.com/user-attachments/assets/65304bdd-61ed-4ed2-8ff3-6f296cbe2326" />
<img width="1023" height="370" alt="스크린샷 2025-08-03 140056" src="https://github.com/user-attachments/assets/742beae2-7b13-4ec7-8d58-665ab39077f2" />
<img width="480" height="419" alt="스크린샷 2025-08-03 180857" src="https://github.com/user-attachments/assets/40892588-3be1-4552-ac2b-bef00425186f" />
<img width="1267" height="400" alt="스크린샷 2025-08-03 180801" src="https://github.com/user-attachments/assets/35eceddd-decb-48c7-9082-92bc21f8e122" />
<img width="1559" height="802" alt="스크린샷 2025-08-03 140210" src="https://github.com/user-attachments/assets/12ff4004-4213-4fef-8aaa-1bef87bba092" />

## 📋 목차

- [프로젝트 개요](#프로젝트-개요)
- [기술 스택](#기술-스택)
- [프로젝트 구조](#프로젝트-구조)
- [설치 및 실행](#설치-및-실행)
- [API 명세](#api-명세)
- [사용법](#사용법)
- [환경 설정](#환경-설정)
- [개발 가이드](#개발-가이드)
- [문제 해결](#문제-해결)

## 🎯 프로젝트 개요

이 프로젝트는 n8n 워크플로우와 실시간으로 통신할 수 있는 채팅 인터페이스를 제공합니다. 사용자가 메시지를 입력하면 n8n 웹훅으로 전송되고, 처리된 응답을 실시간으로 받아볼 수 있습니다.

### 주요 기능

- 🔄 n8n 웹훅과의 실시간 통신
- 💬 직관적인 채팅 UI
- 🌐 CORS 지원으로 다양한 환경에서 접근 가능
- 🔧 Spring Boot와 Node.js 두 가지 백엔드 선택
- 📱 반응형 웹 인터페이스

## 🛠 기술 스택

### Backend (Spring Boot)
- **Java**: 17
- **Spring Boot**: 3.5.4
- **Spring Web**: RESTful API 구현
- **Gradle**: 빌드 도구

### Backend (Node.js)
- **Node.js**: Express 기반
- **Express**: 4.18.2
- **CORS**: 2.8.5

### Frontend
- **HTML5/CSS3**: 사용자 인터페이스
- **Vanilla JavaScript**: 클라이언트 로직
- **Fetch API**: 비동기 통신

## 📁 프로젝트 구조

```
n8n/
├── src/main/java/com/n8n/          # Spring Boot 소스
│   ├── N8nApplication.java         # 메인 애플리케이션
│   └── controller/
│       └── ChatController.java     # 채팅 컨트롤러
├── src/main/resources/
│   ├── application.properties      # Spring Boot 설정
│   └── static/
│       └── chat.html              # 정적 채팅 페이지
├── src/test/java/com/n8n/         # 테스트 코드
├── server.js                       # Node.js 서버
├── chat.html                       # 독립 채팅 페이지
├── package.json                    # Node.js 의존성
├── build.gradle                    # Gradle 설정
└── README.md                       # 프로젝트 문서
```

## 🚀 설치 및 실행

### 전제 조건

- Java 17 이상
- Node.js 16 이상
- Gradle (또는 Gradle Wrapper 사용)

### 1. Spring Boot 버전 실행

```bash
# 저장소 클론
git clone https://github.com/DungeonTalk/poc-n8n-test.git
cd poc-n8n-test

# Gradle로 실행
./gradlew bootRun

# 또는 JAR 빌드 후 실행
./gradlew build
java -jar build/libs/n8n-0.0.1-SNAPSHOT.jar
```

**접속**: http://localhost:8080/chat.html

### 2. Node.js 버전 실행

```bash
# 의존성 설치
npm install

# 서버 실행
npm start
```

**접속**: http://localhost:3000/chat.html

### 3. 개발 모드 (두 서버 동시 실행)

```bash
# 터미널 1: Spring Boot
./gradlew bootRun

# 터미널 2: Node.js
npm start
```

## 📡 API 명세

### POST /webhook

n8n 웹훅으로 메시지를 전송합니다.

#### 요청 (Request)

```json
{
  "message": "안녕하세요!"
}
```

#### 응답 (Response)

**성공 (200 OK)**
```json
{
  "message": "n8n에서 처리된 응답 메시지"
}
```

**에러 (500 Internal Server Error)**
```json
{
  "error": "n8n 웹훅 오류: 연결 실패"
}
```

#### 내부 처리 로직

1. 클라이언트 메시지 수신
2. n8n 호환 형식으로 변환:
   ```json
   {
     "chatInput": "사용자 메시지",
     "message": "사용자 메시지", 
     "sessionId": "e4920a5755e24f0da4b4ff26bf997d65",
     "action": "sendMessage",
     "timestamp": "현재 시간"
   }
   ```
3. n8n 웹훅으로 전송
4. 응답 처리 및 클라이언트 반환

## 💻 사용법

### 기본 채팅

1. 웹 브라우저에서 채팅 페이지 접속
2. 텍스트 입력창에 메시지 작성
3. **전송** 버튼 클릭 또는 Enter 키 입력
4. n8n에서 처리된 응답 확인

### 채팅 인터페이스 기능

- **실시간 메시지 전송**: Enter 키 또는 전송 버튼
- **자동 스크롤**: 새 메시지 시 자동으로 하단 스크롤
- **로딩 표시**: 메시지 처리 중 로딩 인디케이터 표시
- **에러 핸들링**: 연결 실패 시 에러 메시지 표시

## ⚙️ 환경 설정

### Spring Boot 설정 (`application.properties`)

```properties
spring.application.name=n8n
server.port=8080
```

### n8n 웹훅 URL 설정

**Spring Boot** (`ChatController.java`):
```java
private final String N8N_WEBHOOK_URL = "https://moonjunwon.app.n8n.cloud/webhook/77753ce3-6250-4dbc-b760-b58b2d5c3ffb";
```

**Node.js** (`server.js`):
```javascript
const N8N_WEBHOOK_URL = 'https://moonjunwon.app.n8n.cloud/webhook-test/77753ce3-6250-4dbc-b760-b58b2d5c3ffb';
```

### CORS 설정

두 백엔드 모두 모든 오리진 허용으로 설정되어 있습니다:
- Spring Boot: `@CrossOrigin(origins = "*")`
- Node.js: `app.use(cors())`

## 🔧 개발 가이드

### 빌드 명령어

```bash
# Gradle 빌드
./gradlew build

# 테스트 실행
./gradlew test

# 개발 모드 실행
./gradlew bootRun
```

### 코드 구조 설명

#### ChatController.java
- n8n 웹훅과의 통신 담당
- 메시지 형식 변환 및 에러 처리
- REST API 엔드포인트 제공

#### server.js
- Express 기반 경량 서버
- 정적 파일 서빙
- n8n 웹훅 프록시 기능

#### chat.html
- 반응형 채팅 UI
- Fetch API를 통한 비동기 통신
- 실시간 메시지 표시

### 커스터마이징

1. **웹훅 URL 변경**: 각 백엔드 파일에서 URL 수정
2. **포트 변경**: 
   - Spring Boot: `application.properties`
   - Node.js: `server.js`의 `PORT` 변수
3. **UI 수정**: `chat.html` 파일의 CSS/JavaScript 수정

## 🐛 문제 해결

### 자주 발생하는 문제

#### 1. 포트 충돌
```bash
Error: Port 8080 already in use
```
**해결**: `application.properties`에서 다른 포트로 변경

#### 2. n8n 웹훅 연결 실패
```bash
n8n 웹훅 오류: Connection refused
```
**해결**: 
- n8n 웹훅 URL 확인
- 네트워크 연결 상태 확인
- CORS 설정 확인

#### 3. 의존성 문제
```bash
Could not resolve dependencies
```
**해결**:
```bash
# Gradle 의존성 다시 다운로드
./gradlew clean build --refresh-dependencies

# Node.js 의존성 재설치
rm -rf node_modules package-lock.json
npm install
```

### 로그 확인

**Spring Boot 로그**:
```bash
# 애플리케이션 로그
./gradlew bootRun --info

# 특정 레벨 로그
./gradlew bootRun -Dlogging.level.com.n8n=DEBUG
```

**Node.js 로그**:
```bash
# 콘솔 출력 확인
npm start

# PM2 사용시
pm2 logs
```

### 테스트 방법

1. **수동 테스트**: 브라우저에서 직접 채팅 테스트
2. **API 테스트**: 
   ```bash
   curl -X POST http://localhost:8080/webhook \
     -H "Content-Type: application/json" \
     -d '{"message":"테스트 메시지"}'
   ```
3. **단위 테스트**: `./gradlew test`

## 📞 지원

- **이슈 리포팅**: [GitHub Issues](https://github.com/DungeonTalk/poc-n8n-test/issues)
- **기능 요청**: Pull Request 환영
- **문의사항**: 프로젝트 Discussions 활용

## 📝 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.

---

> 🚀 **빠른 시작**: `./gradlew bootRun` 실행 후 http://localhost:8080/chat.html 접속
