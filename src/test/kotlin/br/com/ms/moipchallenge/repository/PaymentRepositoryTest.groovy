package br.com.ms.moipchallenge.repository

import br.com.ms.moipchallenge.models.BoletoPayment
import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.models.Payment
import br.com.ms.moipchallenge.repositories.PaymentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class PaymentRepositoryTest extends Specification {

    @Autowired
    PaymentRepository repository

    def "find() method with valid paymentId should return a payment"(){
        given: "an valid payment persisted"
        Client client = new Client("Mário")
        Buyer buyer = new Buyer("Mário", "mario@gmail.com", "99759587025")
        Payment payment = repository.save(new BoletoPayment(client, new BigDecimal(30), buyer))

        when: "execute method find() passing as argument persisted payment id"
        Payment expected = repository.find(payment.id)

        then: "return the persisted payment"
        expected != null
        expected.id == payment.id
    }
}
