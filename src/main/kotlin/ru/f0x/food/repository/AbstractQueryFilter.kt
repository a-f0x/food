package ru.f0x.food.repository

import javax.persistence.EntityManager
import javax.persistence.ParameterMode
import javax.persistence.PersistenceContext
import javax.persistence.StoredProcedureQuery

abstract class AbstractQueryFilter(
        @PersistenceContext
        private val entityManager: EntityManager
) {

    fun <T> getResultsByQuery(clazz: Class<T>, limit: Int?, query: () -> String): List<T> {
        val queryString = if (limit != null) "${query()} LIMIT $limit" else query()
        return entityManager.createNativeQuery(queryString, clazz).resultList as List<T>
    }

    fun <T> getResultsByFunctions(
            procedureName: String,
            inParams: Map<String, Any>,
            limit: Int?,
            resultTransformer: (raw: Any) -> T): List<T> {

        val p: StoredProcedureQuery = entityManager.createStoredProcedureQuery(procedureName)
        inParams.forEach { (paramName, value) ->
            p.registerStoredProcedureParameter(paramName, value.javaClass, ParameterMode.IN)
            p.setParameter(paramName, value)
        }
        limit?.let {
            p.setMaxResults(it)
        }
        p.execute()
        return p.resultList.filterNotNull()
                .map {
                    resultTransformer(it)
                }
    }
}