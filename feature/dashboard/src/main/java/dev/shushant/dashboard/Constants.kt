package dev.shushant.dashboard

enum class NewsArrangement {
    NewToOld,
    OldToNew;

    companion object {
        fun getValue(string: String) = NewsArrangement.entries.find { it.name == string }
    }
}