package io.ashimjk.product.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/product")
public class ProductController {

  @Value("${app.service.detail}")
  private String serviceDetail;

  @GetMapping("service-detail")
  public String getServiceDetail() {
    return "service detail [" + this.serviceDetail + "]";
  }
}
