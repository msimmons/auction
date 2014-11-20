package net.contrapt.auction.model;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.*;
import java.io.Serializable;

/**
 * General purpose specification helper -- most useful for specifying whether to load collections via join as part of
 * a query
 */
public class SpecificationHelper<T> {

    /**
     * Specify collections to eagerly load via left outer join
     * @param collectionNames
     * @return
     */
    public Specification<T> joinCollections(final String... collectionNames) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                for ( String collectionName : collectionNames ) {
                    root.fetch(collectionName, JoinType.LEFT);
                }
                return null;
            }
        };
    }

    protected Specification<T> findBy(final String attributeName, final Object attributeValue) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.where(cb.equal(root.get(attributeName), attributeValue));
                return null;
            }
        };
    }

    /**
     * Find entities where the given attribute name matches the given attribute value.  Optionally specify collections
     * to eagerly load via left outer join
     * @param attributeName
     * @param attributeValue
     * @param collectionNames
     * @return
     */
    public Specification<T> findBy(final String attributeName, final Object attributeValue, final String... collectionNames) {
        return Specifications.where(findBy(attributeName, attributeValue)).and(joinCollections(collectionNames));
    }

    /**
     * Find entities that match the given specification. Optionally specify collections to eagerly load via left outer join
     * @param specification
     * @param collectionNames
     * @return
     */
    public Specification<T> findBy(final Specification<T> specification, final String... collectionNames) {
        return Specifications.where(specification).and(joinCollections(collectionNames));
    }
}
