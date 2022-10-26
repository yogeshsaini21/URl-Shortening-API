package com.masai.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;
import com.masai.Repository.UrlRepository;
import com.masai.model.Url;
import com.masai.model.UrlDto;

@Component
public class UrlServiceImpl implements UrlService {

	@Autowired
	private UrlRepository urlRepository;
	
	@Override
	public Url generateShortLink(UrlDto urldto) {
		
		if(StringUtils.isNotEmpty(urldto.getUrl())) {
			String encodedUrl = encodeUrl(urldto.getUrl());
			
			Url urlToPersist = new Url();
			urlToPersist.setCreationDate(LocalDateTime.now());
			urlToPersist.setOriginalUrl(urldto.getUrl());
			urlToPersist.setShortLink(encodedUrl);
			urlToPersist.setExpirationDate(getExpirationDate(urldto.getExpirationDate() , urlToPersist.getCreationDate()));
			Url urlToRet = persistShortLink(urlToPersist);
			
			if(urlToRet != null) {
				return urlToRet;
			}
			return null;
			
		}
		
		return null;
	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {

		if(StringUtils.isBlank(expirationDate)) {
			return creationDate.plusWeeks(1);
		}
		
		LocalDateTime expireationDateToRet = LocalDateTime.parse(expirationDate);
		return expireationDateToRet;
		

	}

	private String encodeUrl(String url) {

		String encodedUrl ="";
		LocalDateTime time  = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32()
				.hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
				.toString();
		
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url url) {

		Url urlToRet = urlRepository.save(url);
		return urlToRet;
		 
	}

	@Override
	public Url getEncodedUrl(String url) {

		Url urlToRet = urlRepository.findByShortLink(url);
		return urlToRet;
	}

	@Override
	public void deleteShortLink(Url url) {
		
		urlRepository.delete(url);
		
	}

	
	
}
