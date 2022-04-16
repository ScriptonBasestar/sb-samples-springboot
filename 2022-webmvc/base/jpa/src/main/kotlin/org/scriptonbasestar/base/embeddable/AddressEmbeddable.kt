package org.scriptonbasestar.base.embeddable

import javax.persistence.Embeddable

@Embeddable
data class AddressEmbeddable(
    val postcode: String,
    val address1: String,
    val address2: String,
)
