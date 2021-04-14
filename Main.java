import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<String,Integer>  hashMap = new HashMap<String,Integer>();
        String []keyWords= { "abstract","assert","boolean","break","byte","case","catch",
                "char","class","const","continue","default","do","double","else",
                "enum","extends","false","final","finally","float",
                "for","goto","if","implements","import","instanceof",
                "int","interface","long","native","new","null","package",
                "private","protected","public","return","short","static",
                "strictfp","super","switch","synchronized","this","throw",
                "throws","transient","true","try","void","volatile","while"};
        String a;
        StringBuilder ss = new StringBuilder();
        while (true){
            a = input.nextLine();
            if(a.equals("exit")){
                break;
            }
            if(a.matches("(.*)//(.*)")){ //匹配注释前后
               String []b = a.split("//");
               ss.append(b[0]+" ");
            }

            else{
                ss.append(a+" ");
            }
        }
        //System.out.println(ss);
        String str1 = ss.toString();
        int count = 0;
        Pattern p1= Pattern.compile("\\[(.*?)]");
        Matcher m1=p1.matcher(str1);
        while (m1.find()){
            str1 = str1.replace(m1.group(0),"");
            //p= Pattern.compile("\"(.*?)\"");
            m1=p1.matcher(str1);
        }
        Pattern p= Pattern.compile("\"(.*?)\"");
        Matcher m=p.matcher(str1);
        while (m.find()){
            str1 = str1.replace(m.group(0),"");
            //p= Pattern.compile("\"(.*?)\"");
            m=p.matcher(str1);
        }
        //System.out.println(str1);
        p=Pattern.compile("/\\**(.*?)/");
        m=p.matcher(str1);
        while(m.find()){
            str1=str1.replace(m.group()," ");
            m=p.matcher(str1);
        }
        if(str1.isEmpty()){
            System.out.println("Wrong Format");
            System.exit(0);
        }
         str1 = str1.replace("[", " ");
         str1 = str1.replace("]"," ");
         str1 = str1.replace(","," ");
         /*str1 = str1.replace("+", " ");
         str1 = str1.replace("-", " ");
         str1 = str1.replace("*", " ");
         str1 = str1.replace("/", " ");
         str1 = str1.replace(">", " ");
         str1 = str1.replace("<", " ");
         str1 = str1.replace("=", " ");
         str1 = str1.replace("!", " ");
         str1 = str1.replace("|", " ");
         str1 = str1.replace(":", " ");
         一开始想把这些负号替换成空格的，但是可以直接把非字母的部分匹配
         将非字母的部分全部换成空格
        */
        str1= str1.replaceAll("[^a-zA-Z]", " ");
        //System.out.println(str1);
        str1 = str1.replaceAll("\\s"," ");

        //String[]str2 = str1.split("\"[  ' ']\"");
        String[]str2 = str1.split("\\s");
        int j = 0;
        /*for(int i  = 0;i < str2.length;i++){
            str2[i] = str2[i].trim();
            System.out.println(str2[i]);
        }
        System.out.println(str2.length);*/
        //把原字符串中的关键词写入hashMap中。
        for(int i = 0;i < str2.length;i++){
            for(j = 0 ;j < keyWords.length;j++){
                if(str2[i].equals(keyWords[j])){
                    hashMap.put(keyWords[j],0);
                }
            }
        }
        //进行计数
        for(int i = 0;i < str2.length;i++){
            for(j = 0 ;j < keyWords.length;j++){
                if(str2[i].equals(keyWords[j])){
                    count = hashMap.get(keyWords[j]);
                    hashMap.put(keyWords[j],count+1);
                }
            }
        }
        //        Set<String> str3 = hashMap.keySet();
        //        Iterator<String> it = str3.iterator();//尝试使用迭代器输出
        /*List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();*/
        TreeMap treeMap = new TreeMap(hashMap);
        Set<String> str3 = treeMap.keySet();
        Iterator<String> it = str3.iterator();//尝试使用迭代器输出
        while (it.hasNext()){
            String key = it.next();
            int value = (int) treeMap.get(key);
            System.out.println(value+"\t"+key);
        }
        /*Set set=hashMap.keySet();
        Object[] arr=set.toArray();
        Arrays.sort(arr);
        for(Object k:arr){
            System.out.println(hashMap.get(k)+"\t"+k);
        }*/



    }
}
