package com.td.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.dao.Oauth2Dao;
import com.td.model.Oauth2Client;
import com.td.model.Oauth2User;

@Service
public class Oauth2Service {

	private final Oauth2Dao oauth2Dao;

	@Autowired
	public Oauth2Service(Oauth2Dao oauth2Dao) {
		this.oauth2Dao = oauth2Dao;
	}

	public List<Oauth2Client> getOauth2ClientByClientId(String clientId) {
		List<Oauth2Client> list = oauth2Dao.getOauth2ClientByClientId( clientId );
		return list;
	}

	public List<Oauth2User> getOauth2UserByUsername(String username) {
		List<Oauth2User> list = oauth2Dao.getOauth2UserByUsername( username );
		return list;
	}
}