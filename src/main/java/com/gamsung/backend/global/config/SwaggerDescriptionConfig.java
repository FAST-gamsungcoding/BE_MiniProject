package com.gamsung.backend.global.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDescriptionConfig {

    public static final String ALL_ENTRY_DESCRIPTION = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "### 쿼리 파라미터\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| String | location | 검색을 원하는 지역 | Y |\n" +
            "| Number | page | 결과 페이지 번호\n" +
            "(기본값 1) | X |\n" +
            "| Number | size | 한 페이지에 보여질 상품 개수\n" +
            "(기본값 16) | X |\n" +
            "\n" +
            "```json\n" +
            "/v1/accommodations?location=\"서울\"\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 상품 조회 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Content[] | data | 응답 내용 |\n" +
            "\n" +
            "### Content\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | accommodation_id | 숙박 식별 값 |\n" +
            "| String | accommodation_name | 숙박 이름 |\n" +
            "| String | short_address | 도시 + 구  |\n" +
            "| Number | accommodation_price | 상품 가격 |\n" +
            "| String | accomodation_img | 숙박 외부 사진 |\n" +
            "\n" +
            "```json\n" +
            "\"code\" : 3000,\n" +
            "\"data\" : [\n" +
            "\t\t{\n" +
            "\t\t\t\"accommodation_id\" : 1,\n" +
            "\t\t\t\"accommodation_name\" : \"리거 로얄 라구니 괌 리조트,\"\n" +
            "\t\t\t\"short_address\" : \"경기도 경기시\",\t\t\n" +
            "\t\t\t\"accommodation_price\" : 240000,\n" +
            "      \"accomodation_img\" : \"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"accommodation_id\" : 6,\n" +
            "\t\t\t\"accommodation_name\" : \"리거 로얄 라구니 괌 리조트\",\n" +
            "\t\t\t\"short_address\" : \"경기도 고양시\",\t\t\n" +
            "\t\t\t\"accommodation_price\" : 360000,\n" +
            "      \"accomodation_img\" : \"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"accommodation_id\" : 7,\n" +
            "\t\t\t\"accommodation_name\" : \"리거 로얄 라구니 괌 리조트\",\n" +
            "\t\t\t\"short_address\" : \"경기도 수원시\",\t\t\n" +
            "\t\t\t\"accommodation_price\" : 40000,\n" +
            "      \"accomodation_img\" : \"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t},\n" +
            "\t\t...\t\t\n" +
            "]\n" +
            "```" ;
    public static final String ENTRY_DESCRIPTION = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "### 파라미터\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| id | product_id | 숙소 ID | Y |\n" +
            "\n" +
            "```jsx\n" +
            "/v1/accomodations/1\n" +
            "// 여기서 1은 숙소의 id 값 입니다.\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 개별 상품 조회 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | accommodation_name  | 숙소 이름 |\n" +
            "| String | description | 소개 글 |\n" +
            "| String | address | 상세 주소 |\n" +
            "| Number | limit_people | 최대 수용 가능 인원 |\n" +
            "| Number | accommodation_price | 숙소 비용 |\n" +
            "| String | accommodation_img | 숙소 외부 이미지 |\n" +
            "| List | room_img | 숙소 내부 이미지 (최소 1~5) |\n" +
            "\n" +
            "```jsx\n" +
            "{\n" +
            "\t\"code\" : 3001,\n" +
            "\t\"data\" : {\n" +
            "\t\t\"accommodation_name\" : \"신라 호텔\",\n" +
            "\t\t\"description\" : \"신라 호텔 입니다.\",\n" +
            "\t\t\"address\" : \"상세주소\",\n" +
            "\t\t\"limit_people\" : 4,\n" +
            "\t\t\"accommodation_price\" : 100000,\n" +
            "\t  \"accommodation_img\" : \"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t\"room_img\" : [\n" +
            "\t\t\t\t\"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t\t\t\"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t\t\t\"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t\t\t\"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t\t\t\"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\" \n" +
            "\t\t]\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "## 개별 상품 조회 실패 - 존재하지 않는 숙소id\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 오류 메시지 |\n" +
            "\n" +
            "```java\n" +
            "<해당 id의 숙박업소가 없을 경우>\n" +
            "{\n" +
            "\t\"code\" : 3002,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"해당하는 숙소를 찾을 수 없습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "``` ";

    public static final String FIND_MY_CART = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 헤더\n" +
            "\n" +
            "| 키 | 값 |\n" +
            "| --- | --- |\n" +
            "| authorization | “Bearer access토큰값” |\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 장바구니 요청 조회 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Content[] | data | 응답 내용 |\n" +
            "\n" +
            "### Content\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| Number | cart_id | 장바구니 ID | Y |\n" +
            "| Number | accommodation_id | 숙소 ID | Y |\n" +
            "| String | accommodation_name | 숙소명 | Y |\n" +
            "| String | address | 숙소 주소 | Y |\n" +
            "| DATE | start_date | 예약 시작날짜 | Y |\n" +
            "| DATE | end_date | 예약 종료날짜 | Y |\n" +
            "| Number | people_number | 예약 인원 | Y |\n" +
            "| Number | cart_price | 가격 | Y |\n" +
            "| String | accommodation_img | 숙소 이미지 | Y |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\"code\" : 4000,\n" +
            "\"data\" : [\n" +
            "    {\n" +
            "      \"cart_id\" : 1,\n" +
            "      \"product_id\" : 1,\n" +
            "      \"accommodation_name\" : \"가나모텔\",\n" +
            "\t\t\t\"address\" : \"서울시 강남구 청담대로 9\",\n" +
            "\t\t\t\"start_date\" : \"2023-01-23\",\n" +
            "\t\t\t\"end_date\" : \"2023-01-30\",\n" +
            "\t\t\t\"people_number\" : 2,\n" +
            "\t\t\t\"cart_price\" : 150000,\n" +
            "      \"accommodation_img\" :\"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "    },\n" +
            "\t\t{\n" +
            "      \"cart_id\" : 1,\n" +
            "      \"accommodation_id\" : 1,\n" +
            "      \"accommodation_name\" : \"가나모텔\",\n" +
            "\t\t\t\"address\" : \"서울시 강남구 청담대로 9\",\n" +
            "\t\t\t\"start_date\" : \"2023-01-23\",\n" +
            "\t\t\t\"end_date\" : \"2023-01-30\",\n" +
            "\t\t\t\"people_number\" : 2,\n" +
            "\t\t\t\"cart_price\" : 150000,\n" +
            "      \"accommodation_img\" :\"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "```";

    public static final String ENTRY_MY_CART = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 헤더\n" +
            "\n" +
            "| 키 | 값 |\n" +
            "| --- | --- |\n" +
            "| authorization | “Bearer access토큰값” |\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| Number | accommodation_id | 숙소 ID | Y |\n" +
            "| String | accommodation_name | 숙소명 | Y |\n" +
            "| String | address | 주소 | Y |\n" +
            "| Date | start_date | 예약 시작 날짜 | Y |\n" +
            "| Date | end_date | 예약 종료 날짜 | Y |\n" +
            "| Number | people_number | 예약 인원 | Y |\n" +
            "| Number | cart_price | 숙소 가격 | Y |\n" +
            "| String | accommodation_img | 숙소 사진 | Y |\n" +
            "\n" +
            "```json\n" +
            "\n" +
            "\"data\" : {\n" +
            "  {\n" +
            "    \"product_id\" : 1,\n" +
            "\t\t\"name\" : \"가산호텔\"\n" +
            "\t\t\"address\" : \"서울 숙소구\"\n" +
            "\t\t\"start_date\" : \"2023-01-23\"\n" +
            "\t\t\"end_date\" : \"2023-01-30\"\n" +
            "\t\t\"people\" : 2,\n" +
            "\t\t\"cart_price\" : 150000,\n" +
            "    \"accommodation_img\" : \"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\"\n" +
            "  }\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 장바구니 추가 요청 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String | message | 성공 메세지 |\n" +
            "\n" +
            "```json\n" +
            "\n" +
            "{\n" +
            "\t\"code\" : 4003,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"장바구니에 추가되었습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```";

    public static final String DELETE_MY_CART = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 헤더\n" +
            "\n" +
            "| 키 | 값 |\n" +
            "| --- | --- |\n" +
            "| authorization | “Bearer access토큰값” |\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| Number[] | cart_id | 장바구니 ID  | Y |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "  \"data\": {\n" +
            "    \"cart_id\": [1, 2, 3]\n" +
            "  }\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 장바구니 삭제 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 삭제 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 4003,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"선택한 항목들이 삭제되었습니다.\"\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "## 장바구니 삭제 실패 - id가 존재하지 않을 경우\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String | message | 오류 메시지 |\n" +
            "\n" +
            "```json\n" +
            "<장바구니 id가 존재하지 않을 경우>\n" +
            "{\n" +
            "\t\"code\" : 4004,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"삭제하고자 하는 제품이 존재하지 않습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```";

    public static final String MY_ORDER = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 헤더\n" +
            "\n" +
            "| 키 | 값 |\n" +
            "| --- | --- |\n" +
            "| authorization | “Bearer access토큰값” |\n" +
            "\n" +
            "### 쿼리 파라미터\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| Number | size | 한 페이지에 보여질 상품 개수\n" +
            "(기본값 8) | N |\n" +
            "| Number | page | 결과 페이지 번호\n" +
            "(기본값 1) | N |\n" +
            "\n" +
            "```jsx\n" +
            "/v1/order/me?size=8&page=3\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 예약 내역 조회 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| List[] | data | 응답 내용 |\n" +
            "\n" +
            "### Content\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| DATE | order_date | 주문일 |\n" +
            "| Number | accommodation_id | 숙소 id |\n" +
            "| String | accommodation_name | 숙소 이름 |\n" +
            "| String | accommodation_img | 숙소 이미지 url |\n" +
            "| Number | people_number | 예약인원 |\n" +
            "| DATE | start_date | 예약시작일 |\n" +
            "| DATE | end_date | 예약종료일 |\n" +
            "| String | representative_name | 대표자명 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 2000,\n" +
            "\t\"data\" : [\n" +
            "\t  {\n" +
            "\t\t\t\t\"order_date\" : \"2023-8-13\",\n" +
            "\t\t\t\t\"accommodation_id\" : 3,\n" +
            "\t\t\t\t\"accommodation_name\" : \"로터스모텔\",\n" +
            "\t\t\t\t\"accommodation_img\" : \"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t\t\t\"people_number\" : 1,\n" +
            "\t\t\t\t\"start_date\" : \"2023-08-20\",\n" +
            "\t\t\t\t\"end_date\" : \"2023-08-21\",\n" +
            "\t\t\t\t\"representative_name\" : \"이상혁\"\n" +
            "\t\t},\n" +
            "\t  {\n" +
            "\t\t\t\t\"order_date\" : \"2023-8-13\",\n" +
            "\t\t\t\t\"accommodation_id\" : 3,\n" +
            "\t\t\t\t\"accommodation_name\" : \"로터스모텔\",\n" +
            "\t\t\t\t\"accommodation_img\" : \"https://cdn.discordapp.com/attachments/1174993798766546984/1176059765785370624/image.png?ex=656d7e02&is=655b0902&hm=9653d7a75fae6974278eff3ae6ac4e2379b9c62794934460b311af64138048e2&\",\n" +
            "\t\t\t\t\"people_number\" : 1,\n" +
            "\t\t\t\t\"start_date\" : \"2023-08-25\",\n" +
            "\t\t\t\t\"end_date\" : \"2023-08-27\",\n" +
            "\t\t\t\t\"representative_name\" : \"이상혁\"\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}\n" +
            "```";
    public static final String CALENDAR_CHECK = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "### 쿼리 파라미터\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| Number | accommodation_id | 숙소id | Y |\n" +
            "| Date | start_date | 예약 시작날짜 | Y |\n" +
            "| Date | end_date | 예약 종료날짜 | Y |\n" +
            "\n" +
            "```jsx\n" +
            "/v1/order?accommodation_id=3&start_date=\"2023-11-01\"&end_date=\"2023-11-02\"\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 예약여부 확인 - 예약이 가능한 날짜\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String | message | 실패 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 2001,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\": \"해당 날짜는 예약 가능합니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "## 예약여부 확인 - 예약이 불가능한 날짜\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String | message | 실패 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 2002,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\": \"예약이 불가능한 날짜입니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "## 예약여부 확인 - 해당 id의 숙소를 찾을 수 없음\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String | message | 실패 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 2004,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\": \"해당하는 숙소가 없습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```";

    public static final String ENTRY_ORDER = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 헤더\n" +
            "\n" +
            "| 키 | 값 |\n" +
            "| --- | --- |\n" +
            "| authorization | “Bearer access토큰값” |\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 서비스 코드 규약 |\n" +
            "| Content[] | data | 요청 내용 |\n" +
            "\n" +
            "### Content\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| Number | accommodation_id | 숙소 id | Y |\n" +
            "| Number | people_number | 예약인원 | Y |\n" +
            "| DATE | start_date | 예약시작일 | Y |\n" +
            "| DATE | end_date | 예약종료일 | Y |\n" +
            "| String | representative_name | 대표자명 | Y |\n" +
            "| String | representative_email | 대표자 이메일 | Y |\n" +
            "| Number | order_price | 금액 | Y |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"data\" : [\n" +
            "\t\t{\n" +
            "\t\t\t\"accommodation_id\" : 3,\n" +
            "\t\t\t\"people_number\" : 2,\n" +
            "\t\t  \"start_date\" : \"2023-08-20\",\n" +
            "\t\t\t\"end_date\" : \"2023-08-21\",\n" +
            "\t\t\t\"representative_name\" : \"이상혁\",\n" +
            "\t\t\t\"representative_email\" : \"house1583@naver.com\",\n" +
            "\t\t\t\"order_price\" : 90000\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"accommodation_id\" : 3,\n" +
            "\t\t\t\"people_number\" : 2,\n" +
            "\t\t  \"start_date\" : \"2023-08-25\",\n" +
            "\t\t\t\"end_date\" : \"2023-08-27\",\n" +
            "\t\t\t\"representative_name\" : \"이상혁\",\n" +
            "\t\t\t\"representative_email\" : \"house1583@naver.com\",\n" +
            "\t\t\t\"order_price\" : 180000\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 결제 성공 - 장바구니에 동일 항목이 없음\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 성공 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 2004,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"결제가 성공하였습니다.\"          \n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "## 결제 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 성공 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 2004,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"결제가 성공하였습니다.\"          \n" +
            "\t}\n" +
            "}\n" +
            "```";
    public static final String MEMBER_LOGIN= "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| String  | email | 회원 아이디 | Y |\n" +
            "| String | password | 회원 비밀번호 | Y |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"data\" : {\n" +
            "\t  \"email\" : \"test@gmail.com\",\n" +
            "\t  \"password\" : \"a12345(암호화)\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 로그인 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | accessToken | JWT 액세스 토큰 |\n" +
            "| String | refeshToken | JWT 리프레시 토큰 |\n" +
            "| String | name | 회원 아이디 |\n" +
            "| String | email | 회워 이메일 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 1000,\n" +
            "\t\"data\" : {\n" +
            "\t  \"accessToken\" : \"access-token값\",\n" +
            "\t  \"refreshToken\" : \"refresh-token값\",\n" +
            "    \"name\" : \"박건우\",\n" +
            "    \"email\" : \"pgw111111@naver.com\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "## 로그인 실패\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 오류 메시지 |\n" +
            "\n" +
            "```json\n" +
            "<아이디가 틀렸을 경우>\n" +
            "{\n" +
            "\t\"code\" : 1001,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"회원 아이디가 존재하지 않습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "```json\n" +
            "<비밀번호가 틀렸을 경우>\n" +
            "{\n" +
            "\t\"code\" : 1002,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"비밀번호가 올바르지 않습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "``` ";

    public static final String MEMBER_RIGISTER = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| String  | email | 회원 아이디 | Y |\n" +
            "| String | password | 회원 비밀번호 | Y |\n" +
            "| String | name | 회원 이름 | Y |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"data\" : {\n" +
            "\t\t \"email\" : \"test@gmail.com\",\n" +
            "\t\t \"password\" : \"a12345(암호화)\",\n" +
            "\t\t \"name\" : \"박건우\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 회원가입 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 성공 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 1003,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"회원가입에 성공했습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "## 회원가입 실패 - 유효성 검사 탈락\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 오류 메시지 |\n" +
            "\n" +
            "```json\n" +
            "<회원가입 실패 - 이메일 유효성 검사 탈락>\n" +
            "{\n" +
            "\t\"code\" : 1004,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"이메일 형식이 올바르지 않습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "```json\n" +
            "<회원가입 실패 - 비밀번호 유효성 검사 탈락>\n" +
            "{\n" +
            "\t\"code\" : 1005,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"비밀번호 형식이 올바르지 않습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```";

    public static final String MEMBER_REGISTER_EMAIL_CHECK = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "### 쿼리 파라미터\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 | 필수여부 |\n" +
            "| --- | --- | --- | --- |\n" +
            "| String  | email | 회원 아이디 | Y |\n" +
            "\n" +
            "```jsx\n" +
            "/v1/member/register/check?email=\"test@gmail.com\"\n" +
            "```\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 아이디 중복체크 - 가입 가능 이메일(중복이 아님)\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 성공 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 1006,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"회원가입이 가능한 이메일입니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "## 아이디 중복체크 - 가입 불가능한 이메일(중복임)\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 오류 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 1007,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"회원가입이 되어있는 이메일입니다.\"          \n" +
            "\t}\n" +
            "}\n" +
            "```";

    public static final String MEMBER_LOGOUT = "# 요청\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 헤더\n" +
            "\n" +
            "| 키 | 값 |\n" +
            "| --- | --- |\n" +
            "| authorization | “Bearer access토큰값” |\n" +
            "\n" +
            "# 응답\n" +
            "\n" +
            "---\n" +
            "\n" +
            "## 로그아웃 성공\n" +
            "\n" +
            "## 바디\n" +
            "\n" +
            "### 본문\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| Number | code | 규약 코드 |\n" +
            "| Data | data | 응답 내용 |\n" +
            "\n" +
            "### Data\n" +
            "\n" +
            "| 타입 | 필드명 | 설명 |\n" +
            "| --- | --- | --- |\n" +
            "| String  | message | 성공 메시지 |\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "\t\"code\" : 1011,\n" +
            "\t\"data\" : {\n" +
            "\t  \"message\" : \"로그아웃에 성공했습니다.\"\n" +
            "\t}\n" +
            "}\n" +
            "```";
}
