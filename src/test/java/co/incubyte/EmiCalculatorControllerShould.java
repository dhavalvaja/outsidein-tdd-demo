package co.incubyte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmiCalculatorControllerShould {

    EmiCalculatorController emiCalculator;
    @Mock
    EmiService emiService;

    @BeforeEach
    public void init() {
        emiCalculator = new EmiCalculatorController(emiService);
    }

    @Test
    @DisplayName("calculate emi")
    void calculate_emi() {
        //given
        int amount = 200000;
        double interestRate = 3.5;
        int durationInYear = 30;
        int monthlyIncome = 3000;
        //when
        Emi calculated = emiCalculator.calculate(amount, interestRate, durationInYear, monthlyIncome);

        //then
        verify(emiService).calculate(amount, interestRate, durationInYear, monthlyIncome);
    }
}
