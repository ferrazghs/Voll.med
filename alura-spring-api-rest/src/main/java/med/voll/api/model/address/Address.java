package med.voll.api.model.address;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotBlank
    private String address;

    @NotBlank
    private String district;

    @NotBlank
    private String city;

    @NotBlank
    private String uf;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;

    private String complement;
    private Integer number;


    public Address(Address address) {
        this.address = address.address;
        this.number = address.number;
        this.complement = address.complement;
        this.district = address.district;
        this.city = address.city;
        this.uf = address.uf;
        this.cep = address.cep;
    }

    public void updateRegisterAddress(Address addressUpdate) {

        if (addressUpdate.getAddress() != null) {
            this.address = addressUpdate.getAddress();
        }

        if (addressUpdate.getNumber() != null) {
            this.number = addressUpdate.getNumber();
        }

        if (addressUpdate.getComplement() != null) {
            this.complement = addressUpdate.getComplement();
        }

        if (addressUpdate.getDistrict() != null) {
            this.district = addressUpdate.getDistrict();
        }

        if (addressUpdate.getCity() != null) {
            this.city = addressUpdate.getCity();
        }

        if (addressUpdate.getUf() != null) {
            this.uf = addressUpdate.getAddress();
        }

        if (addressUpdate.getCep() != null) {
            this.cep = addressUpdate.getCep();
        }

    }
}
