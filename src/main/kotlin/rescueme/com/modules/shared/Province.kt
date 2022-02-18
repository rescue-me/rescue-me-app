package rescueme.com.modules.shared

enum class Province(name: String) {
    MADRID("madrid"),
    GUADALAJARA("guadalajara");

    companion object {
        fun from(name: String) = values().first { it.name.lowercase() == name.lowercase() }
    }
}