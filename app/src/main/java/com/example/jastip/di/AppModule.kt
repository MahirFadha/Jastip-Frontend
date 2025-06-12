package com.example.jastip.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jastip.data.local.AppDatabase
import com.example.jastip.data.local.TokenManager
import com.example.jastip.data.local.dao.KeranjangDao
import com.example.jastip.data.local.dao.MenuItemDao
import com.example.jastip.data.local.dao.PesananDao
import com.example.jastip.data.local.dao.UserDao
import com.example.jastip.data.repository.AuthRepositoryImpl
import com.example.jastip.data.repository.KeranjangRepositoryImpl
import com.example.jastip.data.repository.MenuRepositoryImpl
import com.example.jastip.data.repository.PesananRepositoryImpl
import com.example.jastip.data.repository.UserRepositoryImpl
import com.example.jastip.domain.repository.IAuithRepository
import com.example.jastip.domain.repository.IKeranjangRepository
import com.example.jastip.domain.repository.IMenuRepository
import com.example.jastip.domain.repository.IPesananRepository
import com.example.jastip.domain.repository.UserRepository
import com.example.jastip.domain.usecase.EditUseCase
import com.example.jastip.domain.usecase.KeranjangUseCase
import com.example.jastip.domain.usecase.LoginUseCase
import com.example.jastip.domain.usecase.PesananUseCase
import com.example.jastip.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Tambah kolom 'type' dengan default supaya kolom baru tidak NULL
        database.execSQL("ALTER TABLE menu ADD COLUMN type TEXT NOT NULL DEFAULT 'food'")
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provide AppDatabase
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .addMigrations(MIGRATION_3_4)
            .fallbackToDestructiveMigration()
            .build()

    // Provide DAO: User
    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    // Provide DAO: Menu
    @Provides
    @Singleton
    fun provideMenuItemDao(db: AppDatabase): MenuItemDao = db.MenuItemDao()

    @Provides
    @Singleton
    fun providePesananDao(db: AppDatabase): PesananDao = db.PesananDao()


    // Provide Repository: User
    @Provides
    @Singleton
    fun provideUserRepository(dao: UserDao): UserRepository = UserRepositoryImpl(dao)

    // Provide Repository: Menu
    @Provides
    @Singleton
    fun provideMenuRepository(
        dao: MenuItemDao,
        @ApplicationContext context: Context
    ): IMenuRepository = MenuRepositoryImpl(dao, context)

    @Provides
    @Singleton
    fun providePesananRepository(
        dao: PesananDao
    ): IPesananRepository = PesananRepositoryImpl(dao)

    // UseCase: Register
    @Provides
    fun provideRegisterUseCase(repo: UserRepository) = RegisterUseCase(repo)

    // UseCase: Login
    @Provides
    fun provideLoginUseCase(repo: IAuithRepository) = LoginUseCase(repo)

    // UseCase: Edit Profile
    @Provides
    fun provideEditUseCase(repo: UserRepository) = EditUseCase(repo)

    // Provide DAO: Keranjang
    @Provides
    @Singleton
    fun provideKeranjangDao(db: AppDatabase): KeranjangDao = db.KeranjangDao()

    // Provide Repository: Keranjang
    @Provides
    @Singleton
    fun provideKeranjangRepository(
        dao: KeranjangDao
    ): IKeranjangRepository = KeranjangRepositoryImpl(dao)

    // Provide UseCase: Keranjang
    @Provides
    fun provideKeranjangUseCase(
        repo: IKeranjangRepository
    ): KeranjangUseCase = KeranjangUseCase(repo)

    @Provides
    fun providePesananUseCase(
        repo: IPesananRepository
    ): PesananUseCase = PesananUseCase(repo)

    @Provides
    fun provideIAuthRepository(
        dao: UserDao,
        tokenManager: TokenManager
    ): IAuithRepository = AuthRepositoryImpl(dao, tokenManager)
}
