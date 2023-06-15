# Jisoo-Blog-Assignment

---

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

1. Entity를 그대로 반환 하지 말고, DTO에 담아서 반환할 것.
2. JSON을 반환하는 API 형태로 진행할 것.
3. PostMan에서 결과값을 확인해 볼 것.

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

