import java.math.BigDecimal

fun main1(args: Array<String>) {
    println("Hello world!")
    //строчный комментарий one line

    /*многострочный комментарий
    *
    * /* вложенный комментарий(в отличии от java)    */
     */
}

/*
типы данных

Any

Boolean
Byte
UByte
Char
Short
UShort
Int
Long
ULong

Float
Double

String

Unit

Nothing

 */

val firstName: String = "Aslan"
var age = 30
const val DEBUG = true

fun main2() {
    //firstName="Vova" нельзя изменить val

    //if
    val temperature = 26
    val condition = if (temperature < -5) "Cold" else if (temperature < 5) "Norm" else "Warm"
    //also if(temperature<-5 && age>5) ИЛИ if(temperature<-5 || age>5)

    //when
    val condition2 = when {
        temperature < -5 -> "Cold"
        temperature < 5 -> "Norm"
        else -> "Warm"
    }

    //Nullable?
    //age=null нельзя!
    var position: String? = "programmer"
    position = null
    //println("length is "+position.length) НЕЛЬЗЯ!
    //case1
    if (position != null) {
        println("length is " + position.length)
    }
    //case2
    println("length is " + position?.length)
    //case3
    println("length is ${position?.length ?: 0}")
    println(position?.length ?: 0)
    //case4
    //println(outputString="length is " +  position!!.length) !!-при Null->otlinNullPoinerException

    //Проверка типов
    val obj: Any = "str"
    if (obj is String) {//smart cast is  !is
        println("obj is String")
    }
    //val obj2:String=obj НЕЛЬЗЯ!
    val obj2: String = obj as String//unsafe cast
    //val obj3:String=age as String//ClassCastException НЕЛЬЗЯ!
    val obj3: String? = age as? String//safe cast
    println(obj3)//->null


    //String templates
    println("name: " + firstName + " age: " + age)
    println("name: $firstName age: ${age - 2}")

    //для реглярных выражений
    println("\\")//acse1 ->\
    println("""\""")//acse2 ->\
    println(
        """
        one line
        two line
        three line
    """.trimIndent()
    )

    //для sql запросов
    println(
        """
        |SELECT * FROM USERS
        |WHERE age>24
        |ORDER BY NAME
    """.trimMargin()
    )//trimMargin тримит слева всё от символа | включительно
}

//-----------------------------------------------------------------------------------------------------------
//case1
fun greeting1(first: String, last: String): String {
    return "Hello, $first $last"
}

//case2
fun greeting2(first: String, last: String) = "Hello, $first $last"

//case with default parameters
fun greeting3(first: String, last: String = "Bolurov") = "Hello, $first $last"

//Если обязательно нужно указывать имя
fun greeting4(first: String, last: String = "Bolurov"): String {
    require(!first.isBlank()) { "You need point name!" }//IllegalArgumentException message
    //require(first.isNotBlank()){"You need point name!"}//IllegalArgumentException message ИЛИ ТАК

    //equiereNotNull
    //check
    //assert
    return "Hello, $first $last"
}
//-----------------------------------------------------------------------------------------------------------------
//Тестирование JUnit

//в gradle зависимость:
//dependencies{ (JUnit4)
//    implementatin ...
//    testCompiole group:'junit',name:'junit', version:'4.12'
//}
//dependencies{ (JUnit5)
//    implementatin ...
//    testImplementation "org.junit.jupiter:junit0jupiter:5.5.2"
//}
//test{
//    useJUnitPlatform()
//}


// -> new class "TestGreet.kt"

//-----------------------------------------------------------------------------------------------------------
//while do-while
fun main3() {
    var counter = 0
    while (counter < 10) {
        println(counter++)
    }
    //do-while чтобы оказаться в теле цикла хотя бы 1 раз
//    do {
//        val command= readLine()
//        println(command)
//    }while (command != "stop")

    //for
    val oneToFive = 1..5
    val letters = 'A'..'z'//'a'..'z'
    val words = "Java".."Yaml"
    for (i in oneToFive) {
        println(i)
    }
    for (letter in letters) {
        println(letter)
    }

    //Задача-Написать функцию вычисления японской зп,где оклад увеличивается на выслугу  лет.
    //(для денег исп-ть BigDecimal)
    fun japaneseSalary(base: BigDecimal, years: Int): BigDecimal {

        require(base > BigDecimal.ZERO) { "Base salary can't be negative" }
        return base + 10_000.toBigDecimal() * years.toBigDecimal()
    }
    //теты этой функции
//    @Test
//    fun TestNegativeSalary(){
//        val thrown =assertThrows<java.lang.IllegalArgumentException>{
//            japaneseSalary((-10).toBigDecimal(), years = 12)
//        }
//        assertEquals("Base salary can't be negative", thrown.message)
//    }
}

//-----------------КОЛЛЕКЦИИ-------------------------------------------------------------------
fun main4() {
    //Массивы
    val intArray: Array<Int> = arrayOf<Int>(1, 2, 3, 4, 5, 6, 7)
    val array = arrayOf(1, 2, 3, 4, 5, 6)
    val strArray = arrayOf("one", "two", "three")
    //strArray= arrayOf("five") НЕЛЬЗЯ потому что val а не var
    strArray[2] = "ten"//МОЖНО!
    //strArray[4]="four"//->java.lang.ArrayIndexOutOfBoundsException

    //Перечисления
    val list = listOf("one", "two", "three")
    //list[0]="ONE" НЕЛЬЗЯ!
    val mutableList = mutableListOf("one", "two", "three")
    mutableList[0] = "ONE"
    mutableList.add("Four")

    //Отображения
    val pair = Pair("one", "two")
    println("first: ${pair.first} second: ${pair.second}")
    val infixPair = 1 to "one"

    val map = mutableMapOf(1 to "one", 2 to "two")
    for ((f, s) in map) {
        println("f: $f s: $s")
    }

}

//Регулярные выражения
fun main5() {
    val line = "abc 123 def"

    val regexp = """.*\d{3}.*"""
    val pattern = regexp.toRegex()

    println(line.matches(pattern))

    //Упражнение-создать fun date для проверки формата даты
    println(date("12 JAN 2022"))

    html()
}

fun date(date: String): Boolean {
    val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

    val regex = """\d{2} $month \d{4} """
    return date.matches(regex.toRegex())


}

//@Test
//fun regexpTest(){
//    assertTrue(date("12 JAN 2020"))
//    assertFalse(date("12 ZZZ 2020"))
//}
fun html() {
    val html = "<html><head>hello</head><h1><p>Great!<br></html>"

    val regexp = """<[^>]+>"""

    val found = regexp.toRegex().findAll(html)

    for (result in found) {
        println(result.value)
    }

}

//----------О О П--------------------------------------------------------------------------------------------
fun main6() {
    val max = Person("Max", true)
    println(max.name)
    println(Color1.BLUE)//Enum
    println(Color2.GREEN.g)//Enum2

    println("Java".lastCharOf())// ->a
    println(listOf("one", "two").firstElementOf())
}

class Person(
    val name: String,
    var isMarried: Boolean = false
)

//ENUM-ы
enum class Color1 {
    //version 1
    RED, GREEN, BLUE
}

enum class Color2//version 1
    (val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255);//";" ОБЯЗАТЕЛЬНА!

    fun rgb() = r * 256 * 256 + g * 256 + b

}

//Расширение любого класса, даже Immutable "String"
fun String.lastCharOf(): Char = get(length - 1)
fun List<String>.firstElementOf(): String = get(0)

//Интерфейсы
interface View {
    //fields полей нету в интерфейсах
    fun click()
    fun whoAmI() = println("I'm a view!")
}

interface Shape {
    fun whoAmI() = println("I'm a shape!")
}

class Button : View, Shape {
    final override fun click() = println("Button click")//"final" запрет на переопределение в классах-потомках

    //case1
    //override fun whoAmI()=println("I'm a button!") Метод есть в обеих интерфейсах и обязательно нужно переопределить его
    //case2
    override fun whoAmI() {
        super<View>.whoAmI()
        super<Shape>.whoAmI()
    }
}
//note:в котлине по умолчанию все классы и методы не "open"

//abstract и final взаимоисключающие модификаторы
abstract class Animated {
    abstract fun animate()//с кл.словом abstarct можно не писать реализацию метода

    //если у нас есть реализация метода и мы хотим дать возможность ее переопределения в потомках,то "open"
    open fun startAnimate() {}

}

//Вложенне и внутренние классы
class Outer { //внешний
    class Nested { //Вложенный

    }

    inner class Internal { //Внутренний
        //получаем ссылку на внешний класс
        fun getOuter(): Outer = this@Outer
    }

}

fun main7() {
    val outer = Outer()
    val nested = Outer.Nested()
    val internal = outer.Internal()

}

//Изолированнные классы "sealed"
/*Добавление модификатора sealed к суперклассу ограничивает возможность создания подклассов.
Все прямые подклассы должны быть вложены в суперкласс.
Запечатанный класс не может иметь наследников, объявленных вне класса.
*/
sealed class SealedClass {
    class One(val value: Int) : SealedClass()
    class Two(val x: Int, val y: Int) : SealedClass()

    fun eval(e: SealedClass): Int =
        when (e) {
            is SealedClass.One -> e.value
            is SealedClass.Two -> e.x + e.y
        }
}
/*В методе eval() при использовании when не пришлось использовать ветку else,
так как sealed позволяет указать все доступные варианты и значение по умолчанию не требуется.
Если позже вы добавите новый подкласс, то выражение when будет ругаться и вы быстро вспомните,
 что нужно добавить новый код в программу.
По умолчанию запечатанный класс открыт и модификатор open не требуется.
Запечатанные классы немного напоминают enum.
*/

//Конструкторы
class User(val name: String)
class User2(_name: String) {
    val name: String = _name
}

class User3() {
    var name: String = ""

    constructor(name: String = "Aslan") : this() {
        this.name = name
    }

    init {//Сначало выполняется init блок а потом вып-ся конструктор

    }
}

//Переопределение toString(), equals(), haskCode() или для чего "data class"
fun main9() {
    val misha = Client("Misha", 369000)
    val misha2 = Client("Misha", 369000)

    println(misha) // ->Client@27bc2616 override toString()
    println(misha == misha2) // false override equals()
    val set = hashSetOf(misha)
    println(set.contains(misha2)) // false override hashCode()

    //data class-ы можно копировать
    val vasya = misha.copy("Vasya")
    println(vasya)
    //и деструктурировать и компоненты
    val (name, index) = vasya
    println(vasya.component1())
    println(vasya.component2())

}

//class Client(val name: String, val index:Int){
//    override fun toString(): String {
//        return "name: $name, index: $index"
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (other ==null || other !is Client) return false
//        return name==other.name && index==other.index
//    }
//
//    override fun hashCode(): Int {
//        return name.hashCode()*37+index
//    }
//}
data class Client(val name: String, val index: Int) {}

//Упражнение: создать расширение String.isEmptyOrNull()
fun String?.isEmptyOrNull(): Boolean = this?.isEmpty() ?: true

//Упражнение infix сумматор
infix fun Int.sm(other: Int): Int = this + other
fun main8() {
    println(12.sm(22))
    println(12 sm 22)
}


//-----------Объекты------------------------------------------------------------------------------
object Singleton {
    var name = "Aslan"
    fun hello() = println("Hello")
}

fun main10() {
    println(Singleton.name)
    Singleton.hello()

    Outer1.hello()

    println(lazyValue)
    println(lazyValue)

    val lateDemo = LateinitDemonstation()
    println(lateDemo.name)//->Aslan
    //println(lateDemo.late)//->UninitializedPropertyAccessException
    lateDemo.initSomeValue()
    println(lateDemo.late) //->"initialized"

}

class Outer1 {
    companion object {
        fun hello() = println("Hello from companion object")
    }
}

//можно дать название comnpanion object-ов
class Outer2 {
    companion object Object1 {
        fun hello() = println("Hello from companion object 1")
    }
}

//Переопределение операторов + -> "plus", - "minus", * "times", / div ,% mod, - "unaryMinus()"
operator fun Int.plus(other: Int): Int = (this + other) * 2

//Механизм lazy , при первом обращении инициализирует, а при последующих возвращает результат
val lazyValue: String by lazy {
    println("Инициализация")
    "Hello"
}

//lateinit
class LateinitDemonstation {
    val name: String = "Aslan"
    lateinit var late: String

    fun initSomeValue() {
        late = "Initialized"
    }
}

//Лямбда-выражения
data class Person1(val name: String, val age: Int)

val people = listOf(
    Person1("Aslan", 30),
    Person1("Vasya", 31),
    Person1("Alex", 35)
)

fun main11() {
    //находим с минимальным age
    //case1
    println(people.minBy({ p: Person1 -> p.age }))
    //case2
    println(people.minBy() { p: Person1 -> p.age })//если лямбда-последий аргумент то выносится за скобки
    //case3
    println(people.minBy { p: Person1 -> p.age })
    //case4
    println(people.minBy { p -> p.age })
    //case5
    println(people.minBy { it.age })
    //case6
    println(people.minBy(Person1::age))
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    list.filter { x: Int -> x % 3 == 0 }
    list.filter { x -> x % 3 == 0 }
    list.filter { it % 3 == 0 }

    people.maxBy { it.age }
    list.distinct()
    people.distinctBy {  }
    people.map { it.name   }
    //sum всех age case1
    people.map { it.age }.sum()
    //sum всех age case2
    people.sumBy { it.age }


    list.reduce{acc,elem -> acc*elem}
    list.fold(1) {acc,elem -> acc*elem}

    people.groupBy { it.age }//.values   или     .key
    people.associateBy { it.age }//похож на groupBy но группируются не ве элементы,а только последний
    people.partition { it.age >30 }

    //flatten() переводит многомерный list в одномерный список
    //listOfList.flatten()
}

//Практика с лямбда в коллекциях
data class Emp(
    val name: String,
    val age : Int,
    val position: String
)

fun main()
{
    val employees = listOf(
        Emp("Max Petrov",       22, "programmer"),
        Emp("Ivan Shapovalov",  33, "analyst"),
        Emp("Semen Deznev",     55, "manager"),
        Emp("Oleg Petrov",      19, "intern"),
        Emp("Katerina Drogova", 31, "programmer"),
        Emp("Nikolay Spivakov", 23, "analyst"),
        Emp("Boris Moiseev",    48, "manager"),
        Emp("Petr Sveshnikov",  37, "programmer"),
        Emp("Maria Kasatonova", 33, "analyst"),
        Emp("Olga Filimonova",  27, "programmer")
    )

    // самый молодой
    println( employees.minBy (Emp::age) )

    // количество программистов
    println(
        // employees.filter { it.position == "programmer" }.count()
        employees.count { it.position == "programmer" }
    )

    // распечатать все имена программистов - т.е., Max, Ivan, Semen и т.п.
    println(
        employees.filter { it.position == "programmer" }.map { it.name.substringBefore(" ") }
    )

    // средний возраст
    println(
        employees.map { it.age }.average()
    )

    // разделить на две группы - старше 40 и младше
    // и найти профессию самого "старого" из "молодых"
    println(
        employees.partition { it.age > 40 }.second.maxBy { it.age }
    )

    // сгруппировать по профессии и распечатать профессию и количество работников в ней
    println(
        employees.groupBy { it.position } .mapValues { it.value.count() }
    )

    // вернуть в виде пары средний возраст женщин и мужчин

    // распечатать работников с самым часто встречающимся возрастом
    println(
        employees.groupBy { it.age }.maxBy { it.value.count() }
    )

    // пару из работников с самой большой разницей в возрасте
    println(
        employees.flatMap { emp -> employees.map { it -> Pair(it, emp) } }
            .maxBy { it.first.age - it.second.age }
    )


}



package lesson6

class lesson6 {
    var numberOfGets=0
    //увеличивать  numberOfGets каждый раз при получении значения number

    var number:Int=0
        get()=numberOfGets++

}

class LazyClass(val initializer: () -> Int){
    //val initializer:Int=0
    //при первом обращении  initial=initializer(), а при последующих обращениях просто значение initial
    val initial:Int by lazy{initializer()}


}
//расширения Int для определения четности числа isOdd() и isEven()
//fun Int.isOdd():Boolean=this %2 == 1
//fun Int.isEven():Boolean=this %2 == 0

//Это же в виде свойств расширения
val isEven: Int.() -> Boolean = { this %2 == 0 }
val isOdd:  Int.() -> Boolean = { this %2 == 1 }

//расширение форматa списка
fun Collection<String>.formatString(
    prefix:String = "[",
    suffix:String = "]",
    delim:String = ",",
    processor:(String)  -> String = {it}
):String {
    val result = java.lang.StringBuilder()
    for ((index,element) in this.withIndex()){
        if (index !=0){
            result.append(delim)
        }
        result.append(processor(element))
    }
    result.append(suffix)
    return result.toString()
}

fun main1(){
    val claz=lesson6()
    claz.number
    claz.number
    println(claz.numberOfGets)//2 раза

    val init:() -> Int={
        println("init")
        3
    }
    val lazyClass=LazyClass(init)
    lazyClass.initial//выводит init только 1 раз
    lazyClass.initial//не выводит init
    lazyClass.initial//не выводит init

    println(15.isOdd())   //->true
    println(12.isEven())  //->true

    println(
        listOf("abc","def","jhk").formatString("{","}",",") {"" +it.length}// ->{abc,def,jhk} ->{3,3,3}
    )
}


//-----------Работа с JSON--------------------------------------------------------------------------------

class JSONdemo{

}
//Добавление зависимости
//dependences{
//    c
//}ompile 'org.json:json:20190722'
fun main299(){
    val json="""
   {
   "owner": "John Smith",
   age: 55,
   "books":[
      {
         "id":"444",
         "language":"C",
         "edition":"First",
         "author":"Dennis Ritchie "
      },
      {
         "id":"555",
         "language":"C++",
         "edition":"second",
         "author":" Bjarne Stroustrup "
      }
   ]
}    
""".trimIndent()

//    val obj= JSObject(json)
//    println(obj.getString("owner"))
//    val books=obj.getJSONArray("books")
//    val book=books[1] as JSONObject
//    println(book.getString("author"))

    //подключаем gson-библиотеку
    //compile 'com.google.code.gson:gson:2+'

    //Settings->Plagins->JSON to kotlin class
    //затем new-> Kotlin from JSON ->class"BookOwner"

//    val gson=Gson()
//    val owner:BookOwner=gson.fromJson(json,BookOwner::class.java)
//    //автор второй(индекс 1) книги
//    println(owner.books[1].author)


//Работа с XML
    //Парсер SAX- потоковый
    //Парсер DOM
//    val builderFactory=DocumentBuilderFactory.newInstatnce()
//    val documentBuilder=builderFactory.newDocumentBuilder()
//    val doc=documentBuilder.parse(InputSource(StringReader(xml)))
//    val books=doc.getElementsByTagName("book")
//    for(i in 0 until books.length){
//        val book=books.item(i)
//        println(book.attributes.getNamedItem("id"))
//        println(book.childNodes.item(1).textContent)
//    }
}

//Функция с собственным контекстом

fun main99(){
    val string="SomeString"
    string.also {
        println("$it has a length of ${it.length}")

        val anotherString=string.also {
            val hello="hello"
            println("$it has a length of ${it.length}")

        }
        println(anotherString)

        listOf("one","two","three").also { println("$it has a length of ${it.size}") }
        listOf("one","two","three").filter{it.length>3}.also { println("$it has a length of ${it.size}") }

        //run    .run
        run{println("hello!")}
//        val map= mutableMapOf(1 to Window())
//        val window1=map[2]?.run{
//            wigth=100
//            isVisible=true
//            this
//        }
        val builder= java.lang.StringBuilder()
        with(builder){
            for(letter in 'a'..'z'){
                append(letter)
            }
        }
        println(builder)// -> absdef..z

        builder.apply {
            append("123")
        }

        var mystring:String?="123"
        mystring?.let {
            println(it.length)
        }

    }
}








