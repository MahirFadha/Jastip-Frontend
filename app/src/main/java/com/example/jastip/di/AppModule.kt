package com.example.jastip.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jastip.data.local.AppDatabase
import com.example.jastip.data.local.dao.MenuItemDao
import com.example.jastip.data.local.dao.UserDao
import com.example.jastip.data.repository.MenuRepositoryImpl
import com.example.jastip.data.repository.UserRepositoryImpl
import com.example.jastip.domain.repository.IMenuRepository
import com.example.jastip.domain.repository.UserRepository
import com.example.jastip.domain.usecase.EditUseCase
import com.example.jastip.domain.usecase.LoginUseCase
import com.example.jastip.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

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
    fun provideMenuItemDao(db: AppDatabase): MenuItemDao = db.menuItemDao()

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

    // UseCase: Register
    @Provides
    fun provideRegisterUseCase(repo: UserRepository) = RegisterUseCase(repo)

    // UseCase: Login
    @Provides
    fun provideLoginUseCase(repo: UserRepository) = LoginUseCase(repo)

    // UseCase: Edit Profile
    @Provides
    fun provideEditUseCase(repo: UserRepository) = EditUseCase(repo)
}
