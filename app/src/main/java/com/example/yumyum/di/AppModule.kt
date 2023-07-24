package com.example.yumyum.di

import com.example.yumyum.common.Constants.BASE_URL
import com.example.yumyum.data.remote.MealApiService
import com.example.yumyum.data.repository.MealRepositoryImpl
import com.example.yumyum.domain.repository.MealRepository
import com.example.yumyum.domain.use_case.ApiUseCases
import com.example.yumyum.domain.use_case.GetCategoriesUseCase
import com.example.yumyum.domain.use_case.GetMealUseCase
import com.example.yumyum.domain.use_case.GetMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesMealService(retrofit: Retrofit): MealApiService {
        return retrofit.create(MealApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMealRepository(api: MealApiService): MealRepository {
        return MealRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideApiUseCases(repository: MealRepository): ApiUseCases {
        return ApiUseCases(
            getCategoriesUseCase = GetCategoriesUseCase(repository),
            getMealsUseCase = GetMealsUseCase(repository),
            getMealUseCase = GetMealUseCase(repository)
        )
    }

}