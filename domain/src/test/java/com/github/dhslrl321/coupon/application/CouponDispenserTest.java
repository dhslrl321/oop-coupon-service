package com.github.dhslrl321.coupon.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.github.dhslrl321.coupon.command.AssignCouponCommand;
import com.github.dhslrl321.coupon.value.Amount;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponName;
import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.repository.CouponStockRepository;
import com.github.dhslrl321.coupon.value.Quantity;
import com.github.dhslrl321.coupon.value.UserId;
import com.github.dhslrl321.coupon.exception.InvalidCommandException;
import com.github.dhslrl321.coupon.result.CouponAssignedResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CouponDispenserTest {

    public static final CouponCode COUPON_CODE = CouponCode.of("A");
    public static final UserId USER_ID = UserId.of(1L);

    @InjectMocks
    CouponDispenser sut;

    @Mock
    CouponStockRepository repository;

    @Mock
    WalletManager walletManager;

    @Test
    void 협력한다() {
        setUpWith();
        AssignCouponCommand command = AssignCouponCommand.of(USER_ID, COUPON_CODE);
        sut.assign(command);

        verify(repository).findByCode(any());
        verify(walletManager).add(any(), any());
    }

    @Test
    void command_에_의해서_쿠폰이_생성된다() {
        setUpWith();
        AssignCouponCommand command = AssignCouponCommand.of(USER_ID, COUPON_CODE);

        CouponAssignedResult actual = sut.assign(command);

        assertThat(actual.getCouponId()).isNotNull();
        assertThat(actual.getCouponCode()).isEqualTo(COUPON_CODE);
        assertThat(actual.getAssignedAt()).isNotNull();
    }

    @Test
    void sut_는_사전_조건을_만족해야한다_1() {
        AssignCouponCommand command = AssignCouponCommand.of(null, COUPON_CODE);

        assertThatThrownBy(() -> sut.assign(command)).isInstanceOf(InvalidCommandException.class);
    }

    @Test
    void sut_는_사전_조건을_만족해야한다_2() {
        AssignCouponCommand command = AssignCouponCommand.of(USER_ID, null);

        assertThatThrownBy(() -> sut.assign(command)).isInstanceOf(InvalidCommandException.class);
    }

    private void setUpWith() {
        CouponStock stock = CouponStock.newInstance(Quantity.of(100L), COUPON_CODE, Amount.of(50000L),
                CouponName.of("hello"));

        given(repository.findByCode(COUPON_CODE)).willReturn(stock);
    }
}