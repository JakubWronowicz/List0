import io.Source
import io.Codec
import java.nio.charset.CodingErrorAction




object WordCloud{
    def main(args: Array[String]) = {
        val dec = Codec.UTF8.decoder.onMalformedInput(CodingErrorAction.IGNORE)
        val lines = Source.fromFile("01-The-Fellowship-Of-The-Ring.txt")(dec).getLines().toList        
        val stop_words = Source.fromFile("Stop_words.txt")(dec).getLines().toList 
        print("START\n") 
        var words =Array[String]()

        for(line<-lines){
            val split = line.toLowerCase().replaceAll("\\p{Punct}", "").replaceAll("“","").split(' ')
            for (w<-split){
                if(!stop_words.contains(w.toLowerCase())){
                    if(w!=""){
                        words = words :+ w.toLowerCase()
                    }
                }
            }
        }
        val word_count = collection.mutable.Map[String,Int]()
        for(word<-words){
            if(word_count.contains(word)){
                word_count(word)+=1
            }else{
                word_count+= (word->1)
            }
        }
        val sorted_seq = word_count.toSeq.sortWith(_._2>_._2)
        print(sorted_seq.take(50))
   
    }
}
