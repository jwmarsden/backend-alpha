package co.cata.integration

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class GreetingControllerIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun retrieveGreeting() {

        val name = "RaH"

        val result = webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals("$name, Hello from test profile", result.responseBody)
    }
}