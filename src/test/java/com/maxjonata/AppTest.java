package com.maxjonata;

import com.maxjonata.adapters.CreateChromeDriver;
import com.maxjonata.adapters.MakeAdapter;
import com.maxjonata.controllers.LoggerDisabled;
import com.maxjonata.controllers.LoggerSout;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void shouldAnswerWithDefaultDisabledLogger()
    {
        assertTrue(new MakeAdapter().logger(null) instanceof LoggerDisabled);
    }
    
    @Test
    public void shouldAnswerWithSelectedLogger()
    {
        assertTrue(new MakeAdapter().logger(new LoggerSout()) instanceof LoggerSout);
    }

    @Test
    public void shouldAnswerWithANewChromeDriver()
    {
        assertTrue(CreateChromeDriver.make() instanceof ChromeDriver);
    }
}
