package co.incubyte;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller
public class EmiCalculatorController {
    private EmiService emiService;

    public EmiCalculatorController(EmiService emiService) {
        this.emiService = emiService;
    }

    @Get("/mortgagecalculator")
    public Emi calculate(
            @QueryValue("loan_amount") int amount,
            @QueryValue("interest_rate") double interestRate,
            @QueryValue("duration_years") int durationInYear,
            @QueryValue("monthly_income") int monthlyIncome
    ) {
        return this.emiService.calculate(amount, interestRate, durationInYear, monthlyIncome);
    }
}
