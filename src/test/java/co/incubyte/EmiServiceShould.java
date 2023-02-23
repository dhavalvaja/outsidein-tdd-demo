package co.incubyte;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EmiServiceShould {
    EmiClient emiClient = Mockito.mock(EmiClient.class);
    EmiService emiService = new EmiService(emiClient);

    @Test
    @DisplayName("call EmiClient")
    void call_emi_client() {
        //given
        int loanAmount = 200000;
        float interestRate = 3.5F;
        int durationInYears = 30;
        int monthlyIncome = 3000;
        Emi emi = new Emi(123312, 898);
        Mockito.when(emiClient.fetch(loanAmount, interestRate, durationInYears)).thenReturn(emi);
        //when
        emiService.calculate(loanAmount, interestRate, durationInYears, monthlyIncome);
        //then
        Mockito.verify(emiClient).fetch(loanAmount, interestRate, durationInYears);
    }

    @Test
    @DisplayName("approve loan if emi is less then half of monthly income")
    void approve_loan_if_emi_is_less_then_half_of_monthly_income() {
        //given
        int loanAmount = 200000;
        float interestRate = 3.5F;
        int durationInYears = 30;
        int monthlyIncome = 3000;
        Emi emi = new Emi(123312, 898);
        Mockito.when(emiClient.fetch(loanAmount, interestRate, durationInYears)).thenReturn(emi);
        //when
        Emi calculatedEmi = emiService.calculate(loanAmount, interestRate, durationInYears, monthlyIncome);

        //then
        Assertions.assertThat(calculatedEmi.getLoanApproval()).isTrue();
    }
}