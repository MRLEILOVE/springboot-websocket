package com.wallet.chain.config;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NetWorkConfig {

    @Primary
    @Bean
    @ConditionalOnProperty(name = {"btc.network.main"}, havingValue = "true")
    NetworkParameters networkParameters() {
        return MainNetParams.get();
    }

    @Bean
    NetworkParameters testNet3Params() {
        return TestNet3Params.get();
    }

}
