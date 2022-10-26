package com.masai.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UrlResponseDto {

	private String originalUrl;
	private String shortLink;
	private LocalDateTime expirationDate;
	
}
