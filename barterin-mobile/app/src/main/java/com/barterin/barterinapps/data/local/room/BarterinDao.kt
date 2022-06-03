package com.barterin.barterinapps.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barterin.barterinapps.data.local.entity.AddressEntity

@Dao
interface BarterinDao {

    @Query("SELECT * FROM address")
    fun getAddress(): LiveData<List<AddressEntity>>

    @Query("DELETE FROM address WHERE id= id = :id")
    suspend fun deleteAddressById(id: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAddress(address: List<AddressEntity>)

}