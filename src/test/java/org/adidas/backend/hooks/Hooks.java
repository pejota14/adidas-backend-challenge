package org.adidas.backend.hooks;

import io.cucumber.java.After;
import org.adidas.backend.supportFunctions.CommonsModule;

public class Hooks {

    @After
    public void tearDown() {
        CommonsModule.clear();
    }


}
