package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.svc.base.BaseTester;

import redis.clients.jedis.JedisCluster;

public class RedisTester extends BaseTester {

	@Autowired
	JedisCluster jedisCluster;

	@Test
	public void test() {
		System.out.println( "jedisCluster=" + jedisCluster );
		System.out.println( jedisCluster.get("ab12") );
		jedisCluster.set( "ab12", "12" );
		System.out.println( jedisCluster.get("ab12") );
	}

}
