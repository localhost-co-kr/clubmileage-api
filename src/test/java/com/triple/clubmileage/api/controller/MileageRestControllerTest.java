package com.triple.clubmileage.api.controller;

import com.triple.clubmileage.api.service.MileageService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MileageRestController.class)
public class MileageRestControllerTest {

    @MockBean
    private MileageService mileageService;

    @Autowired
    private WebTestClient webClient;

    @Test
    void 마일리지조회시_유효한_요청이면_200_응답을_반환한다() {
        webClient.get()
            .uri(String.format("/%s/mileage", UUID.randomUUID()))
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void 마일리지조회시_유효하지않은_요청이면_400_응답을_반환한다() {
        webClient.get()
            .uri(String.format("/%s/mileage", "not uuid format"))
            .exchange()
            .expectStatus().isBadRequest();
    }
}
