package med.voll.api.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;
import med.voll.api.client.dto.RegisterClientDto;
import med.voll.api.client.dto.UpdateClientDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Client")
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String cpf;

    @Embedded
    private Address address;

    private Boolean status;

    public Client(RegisterClientDto client) {
        this.status = true;
        this.name = client.name();
        this.email = client.email();
        this.telephone = client.telephone();
        this.cpf = client.cpf();
        this.address = new Address(client.address());
    }

    public void updateRegisterClient(UpdateClientDto client) {

        if (client.name() != null) {
            this.name = client.name();
        }

        if (client.telephone() != null) {
            this.telephone = client.telephone();
        }

        if (client.address() != null) {
            this.address.updateRegisterAddress(client.address());
        }
    }

    public void inactivateClient() {
        this.status = false;
    }
}
