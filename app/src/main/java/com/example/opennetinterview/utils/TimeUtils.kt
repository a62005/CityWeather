package com.example.opennetinterview.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object TimeUtils {

    /**
     * 將日期字串轉換為英文星期幾
     * @param dateString 格式: "2025-10-25"
     * @return 英文星期幾，例如: "Friday"
     */
    fun formatToWeekday(dateString: String): String {
        return if (Build.VERSION.SDK_INT >= 26) {
            val date = LocalDate.parse(dateString)
            date.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH))
        } else {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
            val date = inputFormat.parse(dateString)
            outputFormat.format(date!!)
        }
    }

    /**
     * 將日期字串轉換為月/日格式
     * @param dateString 格式: "2025-10-25"
     * @return 月/日格式，例如: "10/25"
     */
    fun formatToMonthDay(dateString: String): String {
        return if (Build.VERSION.SDK_INT >= 26) {
            val date = LocalDate.parse(dateString)
            date.format(DateTimeFormatter.ofPattern("M/d"))
        } else {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("M/d", Locale.ENGLISH)
            val date = inputFormat.parse(dateString)
            outputFormat.format(date!!)
        }
    }
}