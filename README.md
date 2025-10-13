# 📱 CamFlex

대학교 학생들을 위한 자유로운 **중고거래 안드로이드 앱**

---

## 📖 소개

**CamFlex**는 대학교 학생들이 안전하고 편리하게 **중고거래**를 할 수 있도록 제작된 안드로이드 앱입니다.

캠퍼스 내에서 필요한 물품을 쉽게 사고팔 수 있도록 **간단한 UI**, **실시간 채팅**, **안전한 인증 기반 거래** 기능을 제공합니다.

---

## ✨ 주요 기능

- 🔍 **카테고리별 상품 검색**
    
    교재, 가전, 생활용품 등 카테고리 필터를 통해 원하는 상품을 쉽게 찾을 수 있음
    
- 📸 **상품 등록**
    
    사진 업로드 + 제목 + 설명 입력을 통해 판매자가 간편하게 상품 등록 가능
    
- 💬 **실시간 채팅**
    
    구매자와 판매자가 직접 소통할 수 있는 1:1 채팅 기능 제공
    
- 🏷️ **관심 상품 찜하기**
    
    마음에 드는 상품을 저장해두고 나중에 쉽게 확인 가능
    
- 👤 **사용자 프로필 관리**
    
    닉네임, 학과, 연락처 등 사용자 정보 수정 가능
    
- 🏫 **대학교 인증 시스템**
    
    학교 이메일을 통한 인증을 통해 캠퍼스 학생만 이용 가능
    

---

## 🏗️ 아키텍처 및 구축 방식

CamFlex는 **MVC(Model-View-Controller) 패턴**을 기반으로 개발되었습니다.

1. **Android App (View + Controller)**
    - Java 기반, Android Studio와 IntelliJ IDEA 사용
    - 상품 등록, 검색, 채팅, 사용자 관리 기능 구현
2. **Server (Model + Controller)**
    - Spring Boot 기반 API 서버 (MVC 구조)
    - 사용자 인증, 거래 데이터 처리, 채팅 관리
3. **Database (Model)**
    - Docker 환경에서 구동되는 **MySQL** 사용
    - 사용자 정보, 상품 데이터, 채팅 로그 저장
4. **형상 관리**
    - Git & GitHub을 통한 버전 관리 및 협업

---

## ⚙️ 개발 환경

- **언어**: Java
- **IDE**: Android Studio, IntelliJ IDEA
- **DB**: MySQL (Docker 환경)
- **아키텍처**: MVC (Model-View-Controller)
- **형상 관리**: Git / GitHub

---

## 🚀 설치 및 실행

```bash
# 저장소 클론
git clone <https://github.com/your-username/CamFlex.git>
cd CamFlex

# Docker MySQL 실행
docker pull mysql:latest
docker run --name camflex-mysql -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql

# Android Studio 실행 후
# 1. 프로젝트 열기
# 2. Gradle Sync 실행
# 3. 앱 실행

```
