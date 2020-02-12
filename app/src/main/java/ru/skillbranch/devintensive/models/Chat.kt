package ru.skillbranch.devintensive.models

class Chat(
    val id:String,
    val nembers: MutableList<User> = mutableListOf(),
    val messege: MutableList<BaseMessage> = mutableListOf()
)
{
}