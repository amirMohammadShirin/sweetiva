package com.sweet.arch.core.domain.infra.cache

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
/**
 * The markup class streams data with T type
 * Please read the markup class implementation
 * To prevent unpredictable application behavior !
 * */
annotation class StreamsData<T>
