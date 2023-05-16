package com.gavoyage.config.oauth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.config.oauth.dto.PostUserOAuthRes;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {
	private final OAuthService oAuthService;
	
	@GetMapping("/kakao")
	public ResponseEntity<PostUserOAuthRes> kakaoSignUp(@RequestParam String code) throws Exception {
		String accessToken = oAuthService.getKakaoAccessToken(code);
		PostUserOAuthRes postUserOAuthRes = oAuthService.createKakaoUser(accessToken);
		return new ResponseEntity<>(postUserOAuthRes, HttpStatus.OK);
	}
}
