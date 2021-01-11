package com.heftyb.supersupper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GoogleauthApplication
{
    private static boolean run = true;

    private static void checkEnviromentVariable(String envvar)
    {
        if (System.getenv(envvar) == null)
        {
            run = false;
        }
    }

    public static void main(String[] args)
    {
        checkEnviromentVariable("GOOGID");
        checkEnviromentVariable("GOOGSEC");

        if (run)
        {
            SpringApplication.run(GoogleauthApplication.class,
                args);
        } else
        {
            System.out.println("Environment Variables NOT SET: GOOGID and / or GOOGSEC");
        }
    }

}
