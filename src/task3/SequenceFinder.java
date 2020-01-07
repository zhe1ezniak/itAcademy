package task3;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class SequenceFinder implements Callable<Integer> {
    private String fileName;
    private List<Byte> resultSequence = new ArrayList<>();
    private int firstIndex;
    private int secondIndex;

    public SequenceFinder(String fileName) {
        this.fileName = fileName;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int getSecondIndex() {
        return secondIndex;
    }

    @Override
    public Integer call() {
        try (FileInputStream inputStream = new FileInputStream(fileName)){
            byte[] buffer = new byte[(int) new File(fileName).length()];
            while (inputStream.available() > 0) {
                inputStream.read(buffer);
            }

            List<Byte> bufferList = new ArrayList<>();
            for (byte b : buffer){
                bufferList.add(b);
            }

            int size = bufferList.size();
            Set <List<Byte>> tempSetSequence = new HashSet<>();

            for (int i = 0; i < size; i++){
                for (int j = i+resultSequence.size()+1; j <= size && j <= i+size/2; j++){
                    if (j == size || !bufferList.get(j).equals(bufferList.get(j - 1))){
                        List<Byte> temp = bufferList.subList(i, j);
                        if (tempSetSequence.contains(temp)){
                            if (temp.size() > resultSequence.size()){
                                resultSequence = temp;
                            }
                        }
                        else {
                            tempSetSequence.add(temp);
                        }
                    }
                }
            }

            firstIndex = Collections.indexOfSubList(bufferList, resultSequence);
            secondIndex = Collections.lastIndexOfSubList(bufferList, resultSequence);

        } catch (IOException e) {
            System.out.println("Read file exception");
        }

        return resultSequence.size();
    }
}
