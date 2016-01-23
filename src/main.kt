import org.jsoup.*
import org.jsoup.nodes.Element
import thoth.*

fun main(args : Array<String>){
    ppp()
    //var doc = Jsoup.connect("http://www.baka-tsuki.org/project/index.php?title=Rakudai_Kishi_no_Eiyuutan:Volume1_Chapter_1").get()
    //val t = FindContent(doc)
    //val p = ParentByStringContent(t)
    //println(p?.text() ?: "broke yo")

}

fun cdr(args : List<Element>) : List<Element> {
    if (args.isEmpty())
        return args
    else
        return args.minus(args.first())
}

fun car(args : List<Element>) : List<Element>{
    if (args.isEmpty())
        return args
    else
        return listOf(args.first())
}

val empty = listOf<Element>()

fun FindContent(document: org.jsoup.nodes.Document) : List<Element>{
    val allTags = document.body().select("*")
    var test = allTags.toList()
   
    tailrec fun loop(
            acc : List<Element>,
            successCounter : Int,
            failureCounter : Int,
            elements : List<Element>
        ) : List<Element>
        {
            if (elements.isEmpty())
                return acc
            val text = elements.first().ownText().length

            when{
                elements.isEmpty() -> return acc
                else -> when{
                    failureCounter > 3 -> return loop(acc, 0, 0, cdr(elements))
                    successCounter > 400 -> return loop(acc, 0, 0, empty)
                    text < 30 -> return loop(acc, successCounter, failureCounter + 1, cdr(elements))
                    text > 30 -> return loop(acc.plus(elements.first()), (successCounter + text), failureCounter, cdr(elements))
                    else -> return loop(acc, successCounter, failureCounter, cdr(elements))
                }
            }
        }
    return loop(empty, 0, 0, test)
}

fun ParentByStringContent(sample : List<Element>) : Element? {
    val total = sample.fold(0) {total, next -> total + next.ownText().length}
    val parents = (sample.map {element -> element.parent()}).filterNotNull()
    
    if (parents.isEmpty())
        return null

    for(parent in parents){
        if ((parent.text().length / total) > 0.7)
            return parent
    }
    return ParentByStringContent(parents)
}

