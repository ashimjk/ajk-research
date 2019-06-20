package io.ashimjk.cucumber.glue;

import io.ashimjk.cucumber.CucumberConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CucumberConfig.class)
//@ComponentScan(basePackages = "io.ashimjk.cucumber")
class Config {

}
