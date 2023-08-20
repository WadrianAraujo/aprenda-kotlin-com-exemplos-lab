import sun.security.util.Debug
import kotlin.math.log
import kotlin.random.Random

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(var nome: String, val id: String)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    val subscribe = mutableListOf<Usuario>()
    fun matricular(usuario: Usuario) {
        TODO("Utilize o parâmetro usuario para simular uma matrícula (usar a lista de inscritos).")
    }
}



val users = mutableListOf<Usuario>()
val trainings = mutableListOf<Formacao>()

fun main() {
    println("Ola ADM, seja bem vindo ao sistemas de cadastro de formações e usarios da DIO")
    var exitApplication: Boolean = false
    while(!exitApplication){
        println("Oque voce deseja fazer agora? DIGITE O CODIGO ABAIXO")
        println("1 - CADASTRAR NOVO USUARIO | 2 - INSCREVER USUARIO EM FORMAÇÃO | 3 - CADASTRAR NOVA FORMAÇÃO | SAIR  ")
        val userInput = readLine()
        when(userInput){
            "1" -> RegisterUser()
            "2" -> RegisterUserInTraining()
        }

        if (userInput?.equals("sair", ignoreCase = true) == true) {
            exitApplication = true
        }

    }
    TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}

fun RegisterUserInTraining(){
    var training: String
    var user: String
    var inscribed = false
    var trainingFound =false

    var trainingFinal: Formacao
    var userFinal: Usuario

    while(!trainingFound){
        println("Qual é a formação?")
        training = readln()
        trainings.forEach{
            if (it.nome.equals(training)){
                trainingFinal = it
                trainingFound = false
            }else{ println("Formacao nao existe")
            }
        }
    }
    while(!inscribed){
        println("Qual é o Usuario?")
        user = readln()
        users.forEach{
            if (it.nome.equals(user)){
                userFinal = it
            }else{ println("usuario nao existe")
            }
        }
    }

    TODO("colocar a parte de inserir o usuario na formacao aqui")
}

fun RegisterUser(){
    val nameUser: String
    val idUser: String = GenerateNewId()

    println("Qual é o Nome do Usuario?")
    nameUser = readln()
    users.add(Usuario(nameUser,idUser))
    println("Usuario $nameUser cadastrado com o ID $idUser")
}

fun GenerateNewId() : String{
    var newId = ""
    var generatedNumber = false
    println(users)

    while(!generatedNumber){
        println("gerando novo id")
        var random = Random(System.currentTimeMillis())
        var randomNumber = random.nextInt(0, 100_000_000)
        newId = String.format("%09d", randomNumber)
        if (users.isEmpty()) break
        users.forEach {
            if (!it.id.equals(newId)){
                generatedNumber = true
            }
        }
    }
    return newId
}
