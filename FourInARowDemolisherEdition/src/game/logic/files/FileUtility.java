package game.logic.files;

import game.logic.data.GameData;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileUtility {
    public static final String FILE = "GAMESAVED.dat";

    public static void saveGame(String fileName, Object o) throws IOException
    {
        ObjectOutputStream oout = null;
        if(fileName==null){
            fileName=FILE;
        }
        try{
            oout = new ObjectOutputStream(new FileOutputStream(fileName));
            oout.writeObject(o);
        }finally{
            if(oout!=null){
                oout.close();
            }
        }
    }

    public static void saveGame(File fileName, Object o) throws IOException
    {
        ObjectOutputStream oout = null;
        try{
            oout = new ObjectOutputStream(new FileOutputStream(fileName));
            oout.writeObject(o);
        }finally{
            if(oout != null)
                oout.close();
        }
    }

    public static Object loadGame(String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream oin = null;
        try{
            oin = new ObjectInputStream(new FileInputStream(fileName));
            return oin.readObject();
        }finally{
            if(oin!=null){
                oin.close();
            }
        }
    }

    public static Object loadGame(File fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream oin = null;
        try{
            oin = new ObjectInputStream(new FileInputStream(fileName));
            return oin.readObject();
        }finally{
            if(oin != null)
                oin.close();
        }
    }

    public static void saveReplay(GameData gameData) {
        ObjectOutputStream oout = null;
        String fileName;
        File replayFile = new File("temp\\replays\\Replay1.dat");
        if (!replayFile.exists()) {
            fileName="temp\\replays\\Replay1.dat";
        }else{
            replayFile = new File("temp\\replays\\Replay2.dat");
            if (!replayFile.exists()) {
                fileName="temp\\replays\\Replay2.dat";
            }else{
                replayFile = new File("temp\\replays\\Replay3.dat");
                if (!replayFile.exists()) {
                    fileName="temp\\replays\\Replay3.dat";
                }else{
                    replayFile = new File("temp\\replays\\Replay4.dat");
                    if (!replayFile.exists()) {
                        fileName="temp\\replays\\Replay4.dat";
                    }else{
                        replayFile = new File("temp\\replays\\Replay5.dat");
                        if (!replayFile.exists()) {
                            fileName="temp\\replays\\Replay5.dat";
                        }else {
                            File f1 = new File("temp\\replays\\Replay1.dat");
                            f1.delete();
                            File f2 = new File("temp\\replays\\Replay2.dat");
                            f2.renameTo(new File("temp\\replays\\Replay1.dat"));
                            File f3 = new File("temp\\replays\\Replay3.dat");
                            f3.renameTo(new File("temp\\replays\\Replay2.dat"));
                            File f4 = new File("temp\\replays\\Replay4.dat");
                            f4.renameTo(new File("temp\\replays\\Replay3.dat"));
                            File f5 = new File("temp\\replays\\Replay5.dat");
                            f5.renameTo(new File("temp\\replays\\Replay4.dat"));
                            fileName="temp\\replays\\Replay5.dat";
                        }
                    }
                }
            }
        }
        try{
            oout = new ObjectOutputStream(new FileOutputStream(fileName));
            oout.writeObject(gameData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(oout!=null){
                try {
                    oout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
