package com.gavoyage.config.aws.controller;

import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gavoyage.config.aws.service.AwsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws/s3")
public class AwsController {
	
	private final AwsService awsService;

	@PostMapping("/uploadImage")
	public ResponseEntity<URL> uploadFile(MultipartFile image){
	     return ResponseEntity.ok(awsService.uploadFile(image));
	}
}

