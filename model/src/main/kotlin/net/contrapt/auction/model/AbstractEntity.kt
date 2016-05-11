package net.contrapt.auction.model

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

/**
 * Created by msimmons on 10/9/14.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity : AbstractModel() {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Version
    val version: Int = 0

    @CreatedBy
    @Column(name = "created_by")
    lateinit var createdBy: String
        private set

    @CreatedDate
    @Column(name = "created_at")
    lateinit var createdAt: Date
        private set

    @LastModifiedBy
    @Column(name = "updated_by")
    lateinit var updatedBy: String
        private set

    @LastModifiedDate
    @Column(name = "updated_at")
    lateinit var updatedAt: Date
        private set

    /**
     * Each entity should define its unique business key so that this abstract class can implement hash code and
     * equals based on that
     * @return A unique key string for the entity
     */
    abstract fun uniqueKey(): String

    override fun hashCode(): Int {
        return HashCodeBuilder().append(uniqueKey()).toHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if ( other !is AbstractEntity ) return false
        if ( other.javaClass != javaClass ) return false
        return EqualsBuilder().append(uniqueKey(), other.uniqueKey()).isEquals
    }

}
