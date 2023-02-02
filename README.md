# SELAB-TODO
> basic todo with spring-mvc

# 요구명세서
> 일기 작성 서비스

## 목차
1. 소개
   1. 목적
2. 일반적인 기술 사항
   1. 제품의 기능
   2. 사용자 특성
   3. 제약사항
   4. 가정 및 의존성
3. 상세기능 요구 사항
   1. 기능적 요구 사항
      1. 기본적인 CRUD
         1. 등록
         2. 조회
         3. 수정
         4. 삭제

## 소개
### 1.i 목적
동건이형께 Todo 스터디를 완료했으므로 사이드 프로젝트를 진행하기 위하여 시작함
## 일반적인 기술 사항
### 2. i 제품의 기능
앱은 아래와 같은 기능들을 주요 기능으로 갖는다
* 단건 조회
* 범위 조회
* 전체 조회
* 수정
* 등록
* 삭제
* 전체 삭제
### 2.ii 사용자 특정
사용자는 일기를 생성할 수 있어야 한다. 생성은 Json 파일 형식으로 해야 한다


사용자는 일기 조회를 할 수 있어야 한다. 단건 조회는 ID를 입력했을 때 가능하며, 범위 조회는
월(Month)를 입력했을 때 가능해야 한다


사용자는 일기 수정이 가능해야 한다. 수정하는 것은 ID로 수정할 수 있도록 한다.


사용자는 일기 삭제가 가능해야 한다. 삭제는 ID를 통해 이루어지도록 한다.
### 2.iii 제약사항
* spring framework를 사용하여 구현한다
* MySQL과 Jpa를 사용하여 데이터베이스와 연결한다
* create-drop을 사용하기 때문에 데이터베이스에 데이터베이스 스키마를 만들지 않아도 된다

## 상세기능 요구 사항
### 3.i. 기능적 요구 사항
### 3.i.a 기본적인 CRUD
### 3.i.a.a 등록
* request : id, 등록 날짜, 제목, 내용
### 3.i.a.b 조회
#### 단건 조회
* request : id
#### 범위 조회
* request : Month
* response : page
#### 전체 조회
* request : none
* response : page
### 3.i.a.c 수정
* request : id
### 3.i.a.d 삭제
* request : none


