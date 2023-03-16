package com.example.yapeapp.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
//@Module
class ApplicationModule(private val context: Context){
    /*@Provides
    @Singleton
    fun provideGetProductUseCase(getProductUseCase: GetProductUseCaseImpl): GetProductUseCase = getProductUseCase

    @Provides
    @Singleton
    fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository = productRepository

    @Provides
    @Singleton
    fun provideProductRemoteSource(productRemoteSource: ProductRemoteSourceImpl): ProductRemoteSource = productRemoteSource

    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(getAllProductsUseCase: GetAllProductsUseCaseImpl): GetAllProductsUseCase = getAllProductsUseCase

    @Provides
    @Singleton
    fun provideApiServiceProduct(): ApiServiceProduct{
        return ApiClient.Builder<ApiServiceProduct>()
            .setBaseUrl(BASE_URL)
            .setApiService(ApiServiceProduct::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): Database{
        return Room
            .databaseBuilder(context, Database::class.java, "GlobalLogicDB.db3")
            .build()
    }

    @Provides
    @Singleton
    fun provideProductLocalSource(database: Database): ProductLocalSource{
        return ProductLocalSourceImpl(database.productDao())
    }

    @Provides
    @Singleton
    fun provideContext(): Context = context*/
}