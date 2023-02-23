package co.incubyte;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("https://mocki.io/v1/")
public interface EmiClient {

    @Get("6dabfc76-68bd-4371-bde6-589b49fce220")
    Emi fetch(int loanAmount, double interestRate, int durationInYears);
}
