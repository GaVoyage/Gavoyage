//package com.gavoyage.config.oauthold;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Date;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gavoyage.config.auth.PrincipalDetails;
//import com.gavoyage.config.jwt.JwtProperties;
//import com.gavoyage.config.jwt.UserResponse;
//import com.gavoyage.config.oauthold.dto.PostUserOAuthReq;
//import com.gavoyage.config.oauthold.dto.PostUserOAuthRes;
//import com.gavoyage.user.service.UserServiceImpl;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class OAuthService {
//	
//	private final UserServiceImpl userService;
//	private final 
//	
//	@Transactional
//    public String getKakaoAccessToken (String code) throws Exception {
//        String accessToken = "";
//        String refreshToken = "";
//        String reqURL = "https://kauth.kakao.com/oauth/token";
//
//        /**
//         * 1. 카카오로 access token을 요청 하기 위해 보낼 request 생성
//         */
//        URL url = new URL(reqURL); // URL 객체 생성
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // URL에서 URL Connection 객체 얻어오기
//
//        conn.setRequestMethod("POST"); // URL 요청 메소드 지정
//        conn.setDoOutput(true);        // URLConnection이 서버에 데이터를 보낼 수 있는지 여부 설정(기본이 false라 true로 설정)
//
//        //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//        StringBuilder sb = new StringBuilder();
//        sb.append("grant_type=authorization_code");
//        sb.append("&client_id=93aa37e04cb78b70bbeefcf691721b51"); // REST_API_KEY
//        sb.append("&redirect_uri=http://localhost:8080/oauth/kakao"); // 인가코드 받은 redirect_uri
//        sb.append("&code=" + code);
//        bw.write(sb.toString());
//        bw.flush();
//
//        int responseCode = conn.getResponseCode(); // 응답의 헤더 필드를 읽을 때 별도로 connect() 메소드를 호출하지 않아도 암시적으로 연결이 설정됨
//        System.out.println("responseCode : " + responseCode);
//
//        /**
//         * 2. 연결에 대한 response boyd를 읽어 들임
//         */
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line = "";
//        String result = "";
//
//        while ((line = br.readLine()) != null) {
//            result += line;
//        }
//        System.out.println("response body : " + result);
//
//        /**
//         * 3. Gson 라이브러리로 response body의 json을 파싱 후 accessToken, refreshToken 값 조회
//         */
//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(result);
//
//        accessToken = element.getAsJsonObject().get("access_token").getAsString();
//        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
//
//        System.out.println("access_token : " + accessToken);
//        System.out.println("refresh_token : " + refreshToken);
//
//        br.close();
//        bw.close();
//
//        return accessToken;
//    }
//	
//	@Transactional
//    public PostUserOAuthRes createKakaoUser(String token) throws Exception { // access_token을 이용하여 사용자 정보 조회
//        String reqURL = "https://kapi.kakao.com/v2/user/me";
//
//        /**
//         * 1. 카카오로 access token을 요청 하기 위해 보낼 request 생성
//         */
//        URL url = new URL(reqURL); // URL 객체 생성
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // URL에서 URL Connection 객체 얻어오기
//
//        conn.setRequestMethod("POST"); // URL 요청 메소드 지정
//        conn.setDoOutput(true);     // URLConnection이 서버에 데이터를 보낼 수 있는지 여부 설정(기본이 false라 true로 설정)
//        conn.setRequestProperty("Authorization", "Bearer " + token); // 요청 헤더에 토큰을 api 명세에 맞추어 추가
//
//      
//        int responseCode = conn.getResponseCode(); // 응답의 헤더 필드를 읽을 때 별도로 connect() 메소드를 호출하지 않아도 암시적으로 연결이 설정됨, 결과 코드가 200이라면 성공
//        System.out.println("responseCode : " + responseCode);
//
//        /**
//         * 2. 연결에 대한 json 형태의  response boyd를 읽어 들임
//         */
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line = "";
//        String result = "";
//
//        while ((line = br.readLine()) != null) {
//            result += line;
//        }
//        System.out.println("response body : " + result);
//
//        /**
//         * 3. Gson 라이브러리로 response body의 json을 파싱 후 accessToken, refreshToken 값 조회
//         */
//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(result);
//
//        String email = "";
//        String nickname = "";
//        
//        boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
//        boolean hasNickname = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_nickname").getAsBoolean();
//        
//        if(!hasEmail || !hasNickname) { // 사용자가 이메일, 닉네임 정보 제공을 선택하지 않은 경우
//        	throw new Exception();
//        }
//        
//        int id = element.getAsJsonObject().get("id").getAsInt();
//        email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
//        nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
//
//        System.out.println("id : " + id);
//        System.out.println("email : " + email);
//        System.out.println("nickname : " + nickname);
//
//        PostUserOAuthReq postUserOAuthReq = new PostUserOAuthReq(email, "OAuthPW", nickname);
//        PostUserOAuthRes postUserOAuthRes = createUserOAuth(postUserOAuthReq);
//        br.close();
//
//        return postUserOAuthRes;
//    }
//	
//	
//	@Transactional
//    public PostUserOAuthRes createUserOAuth(PostUserOAuthReq postUserOAuthReq) throws Exception {
//        /**
//         * 회원 가입이 된 사용자인 경우
//         */
//        if (userService.emailCheck(postUserOAuthReq.getEmail()) == 1) {
//            Long userIdx = userService.findByUserEmail(postUserOAuthReq.getEmail()).getUserIdx();
//    		
//    		// JWT 토큰 생성
//    		String jwtToken = JWT.create()
//    				.withSubject(postUserOAuthReq.getEmail())
//    				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 유효 기간을 10분으로 지정
//    				.withClaim("email", postUserOAuthReq.getEmail())
//    				.withClaim("nickname", postUserOAuthReq.getNickname())
//    				.sign(Algorithm.HMAC512(JwtProperties.SECRETE)); // 시크릿 키 설정
//    		
//            return new PostUserOAuthRes(userIdx, jwtToken);
//        }
//        
//        return null;
//       
//    }
//}
//
