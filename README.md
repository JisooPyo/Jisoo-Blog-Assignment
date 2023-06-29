# Jisoo-Blog-Assignment

## 기술 스택

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"><img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<br>

<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white">

## 주의사항

<details>
<summary>주의 사항 보기</summary>

1. 회원 가입 API
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기


2. 로그인 API
    - username, password를 Client에서 전달받기
    - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고,
      발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기


3. 전체 게시글 목록 조회 API
    - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기


4. 게시글 작성 API
    - 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
    - 제목, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기(username은 로그인 된 사용자)


5. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기
      (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)


6. 선택한 게시글 수정 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
    - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기


7. 선택한 게시글 삭제 API
    - 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기

</details>

## ERD(Entity Relationship Diagram)

<img src="https://github.com/JisooPyo/Spring-Blog-Assignment/assets/130378232/0cc8d607-c91b-43ff-9839-6ad64c435207"></img>

## API

https://web.postman.co/workspace/My-Workspace~90e1a74b-8fa8-4987-8956-ac15dd3937a3/documentation/27928837-3bed5e63-58e1-4e73-82e6-61eef239af54

## Trouble Shooting

### 1. createdAt, modifiedAt 변수 값이 저장이 되지 않음

###### DB에 column은 만들어졌는데 변수 값이 저장이 안되고 null로 저장이 되었다.

-->

application에 @EnableJpaAuditing annotation을 하지 않아서 생긴 문제였다.
JPA Auditing 기능을 사용하려면 꼭 스프링부트에 annotation을 달아서 알려주어야 한다.

### 2. application을 실행시켰더니 다음 오류가 나고 아무것도 먹히지 않음.

###### This generated password is for development use only. Your security configuration must be updated before running your application in production.

-->

SpringBoot Security 문제로, 처음 시작할 때 만들어진 key를 이용해서 security configuration을 update 시켜주어야 한다.
간단하게 security를 잠깐 끄는 옵션을 찾아서 해결.
Application에 @SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) 추가

### 3. POST 요청 보냈을 때의 오류

###### java.lang.IllegalArgumentException: rawPassword cannot be null

-->

Controller에서 SignupRequestDto에 @RequestBody annotation 달아서 해결

### 4. POST 요청 -> DB에 회원정보 등록은 되었으나 ERROR 발생

###### 2023-06-29T01:42:20.033+09:00 ERROR 11688 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.thymeleaf.exceptions.TemplateInputException: Error resolving template [회원가입 완료], template might not exist or might not be accessible by any of the configured Template Resolvers] with root cause

-->

Controller 메서드에서 String을 반환하는 메서드에 "로그인 성공" 같은 걸 반환하게 하였더니, thymeleaf의 기능으로 그와 같은 이름의 html파일을 못 찾아서 오류가 남. 이 프로젝트에서는 뷰를 반환하지 않으므로 thymeleaf 의존성 주석처리해서 해결.

