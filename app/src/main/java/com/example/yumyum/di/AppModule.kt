package com.example.yumyum.di


import android.app.Application
import android.content.Context
import com.example.yumyum.common.Constants.BASE_URL
import com.example.yumyum.common.Constants.SERVER_CLIENT
import com.example.yumyum.common.Constants.SIGN_IN_REQUEST
import com.example.yumyum.common.Constants.SIGN_UP_REQUEST
import com.example.yumyum.data.remote.MealApiService
import com.example.yumyum.data.repository.AuthRepositoryImpl
import com.example.yumyum.data.repository.MealRepositoryImpl
import com.example.yumyum.data.repository.ProfileRepositoryImpl
import com.example.yumyum.domain.repository.AuthRepository
import com.example.yumyum.domain.repository.MealRepository
import com.example.yumyum.domain.repository.ProfileRepository
import com.example.yumyum.domain.use_case.ApiUseCases
import com.example.yumyum.domain.use_case.GetCategoriesUseCase
import com.example.yumyum.domain.use_case.GetMealUseCase
import com.example.yumyum.domain.use_case.GetMealsUseCase
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // FIREBASE
    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideOneTapClient(
        @ApplicationContext
        context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Singleton
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(SERVER_CLIENT)
                .setFilterByAuthorizedAccounts(true)
                .build())
        .setAutoSelectEnabled(true)
        .build()

    @Provides
    @Singleton
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(SERVER_CLIENT)
                .setFilterByAuthorizedAccounts(false)
                .build())
        .build()

    @Provides
    @Singleton
    fun provideGoogleSignInOptions() =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(SERVER_CLIENT)
            .requestEmail()
            .build()

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ) = GoogleSignIn.getClient(app, options)

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest,
        db: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest,
        db = db
    )

    @Provides
    @Singleton
    fun provideProfileRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        signInClient: GoogleSignInClient,
        db: FirebaseFirestore
    ): ProfileRepository = ProfileRepositoryImpl(
        auth = auth,
        oneTapClient = oneTapClient,
        signInClient = signInClient,
        db = db
    )


    // API
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