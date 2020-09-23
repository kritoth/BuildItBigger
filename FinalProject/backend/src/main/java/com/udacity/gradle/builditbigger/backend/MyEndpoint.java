package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.tiansirk.joketeller.JokeTeller;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        JokeTeller jokeTeller = new JokeTeller();
        String joke = jokeTeller.getJoke();

        MyBean response = new MyBean();
        response.setData("Hi, " + name + "\n" + joke);

        return response;
    }

    /** A simple endpoint method that tells a joke */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        JokeTeller jokeTeller = new JokeTeller();
        String joke = jokeTeller.getJoke();

        MyBean response = new MyBean();
        response.setData(joke);

        return response;
    }
}
