# 💇‍♀️ Hair Salon API

## 헤어샵 예약 API

> Spring Boot를 이용해서 RESTful Web Services 애플리케이션을 개발 과정을 학습하기 위해 작성된 프로젝트입니다.

## [요구사항](./REQUIREMENTS.md)

## API 문서

***HTTP Status***

| Code | Description       |
|------|-------------------|
| 200  | 정상 응답             |
| 400  | 요청 값 오류           |
| 404  | 헤어샵 정보를 찾지 못함     |
| 409  | 해당 시간에 이미 예약이 존재함 |

### 헤어샵 목록 보기

- URL : /shop
- Method : ***GET***

***Query Params***

| Parameter | Type    | Description           |
|-----------|---------|-----------------------|
| page      | number  | 페이지 번호                |
| size      | number  | 한 페이지에 보여줄 데이터의 수     |   


***Response***

```json
{
  "code": 200,
  "httpStatus": "OK",
  "message": "success",
  "data": {
    "content": [
      {
        "shopId": 1,
        "shopName": "비롬헤어 정자본점",
        "shopThumbUrl": "/images/shop1.jpg",
        "shopAddress": "경기 성남시 분당구 불정로77번길 3 1층",
        "shopBusinessHours": null,
        "shopTelNumber": "031-711-4868"
      },
      {
        "shopId": 2,
        "shopName": "아데르 역삼점",
        "shopThumbUrl": "/images/shop2.jpg",
        "shopAddress": "서울 강남구 테헤란로 142 아크플레이스빌딩 B1층",
        "shopBusinessHours": null,
        "shopTelNumber": "0507-1349-1172"
      }
    ],
    "pageable": {
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "offset": 0,
      "pageSize": 2,
      "pageNumber": 0,
      "paged": true,
      "unpaged": false
    },
    "last": false,
    "totalElements": 3,
    "totalPages": 2,
    "size": 2,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
  }
}
```


### 헤어샵 정보 보기

- URL : /shop/{shopId}
- Method : ***GET***

***Path Params***

| Parameter | Type    | Description |
|-----------|---------|-------------|
| shopId    | number  | 헤어샵 고유 번호   |


***Response***

```json
{
    "code": 200,
    "httpStatus": "OK",
    "message": "success",
    "data": {
        "shop": {
            "shopId": 1,
            "shopName": "비롬헤어 정자본점",
            "shopThumbUrl": "/images/shop1.jpg",
            "shopAddress": "경기 성남시 분당구 불정로77번길 3 1층",
            "shopBusinessHours": {
                "토": "10:00 - 19:00",
                "월": "정기휴무 (매주 월요일)",
                "화": "10:00 - 19:00",
                "수": "10:00 - 19:00",
                "금": "10:00 - 19:00",
                "목": "10:00 - 19:00",
                "일": "10:00 - 19:00"
            },
            "shopTelNumber": "031-711-4868",
            "shopMessage": "비롬헤어는 젊은 느낌의 트렌디한 헤어스타일을 추구합니다.\n최고의 실력과 센스를 겸비한 디자이너에게 프라이빗 한 공간에서 스타일링을 받아보세요:)\n또한, 저렴한 약제는 취급하지 않으며 고퀄리티의 약제만 사용하는 것을 약속 드립니다.\n\n100% 예약제로 운영되고 있습니다 :)"
        },
        "procedures": [
            {
                "procedureId": 1,
                "procedureName": "여성커트",
                "procedurePrice": 18000,
                "procedureHours": null
            },
            ...
        ],
        "stylists": [
            {
                "stylistId": 1,
                "stylistName": "영우 디자이너",
                "stylistMessage": "안녕하세요.",
                "stylistThumbUrl": "/images/stylist1.jpg"
            },
            ...
        ]
    }
}
```

### 헤어샵 예약하기

- URL : /reservation
- Method : ***POST***

***Headers***

| Key            | Value            | Description    |
|----------------|------------------|----------------|
| Content-Type   | application/json | 헤어샵 고유 번호      |
| X-API-USER-ID  | (number)         | 회원 고유 번호       |

***Body(json)***

| Key             | Type             | Description        |
|-----------------|------------------|--------------------|
| shopId          | number           | 헤어샵 고유 번호          |
| reservationDate | string           | 예약일자(YYYY-MM-DD)   |
| reservationTime | string           | 예약시간(HH:MI)        |
| stylistId       | number           | 스타일리스트 고유 번호       |
| procedureId     | number           | 시술 고유 번호           |

***Response***

```json
{
  "code": 200,
  "httpStatus": "OK",
  "message": "예약이 완료되었습니다.",
  "result": true
}
```

### 나의 헤어샵 예약 목록 보기

- URL : /reservation
- Method : ***GET***

***Query Params***

| Parameter | Type    | Description           |
|-----------|---------|-----------------------|
| page      | number  | 페이지 번호                |
| size      | number  | 한 페이지에 보여줄 데이터의 수     |   

***Headers***

| Key            | Type    | Description    |
|----------------|---------|----------------|
| X-API-USER-ID  | number  | 회원 고유 번호       |


***Response***
```json
{
    "code": 200,
    "httpStatus": "OK",
    "message": "success",
    "data": {
        "content": [
            {
                "reservationId": 1,
                "reservationStartTime": "2023-05-12T12:31:00",
                "reservationEndTime": "2023-05-12T13:01:00",
                "reservationMinutes": 30,
                "shopId": 1,
                "stylistId": 1,
                "procedureId": 1,
                "reservationShopName": "비롬헤어 정자본점",
                "reservationStylistName": "영우 디자이너",
                "reservationProcedureName": "여성커트",
                "price": 18000
            },
            ...
        ],
        "pageable": {
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 2,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalElements": 1,
        "totalPages": 1,
        "size": 2,
        "number": 0,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "first": true,
        "numberOfElements": 1,
        "empty": false
    }
}
```

## 테스트

### Shop
- should_get_shop_list() : 헤어샵 목록 조회 테스트
- should_get_shop_info() : 헤어샵 정보 조회 테스트

### Reservation
- should_save_reservation() : 헤어샵 예약 테스트
- should_check_reservation() : 헤어샵 예약시간 중복 검사 테스트
- should_get_my_reservation_list() : 나의 예약 목록 조회 테스트