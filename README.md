# Jisoo-Blog-Assignment

## 기술 스택

---

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white">
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<br>

<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white">

## 주의사항

---

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

## Use Case

---

<img src="https://github.com/JisooPyo/Jisoo-Blog-Assignment/assets/130378232/4b59b1d5-5741-48bf-ab32-16f2da105545"></img>


## 블로그 API

---

전체 게시글 목록 조회, 선택한 게시글 조회, 게시글 작성, 선택한 게시글 수정, 선택한 게시글 삭제 기능을 제공합니다.


| 구분      | API 명        | 설명              |
|---------|--------------|-----------------|
| 정보 조회   | 전체 게시글 목록 조회 | 전체 게시글을 조회합니다.  |
| 정보 조회   | 선택한 게시글 조회   | 선택한 게시글을 조회합니다. |
| 정보 생성   | 게시글 작성       | 게시글을 작성합니다.     |
| 정보 업데이트 | 선택한 게시글 수정   | 게시글을 수정합니다.     |
| 정보 삭제   | 선택한 게시글 삭제   | 게시글을 삭제합니다.     |

---

### 1. 전체 게시글 목록 조회

제목, 작성자명, 작성 내용, 작성 날짜를 조회합니다. 작성 날짜 기준 내림차순으로 정렬합니다.

#### Request

###### Request Syntax

| 메서드 | 요청 URL          |
|-----|-----------------|
| GET | JisooBlog/posts |

#### Response

###### Response Syntax

```
{
"id" : 1,
"posttitle" : "제목1",
"username" : "유저1",
"contents" : "내용1",
"createdAt" : "2023-06-16 at 05:44:36 KST",
"modifiedAt" : "2023-06-16 at 05:44:36 KST"
},
{
"id" : 2,
"posttitle" : "제목2",
"username" : "유저2",
"contents" : "내용2",
"createdAt" : "2023-06-16 at 05:47:45 KST",
"modifiedAt" : "2023-06-16 at 05:47:45 KST"
},
...
}
```

###### Response Elements

| 필드         | 타입     | 설명         |
|------------|--------|------------|
| id         | Long   | 게시글 구분 ID  |
| posttitle  | String | 게시글 제목     |
| username   | String | 게시글 작성자 이름 |
| contents   | String | 게시글 내용     |
| createdAt  | String | 게시글 작성 일시  |
| modifiedAt | String | 게시글 수정 일시  |

---

### 2. 선택한 게시글 조회

선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회합니다. (검색 기능 x. 간단한 게시글 조회 구현.)

#### Request

###### Request Syntax

| 메서드 | 요청 URL               |
|-----|----------------------|
| GET | JisooBlog/post/{id} |

###### Request Header

| 파라미터 | 타입   | 설명        |
|------|------|-----------|
| id   | Long | 게시글 구분 ID |

#### Response

###### Response Syntax

```
{
"id" : 1,
"posttitle" : "제목1",
"username" : "유저1",
"contents" : "내용1",
"createdAt" : "2023-06-16 at 05:44:36 KST",
"modifiedAt" : "2023-06-16 at 05:44:36 KST"
}   
```

###### Response Elements

| 필드         | 타입     | 설명         |
|------------|--------|------------|
| id         | Long   | 게시글 구분 ID  |
| posttitle  | String | 게시글 제목     |
| username   | String | 게시글 작성자 이름 |
| contents   | String | 게시글 내용     |
| createdAt  | String | 게시글 작성 일시  |
| modifiedAt | String | 게시글 수정 일시  |

---

### 3. 게시글 작성

제목, 작성자명, 비밀번호, 작성 내용을 저장하고 저장된 게시글을 Client로 반환합니다.

#### Request

###### Request Syntax

```
{
"posttitle" : "제목1",
"username" : "유저1",
"password" : "비밀번호1",
"contents" : "내용1"
}
```

| 메서드  | 요청 URL          |
|------|-----------------|
| POST | JisooBlog/posts |

###### Request Header

| 파라미터       | 타입             | 설명               |
|------------|----------------|------------------|
| requestDto | PostRequestDto | application/json |

###### Request Elements

| 파라미터      | 타입     | 설명         |
|-----------|--------|------------|
| posttitle | String | 게시글 제목     |
| username  | String | 게시글 작성자 이름 |
| password  | String | 게시글 비밀번호   |
| contents  | String | 게시글 내용     |

#### Response

###### Response Syntax

```
{
"id" : 1,
"posttitle" : "제목1",
"username" : "유저1",
"contents" : "내용1",
"createdAt" : "2023-06-16 at 05:44:36 KST",
"modifiedAt" : "2023-06-16 at 05:44:36 KST"
} 
```

###### Response Elements

| 파라미터       | 타입     | 설명         |
|------------|--------|------------|
| id         | Long   | 게시글 구분 ID  |
| posttitle  | String | 게시글 제목     |
| username   | String | 게시글 작성자 이름 |
| contents   | String | 게시글 내용     |
| createdAt  | String | 게시글 작성 일시  |
| modifiedAt | String | 게시글 수정 일시  |

---

### 4. 선택한 게시글 수정

수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client로 반환합니다.

#### Request

###### Request Syntax

| 메서드 | 요청 URL                |
|-----|-----------------------|
| PUT | JisooBlog/post/update |

###### Request Header

| 파라미터       | 타입             | 설명                                |
|------------|----------------|-----------------------------------|
| id         | Long           | application/x-www-form-urlencoded |
| password   | String         | application/x-www-form-urlencoded |
| requestDto | PostRequestDto | application/json                  |

###### Request Elements

| 파라미터      | 타입     | 설명         |
|-----------|--------|------------|
| posttitle | String | 게시글 제목     |
| username  | String | 게시글 작성자 이름 |
| contents  | String | 게시글 내용     |

#### Response

###### Response Syntax

```
{
"id" : 1,
"posttitle" : "제목1_2",
"username" : "유저1_2",
"contents" : "내용1_2",
"createdAt" : "2023-06-16 at 05:44:36 KST",
"modifiedAt" : "2023-06-16 at 05:53:43 KST"
}
```

###### Response Elements

| 파라미터       | 타입     | 설명         |
|------------|--------|------------|
| id         | Long   | 게시글 구분 ID  |
| posttitle  | String | 게시글 제목     |
| username   | String | 게시글 작성자 이름 |
| contents   | String | 게시글 내용     |
| createdAt  | String | 게시글 작성 일시  |
| modifiedAt | String | 게시글 수정 일시  |

---

### 5. 선택한 게시글 삭제

삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후, 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환합니다.

#### Request

###### Request Syntax

| 메서드    | 요청 URL                |
|--------|-----------------------|
| DELETE | JisooBlog/post/delete |

###### Request Header

| 파라미터     | 타입     | 설명                                |
|----------|--------|-----------------------------------|
| id       | Long   | application/x-www-form-urlencoded |
| password | String | application/x-www-form-urlencoded |

#### Response

###### Response Elements

| 파라미터 | 타입     | 설명                  |
|------|--------|---------------------|
|      | String | "글이 성공적으로 삭제되었습니다." |

