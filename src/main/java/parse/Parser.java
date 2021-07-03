package parse;

import org.jsoup.nodes.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    static Logger LOGGER = java.util.logging.Logger.getLogger(Parser.class.getName());

    public void parse(){
        try {
            UrlConnector connector = new UrlConnector();
            FileInputStream ins = new FileInputStream((Paths.get("").toAbsolutePath()+"\\src\\main\\log.config"));

            LogManager.getLogManager().readConfiguration(ins);

            Document documentHtml = connector.connect("https://www.simbirsoft.com/");

            LOGGER.log(Level.INFO,"Выполняем парсинг полученной html страницы...");

            Map<String,Integer> wordMap =
                    wordCountMap(getPageAsList(documentHtml.text().replaceAll(">([^<]*)<", "")));

            LOGGER.log(Level.INFO,"Вывод информации в консоль...");
            wordMap.forEach((s, integer) -> System.out.println(s+" = "+integer));

            LOGGER.log(Level.INFO,"Вывод выполнен.");
            LOGGER.log(Level.INFO,"Завершение программы.");

            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String,Integer> wordCountMap(List<String> textList){
        LOGGER.log(Level.INFO,"Запуск подсчета повторений слов...");
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
        LOGGER.log(Level.INFO,"Подсчет выполнен.");
        return wordsMap;
    }

    public List<String> getPageAsList(String text){
        LOGGER.log(Level.INFO,"Получение входного текста в виде массива ArrayList<String>...");
        List<String> pageList =  Arrays.asList(text.split(" "));

        for (int i = 0; i<pageList.size(); i++){
            if (containSymbols(pageList.get(i))){
                String str = toSimpleWord(pageList.get(i));
                if (!str.equals(""))
                    pageList.set(i,str);
            }
        }

        pageList.replaceAll(String::toUpperCase);

        LOGGER.log(Level.INFO,"Массив получен.");
        return pageList;
    }

    public boolean containSymbols(String str){
        Pattern p = Pattern.compile("[^a-zа-я0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public String toSimpleWord(String str) {
        return str.replaceAll("[^A-Za-zА-Яа-я0-9]", "");
    }

}