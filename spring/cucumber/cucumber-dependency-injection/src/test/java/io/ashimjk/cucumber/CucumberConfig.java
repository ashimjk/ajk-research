package io.ashimjk.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
        glue = {"io.ashimjk.cucumber.glue"})
public class CucumberConfig {

}
