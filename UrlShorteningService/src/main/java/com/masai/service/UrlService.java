package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.model.Url;
import com.masai.model.UrlDto;

@Service
public interface UrlService {

	public Url generateShortLink(UrlDto urldto);
	public Url persistShortLink(Url url);
	public Url getEncodedUrl(String url);
	public void deleteShortLink(Url url);
	
}
