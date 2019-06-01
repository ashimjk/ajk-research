package io.ashimjk.tomcat.auto.configuration;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnClass(Tomcat.class)
@ConditionalOnProperty(name = "log-tomcat-version", matchIfMissing = true)
public class LogTomcatVersionAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(LogTomcatVersionAutoConfiguration.class);

    @PostConstruct
    public void logTomcatVersion() {
        logger.info("\n\n\nTomcat v"
                + Tomcat.class.getPackage().getImplementationVersion() + "\n\n");
    }

}
