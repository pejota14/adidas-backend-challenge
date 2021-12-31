package org.adidas.backend.runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        features = {
                "src/test/resources/features"
        },
        tags = "@pet",
        glue = {
                "org.adidas.backend.stepDefinitions",
                "org.adidas.backend.hooks"
        }
)
public class TestRunner {
}
