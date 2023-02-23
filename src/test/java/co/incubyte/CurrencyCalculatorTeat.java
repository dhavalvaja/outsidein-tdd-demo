package co.incubyte;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonObject;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
public class CurrencyCalculatorTeat {
    @Client("/")
    @Inject
    HttpClient httpClient;

    @Test
    @DisplayName("emi should be calculated")
    void emi_should_be_calculated() {
        //given
        int loanAmount = 200000;
        double interestRate = 3.5;
        int durationInYears = 30;
        int monthlyIncome = 3000;
        //when
        Emi emi = httpClient.toBlocking().retrieve("mortgagecalculator" +
                "?loan_amount=" + loanAmount +
                "&interest_rate=" + interestRate +
                "&duration_years=" + durationInYears +
                "&monthly_income=" + monthlyIncome, Emi.class);
        int interestPaid = emi.getInterestPaid();
        int monthlyPayment = emi.getMonthlyPayment();
        //then
        Assertions.assertThat(interestPaid).isEqualTo(123312);
        Assertions.assertThat(monthlyPayment).isEqualTo(898);
    }

    @Test
    @DisplayName("should approved if emi is less than half of monthly income")
    void should_approved_if_emi_is_less_than_half_of_monthly_income() {
        //given
        int loanAmount = 200000;
        double interestRate = 3.5;
        int durationInYears = 30;
        int monthlyIncome = 3000;
        //when
        Emi emi = httpClient.toBlocking().retrieve("mortgagecalculator" +
                "?loan_amount=" + loanAmount +
                "&interest_rate=" + interestRate +
                "&duration_years=" + durationInYears +
                "&monthly_income=" + monthlyIncome, Emi.class);
        boolean approval = emi.getLoanApproval();
        //then
        Assertions.assertThat(approval).isTrue();
    }
}
