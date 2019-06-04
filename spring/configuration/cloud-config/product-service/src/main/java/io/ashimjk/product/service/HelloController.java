package io.ashimjk.product.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/")
public class HelloController {

  @Value("${app.service.name}")
  private String serviceName;

  @Value("${app.shared.attribute}")
  private String sharedAttribute;

  @GetMapping("/service")
  public String getServiceName() {
    return "service name [" + this.serviceName + "]";
  }

  @GetMapping("/shared")
  public String getSharedAttribute() {
    return " application.properties [" + this.sharedAttribute + "]";
  }
}
