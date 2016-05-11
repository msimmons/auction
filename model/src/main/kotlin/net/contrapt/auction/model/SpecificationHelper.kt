package net.contrapt.auction.model

import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.domain.Specifications
import javax.persistence.criteria.JoinType

/**
 * General purpose specification helper -- most useful for specifying whether to load collections via join as part of
 * a query
 */
class SpecificationHelper<T> {

    /**
     * Specify collections to eagerly load via left outer join
     * @param collectionNames
     * *
     * @return
     */
    fun joinCollections(vararg collectionNames: String): Specification<T> {
        return Specification { root, query, cb ->
            for (collectionName in collectionNames) {
                root.fetch<Any, Any>(collectionName, JoinType.LEFT)
            }
            null
        }
    }

    protected fun findBy(attributeName: String, attributeValue: Any): Specification<T> {
        return Specification { root, query, cb ->
            query.where(cb.equal(root.get<Any>(attributeName), attributeValue))
            null
        }
    }

    /**
     * Find entities where the given attribute name matches the given attribute value.  Optionally specify collections
     * to eagerly load via left outer join
     * @param attributeName
     * *
     * @param attributeValue
     * *
     * @param collectionNames
     * *
     * @return
     */
    fun findBy(attributeName: String, attributeValue: Any, vararg collectionNames: String): Specification<T> {
        return Specifications.where(findBy(attributeName, attributeValue)).and(joinCollections(*collectionNames))
    }

    /**
     * Find entities that match the given specification. Optionally specify collections to eagerly load via left outer join
     * @param specification
     * *
     * @param collectionNames
     * *
     * @return
     */
    fun findBy(specification: Specification<T>, vararg collectionNames: String): Specification<T> {
        return Specifications.where(specification).and(joinCollections(*collectionNames))
    }
}
