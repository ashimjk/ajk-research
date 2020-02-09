package io.ashimjk.swaggerui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwaggerUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerUiApplication.class, args);
    }

    //@Configuration
    //@EnableSwagger2
    //public class SpringFoxConfig {
    //    @Bean
    //    public Docket api() {
    //        return new Docket(DocumentationType.SWAGGER_2)
    //                .select()
    //                .apis(RequestHandlerSelectors.any())
    //                .paths(PathSelectors.any())
    //                .build();
    //    }
    //
    //}

    //@Configuration
    //@EnableWebMvc
    //public static class WebConfig implements WebMvcConfigurer {
    //    @Override
    //    public void addCorsMappings(CorsRegistry registry) {
    //        registry.addMapping("/**");
    //    }
    //}

}
