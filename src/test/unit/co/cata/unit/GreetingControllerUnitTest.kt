package co.cata.unit

import co.cata.controller.GreetingController
import co.cata.service.GreetingsService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [GreetingController::class])
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class GreetingControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var greetingsServiceMock: GreetingsService

    @Test
    fun retrieveGreeting() {

        val name = "RaH"

        every { greetingsServiceMock.retrieveGreeting(any()) } returns  "$name, Hello from mock profile"

        val result = webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        assertEquals("$name, Hello from mock profile", result.responseBody)
    }

}