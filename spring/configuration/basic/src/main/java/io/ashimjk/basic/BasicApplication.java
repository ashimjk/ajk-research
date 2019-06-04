package io.ashimjk.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties
public class BasicApplication {

  public static void main(String[] args) {
    SpringApplication.run(BasicApplication.class, args);
  }

  @Value("${configuration.projectName}")
  void setProjectName(String projectName) {
    makeSpace();

    System.out.println("setting project name: " + projectName);
  }

  void makeSpace() {
    System.out.println("\n\n\n");
  }

  @Autowired
  void setConfigurationProjectProperties(ConfigurationProjectProperties cp) {
    makeSpace();

    System.out.println("configurationProjectProperties.projectName = " + cp.getProjectName());
  }

  @Autowired
  void setEnvironment(Environment env) {
    makeSpace();

    System.out.println("setting environment: " + env.getProperty("configuration.projectName"));

    makeSpace();
  }
}
