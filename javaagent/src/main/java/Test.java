import java.io.File;
import java.util.*;

/**
 * PACKAGE_NAME
 * Test
 * ctl 2019/8/18 17:08
 */
public class Test {
    public static void main(String[] args) {
        File file = new File("E:\\迅雷下载\\520");
        file = new File("F:\\E\\520");
        file.listFiles();

        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        Map<String, File> map = new HashMap<>();
        filesSelect(file.listFiles(), set, list, map);
        System.out.println("--------------------------------------------------------------------");
        // list.parallelStream().forEachOrdered(str->System.out.println(str));
    }

    public static void filesSelect(File[] files, Set<String> set, List<String> list, Map<String, File> map) {
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
//            System.out.println(file.getAbsoluteFile());
//            System.out.println(file.getAbsolutePath());
//            System.out.println(file.getName());
            if (file.isDirectory()) {
                filesSelect(file.listFiles(), set, list, map);
            } else {

                int size = set.size();
                set.add(file.getName());

                if (set.size() == size) {
                    if(!file.getName().endsWith("jpg")){
                        System.out.println(map.get(file.getName()) + "\t" + file.getAbsoluteFile());
                    }
                    list.add(file.getAbsolutePath());
                    //  file.delete();
                    if(map.get(file.getName()).length()==file.length()){
                        map.get(file.getName()).delete();
                        System.out.println("------------"+file.getName());
                    }else {
                        if(!file.getName().endsWith("jpg")){
                            System.out.println(map.get(file.getName()).length()+"\t"+file.length());
                        }
                    }
                } else {
                    map.put(file.getName(), file);
                }
            }
        }
    }
}
