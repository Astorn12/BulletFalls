package com.example.user.bulletfalls;

import com.example.user.bulletfalls.Shop.PossesStrategyPackage.TimeStrategiesPackage.DayOfWeekPossesStrategy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void week_TimePossesStrategy()
    {
        DayOfWeekPossesStrategy strategy= new DayOfWeekPossesStrategy(4);

        assertEquals(1,strategy.howSoon(),0.001);//zajebisteeeeeee1!!!!!!!!!!!!!!!!!!!!!
    }
}