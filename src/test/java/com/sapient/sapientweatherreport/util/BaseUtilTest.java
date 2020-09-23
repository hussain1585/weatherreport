package com.sapient.sapientweatherreport.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class BaseUtilTest
{

    @Test
    void convertKelvinToCelsius()
    {
        Double input = new Double(250);
        Double actual = BaseUtil.convertKelvinToCelsius(input);
        Double expected = new Double(-24.0);
        assertTrue(actual.equals(expected));
    }
}