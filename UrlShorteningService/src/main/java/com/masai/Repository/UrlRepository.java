package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{

	public Url findByShortLink(String shortLink);
}
