package com.coin.wallet;

import com.coin.wallet.service.ICreateAddressFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletApplicationTests {

    @Autowired
    ICreateAddressFactory createAddressService;
    @Test
    public void contextLoads() {
       // createAddressService.execute(null);
    }

}
