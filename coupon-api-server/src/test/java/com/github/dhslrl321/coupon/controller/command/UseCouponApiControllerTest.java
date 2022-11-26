package com.github.dhslrl321.coupon.controller.command;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dhslrl321.coupon.model.data.ApplyCouponRequestData;
import com.github.dhslrl321.coupon.model.data.CouponAppliedResultData;
import com.github.dhslrl321.coupon.service.command.ApplyCouponService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UseCouponApiController.class)
class UseCouponApiControllerTest {
    private static final LocalDateTime ANY_DATE_TIME = LocalDateTime.now();
    private static final String COUPON_CODE = "A";
    private static final long COUPON_ID = 2L;
    private static final long USER_ID = 1L;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ApplyCouponService service;

    @Test
    void 쿠폰을_사용할_수_있다() throws Exception {
        given(service.apply(ApplyCouponRequestData.of(USER_ID, COUPON_ID)))
                .willReturn(CouponAppliedResultData.of(COUPON_ID, COUPON_CODE, ANY_DATE_TIME));

        mvc.perform(patch("/api/users/{userId}/coupons/{couponId}", USER_ID, COUPON_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }
}