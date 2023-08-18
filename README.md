# Vue-Spring
뷰와 스프링부트를 사용한 멀티게시판 개인 프로젝트

## 🖥️ 프로젝트 소개
뷰를 사용한 SPA방식의 사용자, 타임리프를 사용한 MPA방식의 관리자 멀티게시판 개인 프로젝트입니다.
## 🌏 웹 사이트
- [사용자 페이지](http://52.78.57.232:8080)
- 아이디 : test1122 비밀번호 : test1122!
- [관리자 페이지](http://52.78.57.232:8081/admin)
- 아이디 : admin 비밀번호 : admin

## 🕰️ 개발 기간
* 23.07.24 - 23.08.07

### ⚙️ 관리자 개발 환경
- `Java 11`
- **BackEnd** : Springboot 2.7
- **Template** : Thymeleaf
- **Database** : MySql
- **DB Framework** : Mybatis
- **Framework** : Spring Security 

### ⚙️ 사용자 개발 환경
- `Java 11`
- **BackEnd** : Springboot 2.7
- **FrontEnd** : Vue 3.2
- **Database** : MySql
- **DB Framework** : Mybatis
- **Framework** : Spring Security
- **Library** : Vuex, Jwt

### ⚙️ Infra
- AWS EC2, RDS


### ⚙ System Architecture
![image](https://github.com/kimjisoo1122/vue-spring/assets/101982291/47ca7589-26d5-47a5-9f13-688f1c3118e0)



## 📌 ERD
![vue_spring-erd](https://github.com/kimjisoo1122/vue-spring/assets/101982291/d855d045-f6fb-4c59-986a-36a9733694ac)

## 📌 주요 기능

#### 로그인 - <a href="https://github.com/kimjisoo1122/vue-spring/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C-(%EB%A1%9C%EA%B7%B8%EC%9D%B8)" >상세보기</a>
- 관리자 페이지 - 스프링 시큐리티 세션 기반 인증 진행
- 사용자 페이지 - 스프링 시큐리티 인증 후 JWT 엑세스토큰 발급 
#### 회원가입 - <a href="https://github.com/kimjisoo1122/vue-spring/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C-(%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85)" >상세보기</a>
- 회원가입
#### 인증 - <a href="https://github.com/kimjisoo1122/vue-spring/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C-(%EC%9D%B8%EC%A6%9D)" >상세보기</a>
- 사용자 JWT 인증
- 뷰 라우터 네비게이션 가드
#### 멀티게시판 - <a href="https://github.com/kimjisoo1122/vue-spring/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C-(%EB%A9%80%ED%8B%B0%EA%B2%8C%EC%8B%9C%ED%8C%90)" >상세보기</a>
- 자유게시글 등록, 수정 (파일첨부)
- 갤러리 등록, 조회 (파일첨부, 썸네일 조회)
- 관리자 통합게시판 템플릿
- 사용자 Vue

<br>

### 🖼 사용자 주요 화면

- **로그인, 회원가입**
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/d7f051d2-929a-48aa-b65d-7180e73adbd2" width="250" height="250"/>
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/e98fafc7-d0eb-404b-83e4-61ed57c2ccd9" width="250" height="250"/>
<br>
<br>

- **홈**
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/1669cced-cb60-4a71-96bf-1aca376bd106" width="500" height="250"/>
<br>
<br>

- **게시판**
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/889628df-bd2e-485d-a357-30e0837f6e0c" width="400" height="250"/>
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/59d1b5e0-0da7-4a1b-823f-7821c92f45d0" width="400" height="250"/>
<br>
<br>

- **게시판 상세**
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/76f1b263-1294-4da6-97a2-9c44e265157e" width="400" height="250"/>
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/367d02a0-00c2-4ba4-b256-f72e5a69b146" width="400" height="250"/>
<br>
<br>

- **등록, 수정**
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/28fdd600-f480-4edf-835c-b021a2d7d4e6" width="400" height="250"/>
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/1c6b9e77-6f00-4a7c-9678-6e5105bfc2f3" width="400" height="250"/>
<br>
<br>

### 🖼 관리자 주요 화면
- **홈**
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/e81a8f00-1c88-44f1-be3f-bb1793caae61" width="400" height="250"/>
<br>
<br>

- **게시판 관리**
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/0b713fd6-b666-42ac-838b-c52f4f6ccfa3" width="400" height="250"/>
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/e2de60f1-70fe-4651-93e7-787d2cb80d0a" width="400" height="250"/>
<br>
<br>

- **공지사항, 답변등록**
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/5c0fa0e9-0d7f-423e-b4cf-0f88950c3ac5" width="400" height="250"/>
<img src="https://github.com/kimjisoo1122/vue-spring/assets/101982291/be9b514c-f305-476d-b22d-3b0e06a2a890" width="400" height="250"/>
<br>
<br>

## 📄 [JavaDoc](http://portfoilo-vue-spring.s3-website.ap-northeast-2.amazonaws.com/)



