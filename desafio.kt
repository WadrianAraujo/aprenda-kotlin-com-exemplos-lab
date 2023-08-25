import sun.security.util.Debug
import java.util.concurrent.TimeUnit
import kotlin.math.log
import kotlin.random.Random

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(var nome: String, val id: String)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    val subscribe = mutableListOf<Usuario>()
    fun matricular(usuario: Usuario) {
        subscribe.add(usuario)
    }
}

val users = mutableListOf<Usuario>()
val trainings = mutableListOf<Formacao>()
var courses = mutableListOf<ConteudoEducacional>()

fun main() {
    println("Ola ADM, seja bem vindo ao sistemas de cadastro de formações e usarios da DIO")
    var exitApplication: Boolean = false
    while(!exitApplication){
        println("Oque voce deseja fazer agora? DIGITE O CODIGO ABAIXO")
        println("1 - CADASTRAR NOVO USUARIO | 2 - INSCREVER USUARIO EM FORMAÇÃO | 3 - CADASTRAR NOVA FORMAÇÃO | 4 - SAIR")
        val userInput = readLine()
        when(userInput){
            "1" -> RegisterUser()
            "2" -> RegisterUserInTraining()
            "3" -> RegisterTraining()
            "4" -> exitApplication = true
        }

        if (userInput?.equals("sair", ignoreCase = true) == true) {
            exitApplication = true
        }
    }
}

fun RegisterTraining(){
    var training: String
    var courseName: String
    var courseDuration: Int
    var course: ConteudoEducacional
    var finished = false
    var nivel : Nivel


    println("Qual o nome da Formação?")
    training = readln()

    println("Insira agora os cursos da formação $training ")
    TimeUnit.MILLISECONDS.sleep(50)

    while(!finished){
        println("Qual o nome do curso?")
        courseName = readln()
        println("Qual a duração em horas?")
        courseDuration = readln().toInt()
        println("Qual o nivel do curso? 1 - Basico || 2 - Intermediario || 3 - Avançado")
        nivel = ChoiceLevel()
        courses.add(ConteudoEducacional(courseName,courseDuration,nivel))
        println("Deseja inserir outro curso ? 1 - Sim || 2 - Nao")
        val continueHere = readln()
        if (continueHere == "2") finished = true
    }
    trainings.add(Formacao(training,courses))
}

fun ChoiceLevel(): Nivel{
    var nivel = Nivel.BASICO
    var enterNivel = readln()
    when(enterNivel){
        "1" -> nivel = Nivel.BASICO
        "2" -> nivel = Nivel.INTERMEDIARIO
        "3" -> nivel = Nivel.AVANCADO
    }
    return nivel
}

fun RegisterUserInTraining(){
    var training: String
    var user: String
    var inscribed = false
    var trainingFound =false

    var trainingFinal: Formacao
    var userFinal = Usuario("", "")

    while(!inscribed){
        println("Qual é o Usuario?")
        user = readln()
        users.forEach{
            if (it.nome.equals(user)){
                userFinal = it
                inscribed = true
            }else{ println("usuario nao existe")
            }
        }
    }

    while(!trainingFound){
        println("Qual é a formação?")
        training = readln()
        trainings.forEach{
            if (it.nome.equals(training)){
                trainingFinal = it
                trainingFinal.matricular(userFinal)
                trainingFound = true
            }else{ println("Formacao nao existe")
            }
        }
    }
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
