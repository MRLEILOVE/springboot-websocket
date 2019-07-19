package com.td.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.td.model.Oauth2Client;
import com.td.model.Oauth2User;

@Repository
public class Oauth2Dao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public Oauth2Dao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Oauth2Client> getOauth2ClientByClientId(String clientId) {
		String sql = "select * from oauth2_client where clientId = ?";
		return jdbcTemplate.query( sql, new String[] { clientId }, new BeanPropertyRowMapper<>( Oauth2Client.class ) );
	}

	public List<Oauth2User> getOauth2UserByUsername(String username) {
		String sql = "select * from oauth2_user where username = ?";
		return jdbcTemplate.query( sql, new String[] { username }, new BeanPropertyRowMapper<>( Oauth2User.class ) );
	}

}
