package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>
    {
        var buffer:String?
        when(fullName)
        {
            "" -> buffer = null
            " " -> buffer = null
            else -> buffer = fullName
        }
        val parts:List<String>? = buffer?.split(" ")

        val firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        return Pair(firstName,lastName)
    }

    fun transliteration(payload: String, divider:String = " "): String {

        var lat: String

        lat = payload.replace("а".toRegex(), "a")
        lat = lat.replace("б".toRegex(), "b")
        lat = lat.replace("в".toRegex(), "v")
        lat = lat.replace("г".toRegex(), "g")
        lat = lat.replace("д".toRegex(), "d")
        lat = lat.replace("е".toRegex(), "e")
        lat = lat.replace("ё".toRegex(), "e")
        lat = lat.replace("ж".toRegex(), "zh")
        lat = lat.replace("з".toRegex(), "z")
        lat = lat.replace("и".toRegex(), "i")
        lat = lat.replace("й".toRegex(), "i")
        lat = lat.replace("к".toRegex(), "k")
        lat = lat.replace("л".toRegex(), "l")
        lat = lat.replace("м".toRegex(), "m")
        lat = lat.replace("н".toRegex(), "n")
        lat = lat.replace("о".toRegex(), "o")
        lat = lat.replace("п".toRegex(), "p")
        lat = lat.replace("р".toRegex(), "r")
        lat = lat.replace("с".toRegex(), "s")
        lat = lat.replace("т".toRegex(), "t")
        lat = lat.replace("у".toRegex(), "u")
        lat = lat.replace("ф".toRegex(), "f")
        lat = lat.replace("х".toRegex(), "h")
        lat = lat.replace("ц".toRegex(), "c")
        lat = lat.replace("ч".toRegex(), "ch")
        lat = lat.replace("ш".toRegex(), "sh")
        lat = lat.replace("щ".toRegex(), "sh")
        lat = lat.replace("ъ".toRegex(), "")
        lat = lat.replace("ы".toRegex(), "i")
        lat = lat.replace("ь".toRegex(), "")
        lat = lat.replace("э".toRegex(), "e")
        lat = lat.replace("ю".toRegex(), "yu")
        lat = lat.replace("я".toRegex(), "ya")

        //Capital letters
        lat = lat.replace("А".toRegex(), "A")
        lat = lat.replace("Б".toRegex(), "B")
        lat = lat.replace("В".toRegex(), "V")
        lat = lat.replace("Г".toRegex(), "G")
        lat = lat.replace("Д".toRegex(), "D")
        lat = lat.replace("Е".toRegex(), "E")
        lat = lat.replace("Ё".toRegex(), "E")
        lat = lat.replace("Ж".toRegex(), "Zh")
        lat = lat.replace("З".toRegex(), "Z")
        lat = lat.replace("И".toRegex(), "I")
        lat = lat.replace("Л".toRegex(), "I")
        lat = lat.replace("К".toRegex(), "K")
        lat = lat.replace("Л".toRegex(), "L")
        lat = lat.replace("М".toRegex(), "M")
        lat = lat.replace("Н".toRegex(), "N")
        lat = lat.replace("О".toRegex(), "O")
        lat = lat.replace("П".toRegex(), "P")
        lat = lat.replace("Р".toRegex(), "R")
        lat = lat.replace("С".toRegex(), "S")
        lat = lat.replace("Т".toRegex(), "T")
        lat = lat.replace("У".toRegex(), "U")
        lat = lat.replace("Ф".toRegex(), "F")
        lat = lat.replace("Х".toRegex(), "H")
        lat = lat.replace("Ц".toRegex(), "C")
        lat = lat.replace("Ч".toRegex(), "Ch")
        lat = lat.replace("Ш".toRegex(), "Sh")
        lat = lat.replace("Щ".toRegex(), "Sh")
        lat = lat.replace("Ъ".toRegex(), "")
        lat = lat.replace("Ы".toRegex(), "I")
        lat = lat.replace("Ь".toRegex(), "")
        lat = lat.replace("Э".toRegex(), "E")
        lat = lat.replace("Ю".toRegex(), "Yu")
        lat = lat.replace("Я".toRegex(), "Ya")

        lat= lat.replace(" ",divider)
        return lat //Return latinized string
    }

    fun toInitials(firstName:String?, lastName:String?):String?
    {
        var fnBuf:String? = firstName?.replace(" ", "")
        var lnBuf:String? = lastName?.replace(" ", "")

        if(fnBuf.isNullOrEmpty() && !lnBuf.isNullOrEmpty())
            return lnBuf?.get(0).toString().toUpperCase()
        if(!fnBuf.isNullOrEmpty() && lnBuf.isNullOrEmpty())
            return fnBuf?.get(0).toString().toUpperCase()
        if(!fnBuf.isNullOrEmpty() && !lnBuf.isNullOrEmpty())
            return fnBuf?.get(0).toString().toUpperCase()+lnBuf?.get(0).toString().toUpperCase()
        return null
    }
}