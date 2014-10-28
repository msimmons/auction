package net.contrapt.auction.model;

import javax.persistence.Embeddable;

/**
 * Created by msimmons on 10/28/14.
 */
@Embeddable
public class ContactInfo {

    public ContactInfo() {
    }

    public ContactInfo(String email) {
        this.email = email;
    }

    public String email;

    public String address1;

    public String address2;

    public String city;

    public String state;

    public String zip;

    public String phone;
}
