package ru.skillbranch.devintensive.extensions


import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    var dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.shortFormat(): String?
{
    val pattern = if(this.isSameDay(Date())) "HH:mm" else "dd.MM.yy"
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.isSameDay(date:Date): Boolean
{
    val day1 = this.time/DAY
    val day2 = date.time/DAY
    return day1 == day2
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units)
    {
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE-> value * MINUTE
        TimeUnits.HOUR-> value * HOUR
        TimeUnits.DAY-> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.humanizeDiff(date:Date = Date()):String
{
    var dateBuf:Long = this.time - date.time
    var retStr:String = ""

    var buf:Long = dateBuf.div(DAY)
    if(buf>365)
    {
        buf = buf.div(365)
        when(buf.toInt())
        {
            1 ->retStr="более чем через год"
            2,3,4->retStr="более чем ${buf.toInt()} года"
            5,6,7,8,9,10->retStr="более чем ${buf.toInt()} лет"
        }
    }
    if(buf<365)
    {
        buf = buf.div(365)
        when(buf.toInt())
        {
            -1 ->retStr="более года назад"
            -2,-3,-4->retStr="более чем ${buf.toInt()} года назад"
            -5,-6,-7,-8,-9,-10->retStr="более чем ${buf.toInt()} лет назад"
        }
    }

    return retStr
}

fun TimeUnits.plural(value:Int):String
{
    var retStr :String =""
    if(this == TimeUnits.SECOND)
    {
        when(value)
        {
            1->retStr="$value секунду"
            2,3,4->retStr="$value секунды"
            5,6,7,8,9,0->retStr="$value секунд"
        }
    }
    return retStr
}