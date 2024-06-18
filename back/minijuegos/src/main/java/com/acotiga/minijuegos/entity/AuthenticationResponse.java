package com.acotiga.minijuegos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthenticationResponse {
	private String jwt;
	private String redirectUrl;
}
