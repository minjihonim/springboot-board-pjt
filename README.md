# Framework-study backend API Tech Spec
- 프로젝트명: 게시판 CRUD API
- 작성자: 민지호
- 버전: 1.0
- 작성일: 2025-02-13
- 수정이력:
  - v1.0 - 최초 작성
 
## 1. 개요 (Overview)
- 목적
이 API는 게시판 CRUD 기능을 제공합니다.
추가적으로 회원가입, 로그인, 댓글 작성, 댓글 수정, 댓글 삭제, 예외처리 기능이 추가될 수 있습니다.

- 주요 기능
  - 전체 게시글 목록 조회
  - 게시글 작성
  - 선택한 게시글 조회
  - 선택한 게시글 삭제 기능

## 2. 시스템 아키텍처 (System Architecture)

- 기술 스택<br/>

|기술|사용목적|
|---|---|
|Spring Boot|백엔드 애플리케이션|
|JWT|토큰 발급 및 검증|
|Spring Security|인증 및 인가|
|MySQL|사용자 정보 저장|
|Docker|배포 환경|

- 아키텍처 다이어그램
``` scss
(Client) → [Server]
```

## 3. API 명세 (API Specification)
- api-docs 폴더에 등록

## 4. 비즈니스 로직 및 처리 흐름
- 토큰 발급 시퀀스
1. 사용자가 로그인시 비밀번호 검증 후 JWT발급
2. 세션에 유저ID, JWT 토큰 저장
3. 토큰을 응답으로 반환

- 토큰 검증 시퀀스
1. 클라이언트가 `Authorization` 헤더에 토큰 포함하여 요청
2. 서버 세션에 해당 토큰이 유효한지 확인
3. 유효하면 요청을 처리, 그렇지 않으면 `401 Unauthorized` 반환

## 5. 예외 처리 (Error Handling)  
|상황|HTTP 응답 코드| 설명|
|:---|:---|:---|
|잘못된 요청 파라미터|400 Bad Request|필수 값 누락|
|인증 실패|401 Unauthorized|유효하지 않은 토큰|

## 6. 배포 및 운영 계획
- CI/CD: GitHub Actions + Docker 기반 자동 배포

## 7. 테스트 계획
- 단위 테스트: JUnit, Mockito 를 활용하여 각 기능 테스트
- 기능 테스트: Postman 을 이용해 HTTP 메서드 요청 시도

## 8. 한계점 및 향후 개선 계획
- 토큰 탈취 대응을 위해 OAuth 2.0 인증 서버 도입 고려
