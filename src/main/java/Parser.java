import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public void parse(){
        try {
            Document documentHtml = Jsoup.connect("https://www.google.com/")
                    .userAgent("Chrome/81.0.4044.138")
                    .get();

            String htmlText = documentHtml.text();
           // System.out.println(documentHtml.body().html().replaceAll(">([^<]*)<", ""));
           // System.out.println(getPageAsList(htmlText));
            System.out.println(wordCountMap(getPageAsList(htmlText)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String,Integer> wordCountMap(List<String> textList){
        Map<String,Integer> wordsMap = new HashMap<>();

        for (String s : textList){
            if (wordsMap.containsKey(s)) {
                int num = wordsMap.get(s);
                num++;
                wordsMap.put(s,num);
            } else {
                wordsMap.put(s, 1);
            }
        }
        return wordsMap;
    }

    public List<String> getPageAsList(String text){
        List<String> pageList =  Arrays.asList(text.split(" "));

        for (int i = 0; i<pageList.size(); i++){
            if (isContainSymbols(pageList.get(i))){
                String str = toSimpleWord(pageList.get(i));
                if (!str.equals(""))
                    pageList.set(i,str);
            }
        }

        pageList.replaceAll(String::toUpperCase);
        return pageList;
    }

    public boolean isContainSymbols(String str){
        Pattern p = Pattern.compile("[^a-zа-я0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public String toSimpleWord(String str) {
        return str.replaceAll("[^A-Za-zА-Яа-я0-9]", "");
    }

}
