interface SuggestionController<T>{
    fun search(str:String):List<String>
    fun recent(str:String):String
    fun printS(str:String)
}

class User(var id :String,var userName:String, var name :String, var surName:String){}
class Tag(var id:String,var name:String){}

class UserSuggestionController:SuggestionController<User>{
    override fun search(str: String):List<String>{
        val list = mutableListOf<String>()
        for(i in userList) {
            if ((i.name.contains(str[1]) || i.surName.contains(str[1]))) {
                list.add(i.userName)
            }
        }
        return list
    }

    override fun recent(str:String):String {
        return str
    }

    override fun printS(str: String) {
        for(i in userList){
            if(i.userName == str)
                println(i.id + " " + i.userName + " " + i.name + " " + i.surName)
        }
    }
}

class TagSuggestionController:SuggestionController<Tag>{
    override fun search(str: String):List<String>{
        val list = mutableListOf<String>()
        for(i in tagList) {
            if (i.name.contains(str[1])) {
                list.add(i.name)
            }
        }
        return list
    }

    override fun recent(str:String):String {
        return str
    }

    override fun printS(str: String) {
        for(i in tagList){
            if(i.name == str)
                println(i.id + " " + i.name)
        }
    }
}


fun main() {
    val userSuggestionController
            : SuggestionController<User> = UserSuggestionController()

    val tagSuggestionController
            : SuggestionController<Tag> = TagSuggestionController()

    println("Please enter username or hashtag")
    println("Hashtags should start with # and username with @")
    val input = readLine()

    if (input != null) {
        when (input[0]) {

            '@' -> {

                val searchedUsers = userSuggestionController.search(input)
                println("searched users: $searchedUsers") // matched users that contain 'a'
                println("please select username")
                val userName = readLine()
                if (userName != null) {
                    userSuggestionController.printS(userName)

                    println("Recent users")
                    val recentUsers = userSuggestionController.recent(userName)
                    println("searched users: $recentUsers") // recently added users
                }else println("Please enter username or hashtag")
            }

            '#' -> {
                val searchedTags = tagSuggestionController.search(input)
                println("searched tags: $searchedTags") // matched tags that contain 'a'
                println("Please select tag name")
                val tagName = readLine()
                if (tagName != null) {
                    tagSuggestionController.printS(tagName)

                    val recentTags = userSuggestionController.recent(tagName)
                    println("searched tags: $recentTags") // recently added tags
                }else println("Please enter username or hashtag")

            }
            else -> println("WRONG INPUT!!!")

        }
    }else println("Please enter username or hashtag")
}

