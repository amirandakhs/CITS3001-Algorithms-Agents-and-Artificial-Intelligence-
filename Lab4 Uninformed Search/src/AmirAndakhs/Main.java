package AmirAndakhs;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {


    public static void main(String[] args) {
        String[] dict = {"AIM", "ARM", "ART", "RIM", "RAM", "RAT",
                "ROT", "RUM", "RUN", "BOT", "JAM", "JOB", "JAB", "LAB", "LOB", "LOG", "SUN"};
//        System.out.println("ZA".substring(0,1).compareTo("AB".substring(0,1)));
//        List<String> new_dic =new ArrayList<String>();
//        try {
//            File myObj = new File("Dictionary.txt");
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                new_dic.add(data);
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
        String[] result = findPath(dict, "AIM", "BOT");
//
        System.out.println(result.length);


    }
    public static String[] findPath(String[] dictionary, String startWord, String endWord){
        List<String> new_dic =new ArrayList<String>();
        for(String i: dictionary){
            if (i.length() == startWord.length()){
                new_dic.add(i);
            }
        }
        String[] dic =  new String[new_dic.size()];

        dic = new_dic.toArray(dic);
//        System.out.println(dic.length);

//        List<String> words =new ArrayList<String>();
        List<String> visited =new ArrayList<String>();

//        words.add(startWord);
        visited.add(startWord);

        String[] fResult = new String[0];
        int min = Integer.MAX_VALUE;

        Node root = new Node(startWord, null,1);
        Queue<Node> nodes = new LinkedList<>();



        nodes.add(root);
        boolean flag = true;
        while (!nodes.isEmpty() && flag ){

            Node node = nodes.poll();
//            List<String> tour = node.visited;
//            System.out.println(node.value);
            if(node.size>10){
                continue;
            }
            List<String> similarity = checkingSim(node.value, dic);
            for (String word:similarity){
                if(visited.contains(word)){
                    continue;
                }
//                System.out.println(word);
                if(word.equals(endWord)){
//                    tour.add(word);
//                    if (tour.size() < min){
//                        min = tour.size();
//                        fResult = new String[tour.size()];
//                        fResult = tour.toArray(fResult);
//                        for(String j: fResult){
//                            System.out.println("j is" + j);
//                        }
//                        return fResult;
//                    }
                    System.out.println(node.value);
                    System.out.println(node.size);
                    fResult = new String[node.size +1];
                    fResult[node.size] = word;
                    fResult[node.size-1] = node.value;
                    Node temp = node.parent;
                    for(int i = node.size-2; i>=0;i--){
                        fResult[i] = temp.value;
                        temp = temp.parent;
                    }

                    flag = false;
                    break;

                }else {
//                    List<String> temp =new ArrayList<String>(tour);
//                    temp.add(word);
                    Node newNode = new Node(word,node, node.size+ 1 );
                    visited.add(word);
                    nodes.add(newNode);
                }

            }

        }
//        while (node)
        for (String i: fResult){
            System.out.println(i);
        }
        return fResult;

    }
    public static class Node {
        String value;
        Node parent;
        int size;

        Node(String value, Node parent, int size) {
            this.value = value;
            this.size  = size;
            this.parent = parent ;
        }
    }
    public static List<String> checkingSim(String word, String[] dict){
        List<String> similar =new ArrayList<String>();



        for (int i = 0; i < word.length(); i++) {
            for (String item:dict) {
                if(i!=0 && item.substring(0,1).compareTo(word.substring(0,1))>0){
                    break;
                }
                if(!word.equals(item)) {
                    String first = word.substring(0, i) + word.substring(i+1);
                    String second = item.substring(0, i) + item.substring(i+1);
                    if (first.equals(second)) {
                        similar.add(item);
                    }
                }
            }

        }


        return similar;
    }

}
