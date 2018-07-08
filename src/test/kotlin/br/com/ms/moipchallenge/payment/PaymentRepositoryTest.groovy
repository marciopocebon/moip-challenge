package br.com.ms.moipchallenge.payment

import br.com.ms.moipchallenge.buyer.Buyer
import br.com.ms.moipchallenge.client.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class PaymentRepositoryTest extends Specification {

    @Autowired
    PaymentRepository repository

    def "valid paymentId should return a payment"(){
        given: "an valid payment persisted"
        Client client = new Client("Client")
        Buyer buyer = new Buyer("Buyer", "buyer@gmail.com", "99759587025")
        Payment payment = repository.save(new BoletoPayment(client, BigDecimal.TEN, buyer))

        when: "passing payment.id as argument"
        Payment expected = repository.findBy(payment.id)

        then: "return the persisted payment"
        expected != null
        expected.id == payment.id
    }
}
