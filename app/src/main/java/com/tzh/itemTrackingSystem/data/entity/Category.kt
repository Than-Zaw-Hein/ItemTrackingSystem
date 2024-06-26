package com.tzh.itemTrackingSystem.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tzh.itemTrackingSystem.data.TableNameConstant

@Entity(tableName = TableNameConstant.CATEGORY)
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("CategoryId") val id: Int = 0,
    @ColumnInfo(name = "CategoryName") val categoryName: String,
    @ColumnInfo(name = "Description") val description: String? = null
)