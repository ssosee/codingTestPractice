package mindas;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        String[] dir = new String[]{"C:/root","C:/root/folder1","C:/root/folder2/file1.txt","C:/root/folder2/file2.txt"};
        String[][] cmd = new String[][]{{"rmdir","folder2"}, {"CD", "folder1"}};

        String currentPath = dir[0];

        for(int i = 0; i < cmd.length; i++) {
            String command = cmd[i][0];
            String fileOrPath = cmd[i][1];
            // 폴더 삭제
            if(command.equals("rmdir")) {
                for(int j = 0; j < dir.length; j++) {
                    if(!dir[j].contains(fileOrPath+"/") && dir[j].contains(fileOrPath)) {
                        dir[j] = dir[j].replaceAll("/" + fileOrPath, "");
                    }
                }
            }
            // 폴더 생성
            else if(command.equals("mkdir")) {
                for(int j = 0; j < dir.length; j++) {
                    // 폴더 또는 파일이 없으면
                    if(!dir[j].contains(fileOrPath)) {
                        // 현재 경로를 기준으로 폴더 생성
                        dir[0] += "/"+ fileOrPath;
                        // 현재 경로를 갱신
                        currentPath = dir[0];
                        break;
                    }
                }
            }
            // 경로 이동
            else if(command.equals("CD")) {
                for(int j = 0; j < dir.length; j++) {
                    // 폴더 또는 파일이 있으면
                    if(dir[j].contains(fileOrPath)) {
                        // 현재 경로를 기준으로 현재 경로 이동
                        currentPath = dir[j];
                        break;
                    }
                }
            }
        }

        System.out.print(currentPath);
    }
}
