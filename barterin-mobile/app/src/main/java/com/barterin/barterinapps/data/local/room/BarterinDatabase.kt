package com.barterin.barterinapps.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//import com.barterin.barterinapps.data.local.entity.AddressEntity
//
//@Database(entities = [AddressEntity::class], version = 1)
//abstract class BarterinDatabase : RoomDatabase() {
//
//    abstract fun barterinDao(): BarterinDao
//
//    companion object {
//        @Volatile
//        private var instance: BarterinDatabase? = null
//        fun getInstance(context: Context): BarterinDatabase =
//            instance ?: synchronized(this) {
//                instance ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    BarterinDatabase::class.java, "barterin.db"
//                ).build()
//            }
//    }
//
//}