package io.ashimjk.tddworkout;

import io.ashimjk.tddworkout.lgdraft.LgDraftResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnLgDraft() {
        // arrange

        // act
        ResponseEntity<LgDraftResource> resource = restTemplate.getForEntity("/api/v1/lg-drafts/123", LgDraftResource.class);

        // assert
        assertThat(resource.getBody()).isNotNull();
        assertThat(resource.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resource.getBody().getReference()).isEqualTo("123");
        assertThat(resource.getBody().getData()).isEqualTo("lgdraft");
    }

}
