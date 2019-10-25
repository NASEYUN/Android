## Backend Service 이용 vs Open Source SW를 이용하여 직접 Backend Server 구축
Backend Service의 장점
* 개발 속도가 빠르다.
* 개발 가격이 저렴하다.
* 서버가 필요하지 않고 인프라를 관리할 필요가 없다.

개발 기간이나 비용 측면에서 Opensource SW Backend Server를 이용하여 서버를 직접 개발하는 것보다 Backend Service를 이용하는 것이 많은 장점이 있다.

## 쇼핑몰 앱 기능
1. 로그인/사용자 관리 기능
2. 쇼핑 아이템을 위한 데이터 원격 저장/관리 기능
3. 이벤트 알림을 위한 푸시 알림
4. 가격 비교
5. 유사 상품 추천

## Backend Service 비교
### 1. Firebase
#### 장점
* 로그인/사용자 관리 기능
소셜로그인기능과 로그인화면이 구현되어있다.
* 데이터 액세스
데이터는 FireStore에 저장해서 관리한다.
* 푸시 알림 보내기
FCM(Firebase Cloud Messaging)을 통해 푸시 알림 해준다. 
푸시알림은 무제한으로 제공된다.
* Firebase 원격 구성을 사용하면 업데이트할 필요없이 수정사항을 동적으로 업데이트를 해준다.

#### 비용
Flame 요금제-매월 $25
![KakaoTalk_20191017_202628212](https://user-images.githubusercontent.com/43204011/67005850-88538a80-f11e-11e9-802f-de59759f5a9a.png)

### 2. Back4App
#### 장점
* 로그인/사용자 관리 기능
기존 로그인 또는 타사 소셜 네트워크를 통해 사용자를 연결하여 소셜 로그인 기능 제공한다.
* 푸시 알림 보내기
Parse Server는 사용자 계정 관리에 필요한 많은 기능을 자동으로 처리하는 PFUser라는 특수한 사용자 클래스를 제공하여 쉬운 사용자 계정 관리 가능하다.
체널을 설정하여 구독하는 모든 사용자에게 쉽게 푸시 보낼 수 있음 매월 백만 개의 장치에 알림을 무제한으로 제공한다.
* 데이터 액세스
Back4App Smart Indexing은 수신 쿼리 스트림에 따라 인덱스를 자동으로 생성 할 수 있습니다.
이를 통해 Mongo 인스턴스를보다 효과적으로 관리 할 수   있습니다.

##### 단점
속도가 비교적 느려 소규모 스타트업에 적합하다

##### 비용
db 2GB / file storage 50GB - $25
![KakaoTalk_20191017_202607708](https://user-images.githubusercontent.com/43204011/67005816-7540ba80-f11e-11e9-874d-ae555c83f27e.png)

### 3. Azure Mobile Apps
##### 장점
* 로그인/사용자 관리 기능
Facebook, Twitter, Microsoft 또는 Google 계정으로 사용자를 인증할 수 있다.
직원용 모바일 앱을 위해 기업 자체 Active Directory에 연결하듯이 응용 프로그램별 인증 시스템을 구현하는 방식도 지원한다.
* 데이터 액세스
 Azure Table storage , MongoDB, Azure Cosmos DB 및 SaaS API 공급자 (예 : Office 365 및 Salesforce.com)를 포함한 다른 NoSQL 및 SQL 데이터 공급자와 쉽게 통합 할 수 있다 .
* 모든 모바일 플랫폼을 지원한다.
기본 개발 ( iOS , Android 및 Windows ), 플랫폼 간 개발 ( Xamarin.iOS 및 Xamarin.Android , Xamarin.Forms ) 및 하이브리드 애플리케이션 개발 ( Apache Cordova ) 을 다루는 완전한 클라이언트 SDK를 지원한다.
기존 앱을 연결하거나 새로운 앱을 작성할 수 있다.
* 푸시 알림 보내기
Azure Notification Hubs의 등록 기능과 완벽하게 통합되어 다양한 플랫폼의 수많은 장치에 푸시 알림을 브로드캐스트 할 수 있다.
* 모든 개발 도구 또는 언어를 사용하여 개발할 수 있다.
JavaScript, C#, Objective-C, Java등의 언어로 쉽게 모바일 서비스가 사용할 데이터를 저장, 소비할 수 있다.

##### 단점
가격이 비싸다.

##### 비용
50GB - $72
![KakaoTalk_20191017_203002867](https://user-images.githubusercontent.com/43204011/67005862-93a6b600-f11e-11e9-8045-0c85d4cd9683.png)

## 선택한 Backend Mobile Service - Filebase

Firebase file storage - $25 <br>
BACK4APP - DB 2GB / file storage 50GB - $25 <br>
Azure Mobile Apps - file storage 50GB - $72 

<p>
→ 비용적 측면에서 Firebase와 Back4app이 효율적이다. <br>
그러나 Back4app는 속도가 비교적 느려 소규모 스타트업에 적합하다는 단점이 있다.
<p>
**Back4app의 이러한 단점**과 **Firebase의 장점**(특히 FCM을 통해 무제한으로 푸시 알림을 제공한다)을 고려하여 **Firebase를 선택**하였다.
