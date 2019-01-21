## 1. [Spring Security]()

#### Spring Security는 보안에서 중요한 요소인 인증과 권한 처리를 쉽게 하도록 도와줍니다.
- 인증은 일반적으로 로그인을 말합니다.
- 권한은 admin과 일반 user 사이에 권한을 달리하여 특정 페이지는 일반 user에게 접속하지 못하도록 막는것을 뜻합니다.

## 2. [OAuth2]()
- OAuth의1의 유저의 인증플로우, 전반적인 목적만 공유하고 OAuth의1.0을 새로 작성한것이다. OAuth의1.0과 OAuth의2.0의 차이는 앱 애플리케이션, 웹 애플리케이션, 데스크탑 애플리케이션등의 인증방식을 강화하고 Consumer에 개발 간소화를 중심으로 개발
#### OAuth2와 OAuth1의 차이점
- OAuth 1.0 에서 OAuth2.0 차이점은 일단 인증 절차 간소화 됨으로써 개발자들이 구현하기 더쉬워졌고, 기존에 사용하던 용어도 바뀌면서 Authorizaiton server와 Resource서버의 분리가 명시적으로 되었다.

##### 인증 절차 간소화

- 기능의 단순화, 기능과 규모의 확장성 등을 지원하기 위해 만들어 졌다.
- 기존의 OAuth1.0은 디지털 서명 기반이었지만 OAuth2.0의 암호화는 https에 맡김으로써 복잡한 디지털 서명에관한 로직을 요구하지 않기때문에 구현 자체가 개발자입장에서 쉬워짐.

#### OAuth2의 인증종류
##### 1. Authorization Code Grant
- 일반적인 웹사이트에서 소셜로그인과 같은 인증을 받을 때 가장 많이 쓰는 방식으로 기본적으로 지원하고 있는 방식
##### 2. Implicit Grant
- Public Client인 브라우저 기반의 애플리케이션(Javascript application)이나 모바일 애플리케이션에서 바로 Resource Server에 접근하여 사용할 수 있는 방식
##### 3. Resource Owner Password Credentials Grant
- Client에 아이디/패스워드를 받아 아이디/패스워드로 직접 access token을 받아오는 방식이다. Client가 신용이 없을 때에는 사용하기에 위험하다는 단점이 있다. 클라이언트가 확실한 신용이 보장될 때 사용할 수 있는 방식
##### 4. Client Credentials Grant
- 애플리케이션이 Confidential Client일 때 id와 secret을 가지고 인증하는 방식
##### 5. Device Code Grant
- 장치 코드 부여 유형은 브라우저가 없거나 입력이 제한된 장치에서 사용
##### 6. Refresh Token Grant
- 기존에 저장해둔 리프러시 토큰이 존재할 때 엑세스토큰 재발급 받을 필요가 있을 때 사용한다. 그리고 기존 액세스는 토큰이 만료된다.
