package me.bodnarsg.jwtsample.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.bodnarsg.jwtsample.data.remote.AuthAPI
import me.bodnarsg.jwtsample.data.remote.AuthRemoteDataSource
import me.bodnarsg.jwtsample.data.remote.AuthInterceptor
import me.bodnarsg.jwtsample.data.remote.TokenAuthenticator
import me.bodnarsg.jwtsample.data.remote.ApiConstants
import me.bodnarsg.jwtsample.data.repository.AuthRepositoryImpl
import me.bodnarsg.jwtsample.domain.repository.AuthRepository
import me.bodnarsg.jwtsample.data.remote.UserAPI
import me.bodnarsg.jwtsample.data.remote.UserRemoteDataSource
import me.bodnarsg.jwtsample.data.repository.UserRepositoryImpl
import me.bodnarsg.jwtsample.domain.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthAPI =
        retrofit.create(AuthAPI::class.java)

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserAPI =
        retrofit.create(UserAPI::class.java)

    @Provides
    fun provideAuthRemoteDataSource(api: AuthAPI) = AuthRemoteDataSource(api)

    @Provides
    fun provideUserRemoteDataSource(api: UserAPI) = UserRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideAuthRepository(remoteDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl
}