package com.example.r411.persistance.dao

interface DataDao<T> {
    fun getAll(): List<T>
    fun loadAllByIds(ids: IntArray): List<T>
    fun insertAll(vararg data: T)
    fun insertOrUpdate(data: T)
}