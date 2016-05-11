package net.contrapt.auction.model

import javax.persistence.Embeddable

/**
 * Created by msimmons on 10/28/14.
 */
@Embeddable
class ContactInfo : AbstractModel {

    var email: String? = null

    var address1: String? = null

    var address2: String? = null

    var city: String? = null

    var state: String? = null

    var zip: String? = null

    var phone: String? = null

    constructor() {}

    constructor(email: String) {
        this.email = email
    }

}
