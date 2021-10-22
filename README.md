# <div align="center">Spring Security를 이용한 로그인 구현</div>

<br />

## 1. Spring Security 란 ?

### 개념 
- Spring Security는 보안 솔루션을 제공해주는 Spring 기반 하위 프레임워크
- Spring Security에서 제공해주는 보안 솔루션을 사용하면 개발자가 보안 로직을 짤 필요가 없어 편리함 
- 인증과 권한에 대한 뜻을 알아야 함 

### 인증과 권한 
- 인증은 자신이 <span style="color : red">'누구'라고 주장하는 사람이 정말 '누구'인지 확인</span>하는 작업 
- 권한은 <span style="color : red">특정 부분에 접근할 수 있는지 여부에 대해 확인</span>하는 작업 

<br />

## 2. Spring Security를 사용하는 이유 (아래 내용에 대해 공부 필요..) 
- 모든 URL에 대한 인증을 요구 
- 로그인 폼을 생성, 로그아웃 처리 
- CSRF 공격을 방어 
- Session Fixation 공격 방어 
- 요청 헤더 보안 
  - Http Strict Transport Security (HSTS)
  - X-content-Type-Options
  - 캐시 컨트롤 (정적 리소스는 캐싱)
  - X-XSS-Protection 
  - 클릭 재킹 방지를 위한 X-Frame-Options
- 서블릿 API 메소드와 통합 
  - HttpServletRequest#getRemoteUser()
  - HttpServletRequest.html#getUserPrincipal()
  - HttpServletRequest.html#isUserInRole(java.lang.String)
  - HttpServletRequest.html#login(java.lang.String, java.lang.String)
  - HttpServletRequest.html#logout()
 
<br />

## 3. 해당 예제의 기능
### 로그인 페이지 
- 로그인 할 수 있는 페이지 
- 누구나 접근 가능 
- 로그아웃 되면 보이는 페이지 
### 회원가입 페이지
- 회원가입을 할 수 있는 페이지
- 누구나 접근 가능 
### 유저 전용 페이지 
- 로그인 성공하면 이동하는 페이지
- 유저, 관리자만 접근 가능 
- 로그아웃 기능 
### 관리자 전용 페이지
- 관리자만 접근 가능 
- 로그아웃 기능 

<br />

## 4. 내용

<details>
<summary>의존성 라이브러</summary>

- Spring Web
- Thymeleaf
- JPA
- H2 Database
- Spring Security


</details>

<br/>
