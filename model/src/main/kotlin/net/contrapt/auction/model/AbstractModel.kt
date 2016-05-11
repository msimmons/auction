package net.contrapt.auction.model

import org.apache.commons.lang.builder.ReflectionToStringBuilder
import org.apache.commons.lang.builder.ToStringStyle

/**
 * Created by msimmons on 5/6/16.
 */
abstract class AbstractModel {

    override fun toString(): String {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE)
    }

}