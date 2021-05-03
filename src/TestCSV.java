import org.junit.Before;
import org.junit.Test;
import utils.CSVUtil;
import utils.Property;

import javax.swing.plaf.IconUIResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestCSV {
    List<Property> propertyList;
    @Before
    public void setup(){
        try {
            propertyList = CSVUtil.readCSV("data/property.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestRead() throws IOException {
        System.out.println("length = " + propertyList.size());
        for(int i =0;i<10;++i){
            System.out.println(propertyList.get(i));
        }
        String [] area = {"崇礼楼","达高楼","行知楼","清华楼","同方楼","同源楼"};
        HashSet<String> [] set = new HashSet [6];
        for(int j = 0; j<area.length;++j){
            HashSet<String> set1 = new HashSet<>();
            for(Property property : propertyList){
                if(property.getArea().equals(area[j]))
                    set1.add(property.getName());
            }
            set[j] = set1;
        }

        // [崇礼楼二楼连廊, 音乐厅]
        // [达高楼五楼509仓库, 达高楼五楼, 达高楼五楼项目实训室五, 达高楼五楼项目实训室三, 达高楼五楼项目实训室六, 达高楼五楼项目实训室二, 达高楼四楼工作室, 达高楼五楼项目实训室一, 达高楼四楼项目实训室九, 达高楼五楼创意工作室二, 达高楼四楼项目实训室七, 达高楼四楼项目实训室十, 达高楼五楼原SKY工作室, 达高楼五楼创意工作室一, 达高楼五楼项目实训室四, 达高楼四楼项目实训室八]
        // 行知楼[X503]
        // [清华楼3409教室, 清华楼3403教室, 清华楼3414教室, 清华楼3404教室, 清华楼3415教室, 清华楼四楼教室, 清华楼3401教室, 清华楼3412教室, 清华楼3402教室, 清华楼3413教室, 清华楼3407教室, 清华楼3408教室, 清华楼3406教室, 清华楼3410教室, 清华楼3411教室]
        // [同方楼2309教室, 同方楼2405机房, 同方楼2407机房, 同方楼2310教室, 同方楼2406机房, 同源楼二楼]
        // [同源楼四楼, 同源楼一楼, 同源楼四楼7410, 同源楼一楼7113, 同源楼四楼7411, 同源楼四楼7412, 同源楼四楼7413, 同源楼四楼7414, 同源楼四楼7415, 同源楼四楼7416, 同源楼四楼7418, 同源楼四楼7419, 同源楼二楼7209, 同源楼二楼7205, 同源楼二楼7206, 同源楼二楼7207, 同源楼二楼7208, 同源楼五楼, 同源楼三楼7314, 同源楼三楼7315, 同源楼三楼7312, 同源楼三楼7313, 同源楼五楼7511, 同源楼二楼, 同源楼一楼7108, 同源楼一楼7109, 同源楼一楼7106, 同源楼一楼7107, 同源楼一楼7105, 同源楼四楼7421, 同源楼四楼7401, 同源楼四楼7402, 同源楼四楼7424, 同源楼四楼7403, 同源楼四楼7404, 同源楼四楼7405, 同源楼四楼7407, 同源楼四楼7408, 同源楼四楼7409, 同源楼三楼7304, 同源楼一楼7121, 同源楼三楼7302, 同源楼三楼, 同源楼三楼7307, 同源楼三楼7308, 同源楼二楼7210, 同源楼三楼7306]
        for(int i = 0;i<6;++i){
            ArrayList<String> arr = new ArrayList<>(set[i]);
            System.out.print("{ ");
            for(String s: arr){
                System.out.print('"'+s+'"'+',');
            }
            System.out.println("}");
        }

//        System.out.println(arr);
    }
    @Test
    public void TestFilter() throws IOException {
       List<Property> properties = CSVUtil.filter("清华楼", "清华楼3401教室");
       for(Property property: properties){
           System.out.println(property);
       }
    }
    @Test
    public void TestList2Array() throws IOException {
        List<Property> properties = CSVUtil.filter("清华楼", "清华楼3401教室");
        Object[][] objects = CSVUtil.list2Array(properties);
        System.out.println(objects);
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[i].length; j++) {
                System.out.print(objects[i][j]+ ", ");
            }
            System.out.println();
        }
    }
    @Test
    public void TestSum(){
       List<Property> properties = CSVUtil.filter("崇礼楼");
       CSVUtil.summarize(properties);
        System.out.println(properties.size());
       for(Property property: properties){
           System.out.println(property.toString());
       }
    }
}
