package com.yefan.study.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
//        String url = "http://localhost:8079/api/server/hello";
        String url = "http://hello-service/hello";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("++++++++++++++++++++++++++++" + result);
    }

}
