package io.ashimjk.keycloak.resolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rits.cloning.Cloner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.representations.adapters.config.AdapterConfig;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@Slf4j
public class PathBasedKeycloakConfigResolver {

    public KeycloakDeployment resolve(HttpFacade.Request request) {

        Cloner cloner = new Cloner();

        AdapterConfig adapterConfig = buildAdapterConfig();
        KeycloakDeployment deployment = KeycloakDeploymentBuilder.build(cloner.deepClone(adapterConfig));

        return deployment;
    }

    private AdapterConfig buildAdapterConfig() {
        AdapterConfig adapterConfig = new AdapterConfig();
        adapterConfig.setRealm("demo");
        adapterConfig.setAuthServerUrl("http://localhost:8180/auth");
        adapterConfig.setResource("api-based");

        PolicyEnforcerConfig policyEnforcerConfig = readPolicyConfig("keycloak.json");
        adapterConfig.setPolicyEnforcerConfig(policyEnforcerConfig);

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("secret", "a167cfcc-07fa-48be-905a-9e5087bf7ac3");
        adapterConfig.setCredentials(credentials);

        return adapterConfig;
    }

    private PolicyEnforcerConfig readPolicyConfig(String filePath) {
        String SANITIZER_REGEX = "[\r\n]";
        try {
            String policyConfig = readFile(filePath).replaceAll(SANITIZER_REGEX, EMPTY);
            return buildObjectMapper().readValue(policyConfig, PolicyEnforcerConfig.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("cannot read file from path: " + filePath);
        }
    }

    private String readFile(String filePath) throws IOException {
        LOGGER.debug("Loading policy config from: {}", filePath);
        InputStream is = PathBasedKeycloakConfigResolver.class.getClassLoader().getResourceAsStream(filePath);
        if (is == null) {
            throw new IllegalArgumentException("cannot read file from path: " + filePath);
        }
        return IOUtils.toString(is, "UTF-8");
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

}
