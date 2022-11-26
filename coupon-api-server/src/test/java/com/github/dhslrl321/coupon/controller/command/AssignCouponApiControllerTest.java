package com.github.dhslrl321.coupon.controller.command;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dhslrl321.coupon.model.AssignCouponRequest;
import com.github.dhslrl321.coupon.model.data.AssignCouponRequestData;
import com.github.dhslrl321.coupon.model.data.CouponAssignedResultData;
import com.github.dhslrl321.coupon.service.command.AssignCouponService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AssignCouponApiController.class)
class AssignCouponApiControllerTest {

    private static final String COUPON_CODE = "A";
    private static final long USER_ID = 1L;
    private static final LocalDateTime ANY_DATE_TIME = LocalDateTime.now();

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AssignCouponService service;

    @Test
    void 쿠폰을_발급받을_수_있다() throws Exception {
        given(service.assign(AssignCouponRequestData.of(USER_ID, COUPON_CODE)))
                .willReturn(CouponAssignedResultData.of(100L, COUPON_CODE, ANY_DATE_TIME));

        String json = mapper.writeValueAsString(new AssignCouponRequest(COUPON_CODE));

        mvc.perform(post("/api/users/{userId}/coupons", USER_ID)
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }
}