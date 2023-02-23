package co.incubyte;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class EmiService {

    @Inject
    @Client("https://mocki.io/v1/")
    HttpClient httpClient;
    private EmiClient emiClient;

    public EmiService(EmiClient emiClient) {
        this.emiClient = emiClient;
    }

    public Emi calculate(int amount, double interestRate, int durationInYear, int monthlyIncome) {

        Emi fetchedEmi = emiClient.fetch(amount, interestRate, durationInYear);
        fetchedEmi.setLoanApproval(fetchedEmi.getMonthlyPayment() * 2 < monthlyIncome);
        return fetchedEmi;
    }
}
