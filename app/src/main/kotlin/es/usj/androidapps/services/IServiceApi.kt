package es.usj.androidapps.services

import java.util.*

interface IServiceApi<T> {
    fun list(limit: Int?, offset: Long?): List<T>
    fun find(id: UUID): T?
    fun delete(id: UUID): T
    fun save(element: T): T
}